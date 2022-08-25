package application;

    public class Lavoratore {
        private final String Nome;
        private final String Cognome;
        private final String Luogo_nascita;
        private final String data;
        private final String Nazionalita;
        private final String Indirizzo;
        private final String Telefono;
        private final String Email;
        private final String specializzazioni ;
        private final String Lingue ;
        private final String TipoDiPatente ;
        private final String Automunito;
        private final String inizio;
        private final String fine;
        private final String zona;
       
        
        private Persona persona; //contatto di emergenza
        private Lavoro[] lavoro  = new Lavoro[5];
        private int index = 0;
        
        //costruttore
        public Lavoratore(String Nome, String Cognome, String Luogo_nascita, String data, String Nazionalita, String indirizzo, String telefono, String email, String special, String ling, String tipoDiPatente, String automunito,String inizio,String fine,String zona, int index) {
        	this.persona = null;
        	this.inizio = inizio;
        	this.fine = fine;
        	this.zona = zona;
    		this.Nome = Nome;
            this.Cognome = Cognome;
            this.Luogo_nascita = Luogo_nascita;
            this.data = data;
            this.Nazionalita = Nazionalita;
            this.Indirizzo = indirizzo;
            this.Telefono = telefono;
            this.Email = email;
            
            this.specializzazioni = special;
    		this.Lingue= ling;
    	
    	    this.TipoDiPatente = tipoDiPatente;
    	
        
            this.Automunito = automunito;
            this.index = index;
            
            this.lavoro=null;
        }

        public Lavoratore(String Nome, String Cognome, String Luogo_nascita, String data, String Nazionalita, String indirizzo, String telefono, String email, String special, String ling, String tipoDiPatente, String automunito, String inizio, String fine, String zona,int index, Persona persona,Lavoro[] lavoro){
    		this.Nome = Nome;
            this.Cognome = Cognome;
            this.Luogo_nascita = Luogo_nascita;
            this.data = data;
            this.Nazionalita = Nazionalita;
            this.Indirizzo = indirizzo;
            this.Telefono = telefono;
            this.Email = email;
            this.index = index;
           
            this.specializzazioni = special;
    		
    		this.Lingue = ling;
    	    
    	   this.TipoDiPatente = tipoDiPatente;
    		
        
            this.Automunito = automunito;
            this.inizio = inizio;
            this.fine = fine;
            this.zona = zona;
            this.persona =persona;
            this.lavoro = lavoro;
        }

	public String getNome(){
        return Nome;
    }

    public String getCognome(){
        return Cognome;
    }

    public String getLuogo_nascita(){
        return Luogo_nascita;
    }

    public String getData(){
        return data;
    }

    public String getNazionalita(){
        return Nazionalita;
    }

    //getters per gli altri campi
    public String getIndirizzo(){
        return Indirizzo;
    }

    public String getTelefono(){
        return Telefono;
    }

    public String getEmail(){
        return Email;
    }

    public String getSpecializzazioni(){
        return specializzazioni;
    }
    
    public String getLingue(){
        return Lingue;
    }

    public String getTipoDiPatente(){
        return TipoDiPatente;
    }

    public String getAutomunito(){
        return Automunito;
    }
    
    public String getInizio(){
        return inizio;
    }
    
    
    public String getFine(){
        return fine;
    }
    
    
    public String getZona(){
        return zona;
    }
    
    
    //get persona
    
    public Persona getPersona() {
    	return persona;
    }
    
    public void setPersona(Persona persona) {
    	this.persona = persona;
    }
    
    public Lavoro[] getLavoro() {
    	return lavoro;
    }
    
    public void setLavoro(Lavoro[]lavoro) {
    	this.lavoro = lavoro;
    }
    
    public int getIndex() {
    	return index;
    }
    public void setIndex(int index) {
    	this.index = index;
    }
    
    public String getStringa(int k) {
    	
    	String result = null;
    	
    	switch (k) {
    	case 0 : result = getNome();
    	         break;
    	case 1 : result = getCognome();
    	         break;
    	case 2 : result = getTelefono();
    	         break;
    	case 3 : result = getEmail();
    	         break;
    	case 4 : result = getInizio();
    	         break;
    	case 5 : result = getFine();
    	         break;
    	case 6 : result = getLingue();
    	         break;
    	case 7 : result = getSpecializzazioni();
    	         break;
    	case 8 : result = getIndirizzo();
    	         break;
    	case 9 : result = getTipoDiPatente();
    	         break;
    	case 10 :result = getAutomunito();
    	         break;
    	default :break;
    	
    	}
    	
    	return result;
		
	}
    //sempre che si usa una classe in Java, si deve usare il metodo toString() perchè essa è la soluzione più efficiente per stampare i dati della classe.
    @Override
    public String toString(){
        return  Nome + ","+ Cognome+"," +Luogo_nascita + ","+ data+ "," + Nazionalita + ","+Indirizzo+","+ Telefono+","+Email+","+specializzazioni+","+ Lingue+","+ TipoDiPatente+","+ Automunito+","+inizio+","+fine+","+ zona;
    }
    

}
