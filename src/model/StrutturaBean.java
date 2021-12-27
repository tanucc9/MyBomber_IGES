package model;
import java.io.Serializable;
import java.sql.Date;

public class StrutturaBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String nome;
	private String indirizzo;
	private String nazione;
	private String citta;
	private String cap;
	private String provincia;
	private String telefono;
	

	public void setNome(String nome) {
		this.nome=nome;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo=indirizzo;
	}
	public void setNazione(String nazione) {
		this.nazione=nazione;
	}
	public void setCitta(String citta) {
		this.citta=citta;
	}
	public void setCap(String cap) {
		this.cap=cap;
	}
	public void setProvincia(String provincia) {
		this.provincia=provincia;
	}
	public void setTelefono(String telefono) {
		this.telefono=telefono;
	}
	
	public String getNome()
	{
		return nome;
	}
	public String getIndirizzo()
	{
		return indirizzo;
	}
	public String getCitta()
	{
		return citta;
	}
	public String getNazione()
	{
		return nazione;
	}
	public String getCap()
	{
		return cap;
	}
	public String getTelefono()
	{
		return telefono;
	}
	public String getProvincia()
	{
		return provincia;
	}
	
	@Override
	public String toString() {
		return "nome " + nome + " nazione " + nazione + " citta " + citta + 
				" cap " + cap + " provincia " + provincia + " indirizzo " + indirizzo + 
				" telefono " + telefono ;
	}
}