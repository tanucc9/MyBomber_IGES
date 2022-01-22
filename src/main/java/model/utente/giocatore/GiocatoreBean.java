package model.utente.giocatore;

import java.io.Serializable;
import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class GiocatoreBean.
 */
public class GiocatoreBean implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The username. */
  private String username;

  /** The email. */
  private String email;

  /** The nome. */
  private String nome;

  /** The cognome. */
  private String cognome;

  /** The password. */
  private String password;

  /** The telefono. */
  private String telefono;

  /** The nazione residenza. */
  private String nazioneResidenza;

  /** The provincia residenza. */
  private String provinciaResidenza;

  /** The citta residenza. */
  private String cittaResidenza;

  /** The cap residenza. */
  private String capResidenza;

  /** The data nascita. */
  private Date dataNascita;

  /** The valutazione. */
  private float valutazione;

  /**
   * Sets the username.
   *
   * @param username the new username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Sets the email.
   *
   * @param email the new email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the nome.
   *
   * @param nome the new nome
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Sets the cognome.
   *
   * @param cognome the new cognome
   */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Sets the password.
   *
   * @param password the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Sets the telefono.
   *
   * @param telefono the new telefono
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   * Sets the nazione residenza.
   *
   * @param nazione the new nazione residenza
   */
  public void setNazioneResidenza(String nazione) {
    this.nazioneResidenza = nazione;
  }

  /**
   * Sets the provincia residenza.
   *
   * @param provincia the new provincia residenza
   */
  public void setProvinciaResidenza(String provincia) {
    this.provinciaResidenza = provincia;
  }

  /**
   * Sets the citta residenza.
   *
   * @param citta the new citta residenza
   */
  public void setCittaResidenza(String citta) {
    this.cittaResidenza = citta;
  }

  /**
   * Sets the cap residenza.
   *
   * @param cap the new cap residenza
   */
  public void setCapResidenza(String cap) {
    this.capResidenza = cap;
  }

  /**
   * Sets the valutazione.
   *
   * @param valutazione the new valutazione
   */
  public void setValutazione(float valutazione) {
    this.valutazione = valutazione;
  }

  /**
   * Sets the data nascita.
   *
   * @param data the new data nascita
   */
  public void setDataNascita(Date data) {
    this.dataNascita = data;
  }

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the nome.
   *
   * @return the nome
   */
  public String getNome() {
    return nome;
  }

  /**
   * Gets the cognome.
   *
   * @return the cognome
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Gets the telefono.
   *
   * @return the telefono
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Gets the nazione residenza.
   *
   * @return the nazione residenza
   */
  public String getNazioneResidenza() {
    return nazioneResidenza;
  }

  /**
   * Gets the provincia residenza.
   *
   * @return the provincia residenza
   */
  public String getProvinciaResidenza() {
    return provinciaResidenza;
  }

  /**
   * Gets the citta residenza.
   *
   * @return the citta residenza
   */
  public String getCittaResidenza() {
    return cittaResidenza;
  }

  /**
   * Gets the cap residenza.
   *
   * @return the cap residenza
   */
  public String getCapResidenza() {
    return capResidenza;
  }

  /**
   * Gets the data nascita.
   *
   * @return the data nascita
   */
  public Date getDataNascita() {
    return dataNascita;
  }

  /**
   * Gets the valutazione.
   *
   * @return the valutazione
   */
  public float getValutazione() {
    return valutazione;
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "username " + username + " email " + email + " nome " + nome + " cognome " + cognome
        + " password " + password + " nazioneResidenza " + nazioneResidenza + " cittaResidenza "
        + cittaResidenza + " provinciaResidenza " + provinciaResidenza + " capResidenza "
        + capResidenza + " dataNascita " + dataNascita + " valutazione " + valutazione;
  }
}