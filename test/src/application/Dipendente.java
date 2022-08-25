package application;

public class Dipendente {

	private String user;
	private String password;
	private String nome;
	private String cognome;
	private String data;
	private String luogo;
	private String mail;
	private String telefono;
	
	public Dipendente(String user, String password) {
		this.user=user;
		this.password=password;
	}
	
	
	public Dipendente(String user, String password,String nome, String cognome,String data, String luogo,String mail,String telefono) {
		this.user=user;
		this.password=password;
		this.nome=nome;
		this.cognome=cognome;
		this.data=data;
		this.luogo=luogo;
		this.mail=mail;
		this.telefono=telefono;
	}
	
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
    public String getData() {
		return data;
	}
    public  String getLuogo() {
		return luogo;
	}
    public  String getMail() {
		return mail;
	}
    public  String getTelefono() {
		return telefono;
	}
}
