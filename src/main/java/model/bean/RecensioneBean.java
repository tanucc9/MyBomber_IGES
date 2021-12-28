package model.bean;
import java.io.Serializable;
import java.sql.Date;

public class RecensioneBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String recensore;
	private String recensito;
	private float recensione;
	private String evento;
	
	public void setRecensione(float recensione) {
		this.recensione=recensione;
	}
	public void setRecensore(String recensore) {
		this.recensore=recensore;
	}
	public void setRecensito(String recensito) {
		this.recensito=recensito;
	}
	public void setEvento(String evento) {
		this.evento=evento;
	}
	
	
	public float getRecensione()
	{
		return recensione;
	}
	public String getRecensore()
	{
		return recensore;
	}
	public String getRecensito()
	{
		return recensito;
	}
	public String getEvento()
	{
		return evento;
	}
	
	
	@Override
	public String toString() {
		return "recensore " + recensore + " recensito " + recensito + " evento " + evento;
	}
}