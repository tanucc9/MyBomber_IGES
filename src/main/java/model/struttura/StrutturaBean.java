package model.struttura;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class StrutturaBean.
 */
public class StrutturaBean implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The nome. */
  private String nome;

  /** The indirizzo. */
  private String indirizzo;

  /** The nazione. */
  private String nazione;

  /** The citta. */
  private String citta;

  /** The cap. */
  private String cap;

  /** The provincia. */
  private String provincia;

  /** The telefono. */
  private String telefono;

  /**
   * Sets the nome.
   *
   * @param nome the new nome
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Sets the indirizzo.
   *
   * @param indirizzo the new indirizzo
   */
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  /**
   * Sets the nazione.
   *
   * @param nazione the new nazione
   */
  public void setNazione(String nazione) {
    this.nazione = nazione;
  }

  /**
   * Sets the citta.
   *
   * @param citta the new citta
   */
  public void setCitta(String citta) {
    this.citta = citta;
  }

  /**
   * Sets the cap.
   *
   * @param cap the new cap
   */
  public void setCap(String cap) {
    this.cap = cap;
  }

  /**
   * Sets the provincia.
   *
   * @param provincia the new provincia
   */
  public void setProvincia(String provincia) {
    this.provincia = provincia;
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
   * Gets the nome.
   *
   * @return the nome
   */
  public String getNome() {
    return nome;
  }

  /**
   * Gets the indirizzo.
   *
   * @return the indirizzo
   */
  public String getIndirizzo() {
    return indirizzo;
  }

  /**
   * Gets the citta.
   *
   * @return the citta
   */
  public String getCitta() {
    return citta;
  }

  /**
   * Gets the nazione.
   *
   * @return the nazione
   */
  public String getNazione() {
    return nazione;
  }

  /**
   * Gets the cap.
   *
   * @return the cap
   */
  public String getCap() {
    return cap;
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
   * Gets the provincia.
   *
   * @return the provincia
   */
  public String getProvincia() {
    return provincia;
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "nome " + nome + " nazione " + nazione + " citta " + citta + " cap " + cap
        + " provincia " + provincia + " indirizzo " + indirizzo + " telefono " + telefono;
  }
}