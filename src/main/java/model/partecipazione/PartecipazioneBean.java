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
  private String evento;

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
   * @param evento the new evento
   */
  public void setEvento(String evento) {
    this.evento = evento;
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
  public String getEvento() {
    return evento;
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "utente " + utente + " evento " + evento;
  }
}