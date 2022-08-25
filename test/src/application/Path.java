package application;

public class Path{
	
	private static Path path = new Path();
	
	private Path() {};
	
	public static Path getPath() {
		return path;
	}
	
	public String showPathPartenza() {
		return "C:\\Users\\amara\\OneDrive\\Desktop\\test\\src\\application\\login.csv";
	}
	
	public String showPathLogin() {
		return "login.csv";
	}
	
	public String showPathLavoratoriPartenza() {
		return "C:\\Users\\amara\\OneDrive\\Desktop\\test\\src\\application\\lavoratori.csv";
	}
	
	public String showPathLavoratori() {
		return "lavoratori.csv";
	}
	
	

}
