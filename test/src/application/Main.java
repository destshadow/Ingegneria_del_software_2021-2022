package application;
	

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;




public class Main extends Application {
	
	//lista di dipendenti dell'agenzia
	protected static ObservableList<Dipendente> dipendenti = FXCollections.
            observableArrayList();
	
    //lista di lavoratori inseriti
	protected static ObservableList<Lavoratore> lavoratori = FXCollections.
			observableArrayList();
	
	 // variabili 
	static Path path = Path.getPath();
	static Persona persona;
	static Lavoro[] lavoro = new Lavoro[5];
	protected static BufferedReader reader;	
	
	public static void main(String[] args) throws IOException {	    
    String line = "";
    String delimiter = ",";
    String[] token = {"","","","","","","",""};
	
    // acquisizione lista di dipendenti già registrati 
	try
        {
            FileReader fileReader = new FileReader(path.showPathPartenza());
            
            reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null) {
              	token = line.split(delimiter);
             
             	dipendenti.add(new Dipendente(token[0], token[1], token[2],token[3],token[4],token[5], token[6],token[7]));
               
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
	
	    // acquisizione lavoratori già registrati 
        getListaLavoratori(path.showPathLavoratoriPartenza());
        
		Application.launch(args);
	}
 
	@Override
	//metodo inizio applicazione 
	public void start(Stage primaryStage) {
	Parent root;
		try{
			root = FXMLLoader.load(getClass().getResource("login.fxml"));
			primaryStage.setTitle("Welcome");
			primaryStage.setScene(new Scene(root));
			//Model model = new Model();
			//Controller c;
			//c = (Controller) loader.getController();
			primaryStage.show();

		    } 
		catch(Exception e)    {
			e.printStackTrace();
		}	
	}
	
    //metodo per aggiornare la lista dei dipendenti contenuta nei file quando un nuovo dipendente accede
	
	public static void AggiornaLista(String fxml, String user) throws IOException {
		
		String line = "";
		
		FileWriter w = new FileWriter(path.showPathLogin());
		BufferedWriter b = new BufferedWriter(w);
		
        FileReader fileReader = new FileReader(path.showPathPartenza());
        
        reader = new BufferedReader(fileReader);
        while ((line = reader.readLine()) != null)   
        {
          	String token = line;
         if(token.contains(user)) {
        	 b.write(fxml);
            }
         else
        	 b.write(token + "\n");
        }
        reader.close();
		
		b.flush();
		b.close();
		CopiaFile(path.showPathPartenza(),path.showPathLogin());
		
	}
    
	//aggiorna i file contenenti la lista di lavoratori quando viene inserito un nuovo lavoratore
	
	public static void AggiornaListaLavoratori(String lavoratore, String nome_lavoratore, int lunghezza) throws IOException {
        int trovato = 0;
        String line = "";
		
		FileWriter w = new FileWriter(path.showPathLavoratori());
		BufferedWriter b = new BufferedWriter(w);
		
        FileReader fileReader = new FileReader(path.showPathLavoratoriPartenza());
        
        reader = new BufferedReader(fileReader);
        while ((line = reader.readLine()) != null)   
        {   
        	String titolo = line;
        	if(titolo.contains(nome_lavoratore) && trovato == 0) {
        		if(titolo.contains("null")) {
        			b.write(titolo.substring(0, lunghezza)+","+lavoratore);
        		}
        		else {
        			b.write(titolo +"\n");
        		    b.write(titolo.substring(0, lunghezza) + ","+lavoratore);
        		}
        		trovato =1;
        	}
        	else
        	   b.write(titolo +"\n");
         }
        
        if(trovato == 0)
        	b.write(lavoratore +"null,null,null,null,null,null" );
        
        
        reader.close();
		
		b.flush();
		b.close();
		
		CopiaFile(path.showPathLavoratoriPartenza(),path.showPathLavoratori());

	}

	//crea lista dei lavoratori dal file csv e lo restituisce
	
	public static ObservableList<Lavoratore> getListaLavoratori(String csv) throws IOException {

		String line = "";
		String delimiter = ",";
		String[] token ;
        int index = 0;
        int trovato = 0 ;
        
		FileReader fileReader = new FileReader(csv);
		reader = new BufferedReader(fileReader);
		while ((line = reader.readLine()) != null) {
			token = line.split(delimiter);
			if(token[0].equals("")) {
				break;
			}
			for(Lavoratore lavoratore:lavoratori) {
			   if(token[0].equals(lavoratore.getNome())) {
				    index = lavoratore.getIndex();
				    lavoro =lavoratore.getLavoro();
				    Lavoro lavori = new Lavoro(token[19],token[20],token[21],token[22],token[23],token[24]);
				    lavoro[index]=lavori;
				    lavoratore.setIndex(index+1);
				    lavoratore.setLavoro(lavoro);
				    trovato = 1;
			   }
			}
			if(trovato == 0) {
		    index = 0;
		    persona = new Persona(token[15],token[16],token[17],token[18]);	
			Lavoro lavori = new Lavoro(token[19],token[20],token[21],token[22],token[23],token[24]);
			lavoro[index]=lavori;
			if(!token[19].equals("null"))
				index++;
			lavoratori.add(new Lavoratore(token[0], token[1], token[2], token[3], token[4], token[5], token[6], token[7], token[8], token[9], token[10],token[11],token[12],token[13],token[14],index,persona,lavoro));
			}
			trovato = 0;
		}
		reader.close();

		return lavoratori;
	}
	
	//aggiorna il file di partenza con la lista di lavoratori o dipendenti nuova
	 public static void CopiaFile( String scrittura , String lettura) throws IOException {
			String line = "";
			
			FileWriter w = new FileWriter(scrittura);
			BufferedWriter b = new BufferedWriter(w);
			
		    FileReader fileReader = new FileReader(lettura);
		    
		    reader = new BufferedReader(fileReader);
		    while ((line = reader.readLine()) != null)   
		    {   
		    	String titolo = line;
		    	 b.write(titolo + "\n");
		    }
		    
		    reader.close();
			b.flush();
			b.close();

		}
	 
       //elimina riga corrispondente al lavoro del lavoratore inserito per primo quando si inserisce il 6 lavoro svolto
		public void CancellaRiga(String nome ,String nome1, String nome_lavoratore) throws IOException {
		      String line = "";
			  
			   FileWriter w = new FileWriter(path.showPathLavoratori());
			   BufferedWriter b = new BufferedWriter(w);
				
		      FileReader fileReader = new FileReader(path.showPathLavoratoriPartenza());
		      
		      reader = new BufferedReader(fileReader);
		     
		      while ((line =reader.readLine()) != null)   
		      { 
		    	  String titolo = line;
		    	  System.out.println("1");
		      	if(titolo.contains(nome) && titolo.contains(nome1)&&titolo.contains(nome_lavoratore));
		      	else
		      		b.write(titolo+"\n");
		      }

		      reader.close();
				
				b.flush();
				b.close();
				
				CopiaFile(path.showPathLavoratoriPartenza(), path.showPathLavoratori());

			}
}	

