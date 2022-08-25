package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

public class Controller extends Main {
   
	//variabili usate
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String username;
	private String pass;
	private String automunito = "null";
	private Persona persona;
    Patente patenti[] = Patente.values();
    LocalDate oggi = LocalDate.now();
    Lavoro[] lavori = new Lavoro[5];
    static String nome_lavoratore;
    static int lunghezza;
    static int finito = 0;
    static int index = 0 ;
    private String[] campi = new String[11];
    boolean[] flag = new boolean[11];
    static int count = 0 ;
    private ArrayList<Lavoratore> lavora = new ArrayList<>();
    private ArrayList<Lavoratore> tmp = new ArrayList<>();
    
    //variabili usate nei file fxml
	@FXML
	private TextField user;
	@FXML
	private PasswordField password;
	@FXML
	private TextField nome;
	@FXML
	private TextField cognome;
	@FXML
	private TextField data;
	@FXML
	private TextField luogo;
	@FXML
	private TextField telefono;
	@FXML
	private TextField email;
	@FXML
	private Label label;
	@FXML
	private TextField nazionalita;
	@FXML
	private TextField via,cap,civico,citta,indirizzo;
	@FXML
	private TextField patente;
	@FXML
	private TextField lingua;
	@FXML
	private TextField specializzazioni;
	@FXML
	private TextField inizio,fine,zona;
	@FXML
	private TextField azienda,mansioni,retribuzione;
	@FXML
	private TextField andor;
	
	
	@FXML
	private void Login(ActionEvent event) throws IOException {
		
        int i = 0;
   	    int trovato = 0;
		username = user.getText();
		pass = password.getText();
		
		for (Dipendente dipendente : dipendenti) {
			if(dipendente.getUser().equals(username) || dipendente.getPassword().equals(pass))
				trovato = 1 ;
			else
				trovato = 0 ;
		}
        if(trovato == 1) {
		for (Dipendente dipendente : dipendenti) {
			if(username.length() == 0 || pass.length() == 0){
				i= 1;
				label.setText("Accesso Negato : credenziali errate");
				break;	
			}
			else if (dipendente.getUser().equals(username) && dipendente.getPassword().equals(pass) && (dipendente.getNome().compareTo("null") != 0)) {
				CambioScena("menu.fxml", "Menu", event);
				i = 1;
				break;

			} else if (dipendente.getUser().equals(username) && (dipendente.getPassword().compareTo(pass) != 0) || (dipendente.getUser().compareTo(username) != 0) && dipendente.getPassword().equals(pass)) {
				user.setText(null);
				password.setText(null);
				label.setText("Accesso Negato : credenziali errate");
				i = 1;
				break;

			}
			
		}
		
	
		if (i == 0) {
			CambioScena("registrazione.fxml", "Registrazione", event);
		}
        }
        else {
        	user.setText(null);
			password.setText(null);
        	label.setText("Accesso negato credenziali errate");
        }
        	
	}


	@FXML
	private void Registrazione(ActionEvent event) throws IOException {
		int trovato = 0; //user-password non corretti
        int errore = 0 ;

		for (Dipendente dipendente : dipendenti) {
			if (user.getText().compareTo(dipendente.getUser()) == 0 && password.getText().compareTo(dipendente.getPassword()) == 0) {
				trovato = 1;
				break;
			}
		}

		if (trovato == 0) {
			user.setText(null);
			password.setText(null);
			label.setText("Accesso negato : credenziali errate");

		} else {

			if (ControlloStringa(nome.getText()) == false) {
				errore++;
				nome.setText(null);
				label.setText("Accesso negato : nome sbagliato");
			}
			if (ControlloStringa(cognome.getText()) == false) {
				errore++;
				cognome.setText(null);
				label.setText("Accesso negato : cognome sbagliato");
			}
			if (ControlloStringa(luogo.getText()) == false) {
				errore++;
				luogo.setText(null);
				label.setText("Accesso negato : luogo sbagliato");
			}
		
			if (ControlloTelefono(telefono.getText()) == false) {
				errore++;
				telefono.setText(null);
				label.setText("Accesso negato : numero di telefono sbagliato");
			}

			if (ControlloEmail(email.getText()) == false) {
				errore++;
				email.setText(null);
				label.setText("Accesso negato : email sbagliata");
			}

			if (ControlloData(data.getText()) == false) {
				errore++;
				data.setText(null);
				label.setText("Accesso negato : data di nascita sbagliata");
			}

            if(errore == 0) {
			dipendenti.add(new Dipendente(user.getText(), password.getText(), nome.getText(), cognome.getText(), data.getText(), luogo.getText(), email.getText(), telefono.getText()));
			String dipendente = user.getText() + "," + password.getText() + "," + nome.getText() + "," + cognome.getText() + "," + data.getText() + "," + luogo.getText() + "," + email.getText() + "," + telefono.getText() + "\n";
			AggiornaLista(dipendente, user.getText());
			CambioScena("menu.fxml", "Menu", event);
            }
		}

	}
	
