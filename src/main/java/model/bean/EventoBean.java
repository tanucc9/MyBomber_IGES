package model.bean;
import java.io.Serializable;
import java.sql.Date;

public class EventoBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descrizione;
	private String struttura;
	private Date data;
	private int ora;
	private String gestore;
	private String organizzatore;
	private String stato; //richiesta - attivo - completo - concluso
	private float valutazione; //somma valutazioni giocatori
	private int numPartecipanti;
	
	
	public EventoBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public EventoBean(String nome, String descrizione, String struttura, Date data, int ora, String gestore, String organizzatore) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.struttura = struttura;
		this.data = data;
		this.ora = ora;
		this.gestore = gestore;
		this.organizzatore = organizzatore;
		this.stato = "richiesta";
		this.valutazione = 0;
		this.numPartecipanti = 0;
	}
	
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
	public void setOra(int ora) {
		this.ora=ora;
	}
	public void setGestore(String gestore) {
		this.gestore=gestore;

	}
	
	public void setOrganizzatore(String organizzatore) {
		this.organizzatore=organizzatore;
	}
	
	public void setStato(String stato) {
		this.stato = stato;
	}
	
	public void setValutazione(float valutazione) {
		this.valutazione = valutazione;
	}
	
	public void setNumPartecipanti(int numPartecipanti) {
		this.numPartecipanti = numPartecipanti;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public String getStruttura() {
		return struttura;
	}
	
	public Date getData() {
		return data;
	}
	
	public int getOra() {
		return ora;
	}
	
	public String getGestore() {
		return gestore;
	}
	
	public String getOrganizzatore() {
		return organizzatore;
	}
	
	public String getStato() {
		return stato;
	}
	
	public float getValutazione() {
		return valutazione;
	}
	
	public int getNumPartecipanti() {
		return numPartecipanti;
	}
	
	public float getMedia() {
		return valutazione / numPartecipanti;
	}
	
	public void aggiungiG() {
		numPartecipanti++;
	}
	
	public void rimuoviG() {
		numPartecipanti--;
	}
	
	@Override
	public String toString() {
		return "Evento [nome = " + nome + ", descrizione = " + descrizione + ", struttura = " + struttura + 
				", data = " + data.toString() + ", ora = " + ora + ", gestore = " + gestore + 
				", organizzatore = " + organizzatore + ", stato = " + stato + "]";
	}
}