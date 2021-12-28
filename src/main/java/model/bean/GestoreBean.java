package model.bean;
import java.io.Serializable;
import java.sql.Date;

public class GestoreBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	

	private String email;
	private String nome;
	private String cognome;
	private String password;
	private String telefono;
	private String struttura;

	
	public void setEmail(String email) {
		this.email=email;
	}
	public void setNome(String nome) {
		this.nome=nome;
	}
	public void setCognome(String cognome) {
		this.cognome=cognome;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public void setTelefono(String telefono) {
		this.telefono=telefono;
	}

	public void setStruttura(String struttura) {
		this.struttura=struttura;
	}
	
	public String getEmail()
	{
		return email;
	}
	public String getNome()
	{
		return nome;
	}
	public String getCognome()
	{
		return cognome;
	}
	public String getPassword()
	{
		return password;
	}
	public String getTelefono()
	{
		return telefono;
	}
	
	public String getStruttura()
	{
		return struttura;
	}
	@Override
	public String toString() {
		return " email " + email + " nome " + nome + 
				" cognome " + cognome + " password " + password + 
				" telefono " + telefono+ " struttura " + struttura;
	}
}