	@FXML
	private void Automunito(ActionEvent event) throws IOException {
		automunito = "true";
	}

	@FXML
	private void NonAutomunito(ActionEvent event) throws IOException {
		automunito = "false";

	}

    @FXML
    private void TornaAlMenu(ActionEvent event) throws IOException {

		CambioScena("menu.fxml", "Menù", event);

	}


	@FXML
	private void CreaLavoratore(ActionEvent event) throws IOException {

		CambioScena("lavoratore.fxml", "Lavoratore", event);

	}

	@FXML
	private void InserisciUnNuovoLavoratore(ActionEvent event) throws IOException {
		int errore = 0;
		
	
		if (ControlloStringa(nome.getText()) == false) {
			errore++;
			nome.setText(null);
			label.setText("nome sbagliato");
		}
		
		if (ControlloStringa(cognome.getText()) == false) {
			errore++;
			cognome.setText(null);
			label.setText("cognome sbagliato");
		}
		
		
		if (ControlloData(data.getText())== false) {
			errore++;
			data.setText(null);
			label.setText(" data di nascita sbagliata");
		}

		if (ControlloStringa(luogo.getText()) == false) {
			errore++;
			luogo.setText(null);
			label.setText("luogo sbagliato");
		}
		
		if (ControlloStringa(nazionalita.getText()) == false) {
			errore++;
			nazionalita.setText(null);
			label.setText("nazionalità sbagliata");
		}

		if (ControlloTelefono(telefono.getText()) == false) {
			errore++;
			telefono.setText(null);
			label.setText(" numero di telefono sbagliato");
		}

		if (ControlloEmail(email.getText()) == false) {
			errore++;
			email.setText(null);
			label.setText(" email sbagliata");
		}

		
		if (ControlloStringa(via.getText()) == false) {
			errore++;
			via.setText(null);
			label.setText("via sbagliata");
		};
		
		
		if(ControlloCivico(civico.getText()) == false) {
			errore++;
			civico.setText(null);
			label.setText(" Indirizzo sbagliato");
		}
		
		if(ControlloCap(cap.getText()) == false) {
			errore++;
			cap.setText(null);
			label.setText(" Indirizzo sbagliato");
		}
		if (ControlloStringa(citta.getText()) == false) {
			errore++;
			citta.setText(null);
			label.setText("indirizzo sbagliato");
		};
		
		String indirizzo = via.getText()+" "+civico.getText()+" "+cap.getText()+" "+citta.getText();
		
		String[] line = (patente.getText()).split("-");
		for(String pat :line) {
		    if(ControlloPatente(pat) == false) {
		    	errore++;
			    patente.setText(null);
			    label.setText(" Patente errata");
			    break;
		    }
		}

		String s = lingua.getText();
		String[] s1 = s.split("-");
		for(int i = 0; i < s1.length; i++) {
			if(ControlloStringa(s1[i]) == false){
				errore++;
				lingua.setText(null);
				label.setText(" Lingua errata");
				break;
			}
		}
		
		String l = specializzazioni.getText();
		String[] l1 = l.split("-");
		for(int i = 0; i < l1.length; i++) {
			if(ControlloStringa(l1[i]) == false){
				errore++;
				specializzazioni.setText(null);
				label.setText(" Specializzazoni errate");
				break;
			}
		}
		
		if (ControlloData(inizio.getText())== false) {
			errore++;
			inizio.setText(null);
			label.setText(" data di inizio sbagliata");
		}
		
		if (ControlloDate(fine.getText())== false) {
			errore++;
			fine.setText(null);
			label.setText("data di fine  sbagliata");
		}
		
		if (ControllaPeriodo(inizio.getText(), fine.getText()) == false) {
			errore++;
			inizio.setText(null);
			fine.setText(null);
			label.setText(" data di inizio o fine sbagliata");
		}
		
		if (ControlloStringa(zona.getText()) == false) {
			errore++;
			zona.setText(null);
			label.setText("zona sbagliata");
		};
		
       
		if(errore == 0) {
			nome_lavoratore =nome.getText();
			lavoratori.add(new Lavoratore(nome.getText(), cognome.getText(), luogo.getText(),data.getText(), nazionalita.getText(), indirizzo, telefono.getText(), email.getText(), specializzazioni.getText(),lingua.getText(), patente.getText(), automunito,inizio.getText(),fine.getText(),zona.getText(),0));
			automunito = "null";
			CambioScena("urgenza.fxml", "Contatto di emergenza",event);
		}
			
		
	}



