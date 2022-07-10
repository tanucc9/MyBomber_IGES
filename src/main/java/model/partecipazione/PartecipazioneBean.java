package model.partecipazione;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class PartecipazioneBean.
 */
public class PartecipazioneBean implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The utente. */
  private String utente;

  /** The evento. */
  private String nomeEvento;

  /**
   * Sets the utente.
   *
   * @param utente the new utente
   */
  public void setUtente(String utente) {
    this.utente = utente;
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
   * Gets the utente.
   *
   * @return the utente
   */
  public String getUtente() {
    return utente;
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
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "utente " + utente + " nomeEvento " + nomeEvento;
  }
}