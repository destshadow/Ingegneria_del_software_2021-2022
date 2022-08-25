package application;


public class Lavoro {

	private String inizio;
	private String fine;
	private String mansioni;
	private String azienda;
	private String luogo;
	private String retribuzione;
	
	public Lavoro(String inizio, String fin, String man,String azienda, String luogo,String retribuzione) {
		this.inizio = inizio;
		this.fine = fin;
		this.azienda = azienda;
		this.luogo=luogo;
		this.retribuzione=retribuzione;
		
	     this.mansioni=man;
		}

	
	public String getInizio() {
		return inizio;
	}
	
	public String getFine() {
		return fine;
	}
	
	public String getAzienda() {
		return azienda;
	}
	
	public String getLuogo() {
		return luogo;
	}
	
	public String getRetribuzione() {
		return retribuzione;
	}
	
	public String getMansioni(){
		return mansioni;
	}
	
	public String toString() {
		return inizio+","+ fine +","+ azienda +","+luogo+","+mansioni+","+retribuzione+"\n";
	}

}
