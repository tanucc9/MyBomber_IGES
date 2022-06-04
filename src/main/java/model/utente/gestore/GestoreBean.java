package model.utente.gestore;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class GestoreBean.
 */
public class GestoreBean implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The email. */
  private String email;

  /** The nome. */
  private String nome;

  /** The cognome. */
  private String cognome;

  /** The encrypted password. */
  private String encPassword;

  /** The telefono. */
  private String telefono;

  /** The struttura. */
  private String struttura;

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
   * @param encPassword an encoded password
   */
  public void setEncPassword(String encPassword) {
    this.encPassword = encPassword;
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
   * Sets the struttura.
   *
   * @param struttura the new struttura
   */
  public void setStruttura(String struttura) {
    this.struttura = struttura;
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
  public String getEncPassword() {
    return encPassword;
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
   * Gets the struttura.
   *
   * @return the struttura
   */
  public String getStruttura() {
    return struttura;
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return " email " + email + " nome " + nome + " cognome " + cognome + " encPassword " + encPassword
        + " telefono " + telefono + " struttura " + struttura;
  }
}