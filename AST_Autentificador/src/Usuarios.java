
public class Usuarios {

	private String Nick;
	private String password;
	private String mail;
	
	public Usuarios(){
		
	}
	
	public Usuarios(String Nick, String password, String mail){
		this.setNick(Nick);
		this.setPassword(password);
		this.setMail(mail);
	}

	public String getNick() {
		return Nick;
	}

	public void setNick(String nick) {
		Nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String toString(){
return "Nick = "+Nick+"Password = "+password+"Mail = "+password;
	}
}
	
