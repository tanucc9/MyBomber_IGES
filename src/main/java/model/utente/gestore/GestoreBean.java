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

  /** The password. */
  private String password;

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
    return " email " + email + " nome " + nome + " cognome " + cognome + " password " + password
        + " telefono " + telefono + " struttura " + struttura;
  }
}