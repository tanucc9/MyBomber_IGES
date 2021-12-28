package model.bean;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class EventoBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descrizione;
	private String struttura;
	private Date data;
	private Time ora;
	private String gestore;
	private String organizzatore;
	

	public void setNome(String nome) {
		this.nome=nome;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione=descrizione;
	}
	public void setStruttura(String struttura) {
		this.struttura=struttura;
	}
	public void setData(Date data) {
		this.data=data;
	}
	public void setOra(Time ora) {
		this.ora=ora;
	}
	public void setGestore(String gestore) {
		this.gestore=gestore;
	}
	public void setOrganizzatore(String organizzatore) {
		this.organizzatore=organizzatore;
	}
	
	public String getNome()
	{
		return nome;
	}
	public String getDescrizione()
	{
		return descrizione;
	}
	public String getStruttura()
	{
		return struttura;
	}
	public Date getData()
	{
		return data;
	}
	public Time getOra()
	{
		return ora;
	}
	public String getGestore()
	{
		return gestore;
	}
	public String getOrganizzatore()
	{
		return organizzatore;
	}
	
	@Override
	public String toString() {
		return "nome " + nome + " descrizione " + descrizione + " struttura " + struttura + 
				" data " + data.toString() + " ora " + ora.toString() + " gestore " + gestore + 
				" organizzatore " + organizzatore ;
	}
}