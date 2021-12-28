package model.bean;
import java.io.Serializable;
import java.sql.Date;

public class PartecipazioneBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String utente;
	private String evento;
	

	public void setUtente(String utente) {
		this.utente=utente;
	}
	public void setEvento(String evento) {
		this.evento=evento;
	}
		
	public String getUtente()
	{
		return utente;
	}
	public String getEvento()
	{
		return evento;
	}
	
	
	@Override
	public String toString() {
		return "utente " + utente + " evento " + evento ;
	}
}