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
  private String codeEvento;

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
   * @param codeEvento the new evento
   */
  public void setCodeEvento(String codeEvento) {
    this.codeEvento = codeEvento;
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
  public String getCodeEvento() {
    return codeEvento;
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "utente " + utente + " nomeEvento " + codeEvento;
  }
}