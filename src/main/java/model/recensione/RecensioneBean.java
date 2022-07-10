package model.recensione;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class RecensioneBean.
 */
public class RecensioneBean implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The recensore. */
  private String recensore;

  /** The recensito. */
  private String recensito;

  /** The recensione. */
  private float recensione;

  /** The evento. */
  private String nomeEvento;

  /** The descrizione. */
  String descrizione;

  /**
   * Sets the recensione.
   *
   * @param recensione the new recensione
   */
  public void setRecensione(float recensione) {
    this.recensione = recensione;
  }

  /**
   * Sets the recensore.
   *
   * @param recensore the new recensore
   */
  public void setRecensore(String recensore) {
    this.recensore = recensore;
  }

  /**
   * Sets the recensito.
   *
   * @param recensito the new recensito
   */
  public void setRecensito(String recensito) {
    this.recensito = recensito;
  }

  /**
   * Sets the evento.
   *
   * @param nomeEvento the new evento
   */
  public void setNomeEvento(String nomeEvento) {
    this.nomeEvento = nomeEvento;
  }

  /**
   * Sets the descrizione.
   *
   * @param descrizione the new descrizione
   */
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   * Gets the recensione.
   *
   * @return the recensione
   */
  public float getRecensione() {
    return recensione;
  }

  /**
   * Gets the recensore.
   *
   * @return the recensore
   */
  public String getRecensore() {
    return recensore;
  }

  /**
   * Gets the recensito.
   *
   * @return the recensito
   */
  public String getRecensito() {
    return recensito;
  }

  /**
   * Gets the evento.
   *
   * @return the evento
   */
  public String getNomeEvento() {
    return nomeEvento;
  }

  /**
   * Gets the descrizione.
   *
   * @return the descrizione
   */
  public String getDescrizione() {
    return descrizione;
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "recensore " + recensore + " recensito " + recensito + " nomeEvento " + nomeEvento;
  }
}