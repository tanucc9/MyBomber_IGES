package model.utente.giocatore;
import java.io.Serializable;
import java.sql.Date;

public class GiocatoreBean implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String username;
	private String email;
	private String nome;
	private String cognome;
	private String password;
	private String telefono;
	private String nazioneResidenza;
	private String provinciaResidenza;
	private String cittaResidenza;
	private String capResidenza;
	private Date dataNascita;
	private float valutazione;
    
	public void setUsername(String username) {
		this.username=username;
	}
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
	public void setNazioneResidenza(String nazione) {
		this.nazioneResidenza=nazione;
	}
	public void setProvinciaResidenza(String provincia) {
		this.provinciaResidenza=provincia;
	}
	public void setCittaResidenza(String citta) {
		this.cittaResidenza=citta;
	}
	public void setCapResidenza(String cap) {
		this.capResidenza=cap;
	}
	
	public void setValutazione(float valutazione) {
		this.valutazione=valutazione;
	}
	public void setDataNascita(Date data) {
		this.dataNascita=data;
	}
	public String getUsername()
	{
		return username;
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
	public String getNazioneResidenza()
	{
		return nazioneResidenza;
	}
	public String getProvinciaResidenza()
	{
		return provinciaResidenza;
	}
	public String getCittaResidenza()
	{
		return cittaResidenza;
	}
	public String getCapResidenza()
	{
		return capResidenza;
	}
	public Date getDataNascita()
	{
		return dataNascita;
	}
	public float getValutazione()
	{
		return valutazione;
	}
	@Override
	public String toString() {
		return "username " + username + " email " + email + " nome " + nome + 
				" cognome " + cognome + " password " + password + " nazioneResidenza " + nazioneResidenza + 
				" cittaResidenza " + cittaResidenza + " provinciaResidenza " + provinciaResidenza + " capResidenza " + capResidenza + 
				" dataNascita " + dataNascita +" valutazione " + valutazione;
	}
}