	@FXML
	private void Menu(ActionEvent event) throws IOException {
		int errore= 0;
		
		if (ControlloStringa(nome.getText()) == false) {
			errore++;
			nome.setText(null);
			label.setText("nome sbagliato");
		};
		if (ControlloStringa(cognome.getText()) == false) {
			errore++;
			cognome.setText(null);
			label.setText("cognome sbagliato");
		}
		
		
		if (ControlloTelefono(telefono.getText()) == false) {
			errore++;
			telefono.setText(null);
			label.setText(" numero di telefono sbagliato");
		}

		if (ControlloEmail(email.getText()) == false) {
			errore++;
			email.setText(null);
			label.setText(" email sbagliata");
		}
		if(errore == 0) {
			persona = new Persona(nome.getText(),cognome.getText(),telefono.getText(),email.getText());
			AggiornaPersona(nome_lavoratore);
			String pers = persona.toString();
		 	for(Lavoratore lavoratore :lavoratori) {
		 		if(lavoratore.getNome().contentEquals(nome_lavoratore)) {
		 			lavoratore.setLavoro(lavori);
		 		AggiornaListaLavoratori(lavoratore.toString() + ","+ pers,nome_lavoratore,30);
		 		}
		 	}
			CambioScena("menu.fxml","Menu",event);
		}
	}
	
	@FXML
	private void MenuLavoro(ActionEvent event) throws IOException {
		int errore= 0;
		int trovato = 0;
		
		nome_lavoratore = nome.getText();
		for(Lavoratore lavoratore :lavoratori) {
			if(lavoratore.getNome().equals(nome_lavoratore)) {
				trovato = 1;
				break;
			}				
		}
		
		System.out.println(trovato);
		
		if(trovato == 0) {
			CambioScena("errore.fxml","Errore",event);
		}
		
		
		if(trovato == 1) {
		
		if (ControlloData(inizio.getText()) == false) {
			errore++;
			inizio.setText(null);
			label.setText(" data inizo sbagliata");
		}

		if (ControlloData(fine.getText()) == false) {
			errore++;
			fine.setText(null);
			label.setText(" data fine sbagliata");
		}
		
		if(ControllaPeriodo(inizio.getText(),fine.getText()) == false || ControllaAnno(inizio.getText()) == false) {
			errore++;
			inizio.setText(null);
			fine.setText(null);
			label.setText(" data fine sbagliata");
		}
		
		if (ControlloStringa(azienda.getText()) == false) {
			errore++;
			azienda.setText(null);
			label.setText("azienda sbagliata");
		}
		if (ControlloStringa(luogo.getText()) == false) {
			errore++;
			luogo.setText(null);
			label.setText("luogo sbagliato");
		}

		
		String l = mansioni.getText();
		String[] l1 = l.split("-");
		for(int i = 0; i < l1.length; i++) {
			if(ControlloStringa(l1[i]) == false){
				errore++;
				mansioni.setText(null);
				label.setText(" mansioni errate");
				break;
			}
		}
		
		
		if (ControlloRetribuzione(retribuzione.getText()) == false) {
			errore++;
			retribuzione.setText(null);
			label.setText(" retribuzione sbagliata");
		}
		
		if(errore == 0 ) {
			Lavoro lavorato = new Lavoro(inizio.getText(),fine.getText(), mansioni.getText(),azienda.getText(),luogo.getText(),retribuzione.getText());
			if(AggiungiLavoro(lavorato,nome_lavoratore)==0) {
				AggiungiLavoro(lavorato,nome_lavoratore);
			}
			CambioScena("menu.fxml","Menù",event);
		}
		}
	}

