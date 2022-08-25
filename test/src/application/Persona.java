package application;

public class Persona {
	
	 private final String Nome;
	 private final String Cognome;
	 private final String Telefono;
	 private final String Email;
	 
	 public Persona(String Nome, String Cognome,String Telefono,String Email) {
		 this.Nome=Nome;
		 this.Cognome=Cognome;
		 this.Telefono=Telefono;
		 this.Email=Email;
	 }
	 
	 public String getNome() {
		 return Nome;
	 }
	  
	 public String getCognome() {
		 return Cognome;
	 }
	 
	 public String getTelefono() {
		 return Telefono;
	 }
	 
	 public String getEmail() {
		 return Email;
	 }
	 
	 public String toString() {
		 return Nome + "," +Cognome + ","+Telefono+ ","+Email +",";
	 }
}