	@FXML
	private void Lavoro(ActionEvent event) throws IOException {
		CambioScena("lavoro.fxml", "Lavori ultimi 5 anni", event);

	}

	@FXML
	private void Ricerca(ActionEvent event) throws IOException {
		CambioScena("ricerca.fxml", "Ricerca", event);

	}

 @FXML
  private void Ricerca2(ActionEvent event) throws IOException {
		int errore = 0;
		
		for(int k = 0 ; k < 11 ; k++)
			campi[k]= "null";
        
		if(!nome.getText().equals("")) {
			if (ControlloStringa(nome.getText()) == false) {
				errore++;
			    nome.setText(null);
				label.setText("nome sbagliato");
			};
		   if(errore == 0) {
			   campi[0]=nome.getText();
			   count ++;
		   }
		}
        
		if(!cognome.getText().equals("")) {
			if (ControlloStringa(cognome.getText()) == false) {
				errore++;
				cognome.setText(null);
				label.setText("cognome sbagliato");
			};
		   if(errore == 0) {
			   campi[1]=cognome.getText();
			   count++;
		   }
		}
		if (!telefono.getText().equals("")) {
			if(ControlloTelefono(telefono.getText()) == false) {
			    errore++;
			   telefono.setText(null);
			   label.setText("numero di telefono sbagliato");
		      }
			else {
				campi[2]=telefono.getText();
				count++;
			}
		}

		if (!email.getText().contentEquals("") ) {
			if(ControlloEmail(email.getText()) == false) {
			   errore++;
			   email.setText(null);
			   label.setText(" email sbagliata");
		     }
			else {
				campi[3]=email.getText();
				count++;
			}
		}

		if (!inizio.getText().equals("")) {
			if(ControlloData(inizio.getText())== false) {
			   errore++;
			   inizio.setText(null);
			   label.setText(" data di inizio sbagliata");
		     }
			else {
				campi[4] = inizio.getText();
			}
		}

		if (!fine.getText().contentEquals("")) {
			if(ControlloData(fine.getText())== false) {
			   errore++;
			   fine.setText(null);
			  label.setText("data di fine  sbagliata");
		      }
			else {
				campi[5]=fine.getText();
			}
		}

		if (!inizio.getText().contentEquals("") && !fine.getText().contentEquals("")) {
			if(ControllaPeriodo(inizio.getText(), fine.getText()) == false) {
			    errore++;
			    inizio.setText(null);
			    fine.setText(null);
			    label.setText(" data di inizio o fine sbagliata");
		        }
			else {
				campi[4] = inizio.getText();
			    campi[5] = fine.getText();
			    count+=2;
		        }
		}
		//lingue
		if (!lingua.getText().equals("")) {
			if(ControlloStringa(lingua.getText()) == false) {
			   errore++;
			   lingua.setText(null);
			   label.setText(" lingue sbagliate");
		    }
			else {
				campi[6]=lingua.getText();
				count++;
			}
		}
		//Specializzazioni
		if (!specializzazioni.getText().equals("")) {
			if(ControlloStringa(specializzazioni.getText()) == false) {
			   errore++;
			   specializzazioni.setText(null);
			    label.setText(" specializzazioni sbagliate");
		       }
			else {
				campi[7]=specializzazioni.getText();
				count++;
			}
		}

		//residenza
		if (!via.getText().equals("")) {
			if (ControlloStringa(via.getText()) == false) {
				errore++;
				via.setText(null);
				label.setText("via sbagliata");
			};
			
			
			if(ControlloCivico(civico.getText()) == false) {
				errore++;
				civico.setText(null);
				label.setText(" Indirizzo sbagliato");
			}
			
			if(ControlloCap(cap.getText()) == false) {
				errore++;
				cap.setText(null);
				label.setText(" Indirizzo sbagliato");
			}
			if (ControlloStringa(citta.getText()) == false) {
				errore++;
				citta.setText(null);
				label.setText("città sbagliata");
			}
			
			if(errore == 0) {
				String indirizzo = via.getText()+" "+civico.getText()+" "+cap.getText()+" "+citta.getText();
				campi[8]=indirizzo;
				count++;
			}
		}

		//patente
		if (!patente.getText().contentEquals("")) {
			if(ControlloPatente(patente.getText()) == false) {
			   errore++;
			   patente.setText(null);
			   label.setText(" patente sbagliata");
		    }
			else {
				campi[9]=patente.getText();
				count++;
			}
		}
		
	    if(!automunito.equals("null")) {
			campi[10]=automunito;
			count++;
	    }
	    
		
		if(errore == 0) { 
			int j = 0 ;
			int t = 0 ;
			
			for(j = 0 ; j < count ; j++) {
				for (int k = 0 ; k < campi.length; k++) {
				   if(flag[j] == true && !(campi[k].equals("null")) && t == 0) {
						 for(Lavoratore lavoratore :lavoratori) {
						        if(lavoratore.getStringa(k).contains(campi[k])) {
						    	    lavora.add(lavoratore);
						    	    t ++;
							        }
						        }
						 campi[k]="null";
						 break;
					}
					else if(flag[j]== true && !(campi[k].equals("null")) && t != 0) {
						for(Lavoratore lavoratore : lavora) {
				    		if(lavoratore.getStringa(k).contains(campi[k])) {
				    			tmp.add(lavoratore);
				    		}
				    	}
						lavora = tmp;
						campi[k]="null";
						break;
					}
					else if(flag[j]== false && !(campi[k].equals("null")) && t == 0) {
						 for(Lavoratore lavoratore :lavoratori) {
						        if(lavoratore.getStringa(k).contains(campi[k])) {
						    	    lavora.add(lavoratore);
						    	    t ++;
							        }
						        }
						 campi[k]="null";
						 break;
					}
					else if(flag[j]== false && !(campi[k].equals("null")) && t != 0) {
						 for(Lavoratore lavoratore :lavoratori) {
							 int trovato = 0 ;
						        if(lavoratore.getStringa(k).contains(campi[k])) {
						    	        if(lavora.contains(lavoratore)) {
						    	        	trovato = 1;
							              }
						                
						                 if(trovato == 0) {
						        	         lavora.add(lavoratore);
						        	     }
						            }
						        }
						   
						 campi[k]="null";
						 break;
				}
				}
				if(lavora.size() == 0) {
					System.out.println("lavoratore non trovato");
					    break;
					}
					
				}
	
				for(Lavoratore lavoratore : lavora) {
					System.out.println(lavoratore.toString());	
			     }
				
			
			CambioScena("menu.fxml", "Menù", event);
		}
		}
		
	
 
   @FXML
	private void AND(ActionEvent event) throws IOException {
	   flag[index] = true ;
	   index++;

	}
   @FXML
	private void OR(ActionEvent event) throws IOException {
	   flag[index] = false ;
	   index++;

	}


	@FXML
	private void Finish(ActionEvent event) throws IOException{

		CambioScena("login.fxml", "Login", event);


	}

	public void CambioScena(String fxml, String titolo, ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource(fxml));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(titolo);
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public boolean ControlloStringa(String controlla) {
		
		if(controlla.length()==0)
		   return false;

		for (int i = 0; i < controlla.length(); i++) {
			char c = controlla.charAt(i);
			if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && c != ' ' && c != '\'') {
				return false;
			}
		}
		return true;
	}

	//controlla numero di telefono
	public boolean ControlloTelefono(String controlla) {
		
		if(controlla.equals(""))
			return true;

	   else if (controlla.length()< 10 || controlla.length()>10) {
			return false;
		}

		for (int i = 0; i < controlla.length(); i++) {
			char c = controlla.charAt(i);
			if (!(c >= '0' && c <= '9')) {
				return false;
			}
		}
		return true;
	}

	
	//ControlloEmail
	public boolean ControlloEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		if (email.equals("")) {
			return false;
		}
		return pattern.matcher(email).matches();
	}

	//controllo patente
	public boolean ControlloPatente(String patente) {
		if (patente.equals("")) {
			return false;
		}
		for(Patente pat : patenti) {
		   if(patente.compareTo(pat.toString()) == 0)
			   return true;
		}
		return false;
	    
	}
    public Data CostruisciData(String text) {
		
		String giorno = text.substring(0,2);
		String mese = text.substring(3,5);
		String anno = text.substring(6, text.length());
		
		int day = Integer.valueOf(giorno);
		int month = Integer.valueOf(mese);
		int year = Integer.valueOf(anno);
		
		Data data = new Data(year,month,day);
		
		return data;
		
	}
	public boolean ControlloData(String text) {
		
		if(text.length() > 10 || text.length() < 10 )
			return false ;
		
		Data data = CostruisciData(text);
		
		if (data.compareTo(oggi) > 0)
			return false;
		
		return data.isValid();
		
}
   public boolean ControlloDate(String text) {
		
		if(text.length() > 10 || text.length() < 10 )
			return false ;
		
		Data data = CostruisciData(text);
		
		return data.isValid();
		
}
	
	public boolean ControllaPeriodo(String ini, String fin) {
		
		Data data = CostruisciData(ini);
			
		Data data1 = CostruisciData(fin);
		
		if(data.compareTo(data1) > 0)
			return false;
		
		return true;	
		
	}
	public boolean ControlloCivico(String controlla) {
		
		if (controlla.equals("") || controlla.length()< 0  || controlla.length()> 3) {
			return false;
		}

		for (int i = 0; i < controlla.length(); i++) {
			char c = controlla.charAt(i);
			if (!(c >= '0' && c <= '9')) {
				return false;
			}
		}
		return true;
	}
	
    public boolean ControlloCap(String controlla) {
		
		if (controlla.equals("") || controlla.length()< 5 || controlla.length()> 5) {
			return false;
		}

		for (int i = 0; i < controlla.length(); i++) {
			char c = controlla.charAt(i);
			if (!(c >= '0' && c <= '9')) {
				return false;
			}
		}
		return true;
	}
    public boolean ControllaAnno(String text) {
	
	    Data data = CostruisciData(text);
	
	     if(data.getYear() < (oggi.getYear()-5) || data.compareTo(oggi)> 0 )
		    return false;
	
	     return true;
    }

    public boolean ControlloRetribuzione(String controlla) {
	
	     if (controlla.equals("") ) {
		     return false;
	      }

	     for (int i = 0; i < controlla.length(); i++) {
		      char c = controlla.charAt(i);
		      if (!(c >= '0' && c <= '9')) {
			      return false;
		       }
	      }
	      return true;
    }

    public void AggiornaPersona(String nome) {
	   for(Lavoratore lavoratore :lavoratori) {
		   if(lavoratore.getNome().equals(nome)) {
			  lavoratore.setPersona(persona);
		   }
	   } 
	
     }
    private int AggiungiLavoro(Lavoro lavo,String nome_lavoratore) throws IOException {
	    int aggiunto = 0;
	
		for(Lavoratore lavoratore :lavoratori) {
			if(lavoratore.getNome().equals(nome_lavoratore)) {
				lavori = lavoratore.getLavoro();
				if(lavoratore.getIndex()==5 ) {
					lavoratore.setIndex(0);
					  finito++;
				}
			    if(finito != 0) {
			        CancellaRiga(lavoratore.getLavoro()[lavoratore.getIndex()].getInizio(),lavoratore.getLavoro()[lavoratore.getIndex()].getFine(),nome_lavoratore);	
				}
				
			     lavori[lavoratore.getIndex()] = lavo;
			     lavoratore.setIndex(lavoratore.getIndex() + 1); 
			     lavoratore.setLavoro(lavori);
			      aggiunto = 1;
				
			     lunghezza =( lavoratore.toString()+lavoratore.getPersona().toString()).length();
			    
			    
				  AggiornaListaLavoratori(lavo.toString(),nome_lavoratore,lunghezza);
	                
				lunghezza = 0;
			}	     
		} 
	
	return aggiunto;
	
    }

}

