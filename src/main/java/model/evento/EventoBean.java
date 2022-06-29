package model.evento;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

// TODO: Auto-generated Javadoc
/**
 * The Class EventoBean.
 */
public class EventoBean implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The nome. */
  private String nome;

  /** The descrizione. */
  private String descrizione;

  /** The struttura. */
  private String struttura;

  /** The data. */
  private Date data;

  /** The ora. */
  private int ora;

  /** The gestore. */
  private String gestore;

  /** The organizzatore. */
  private String organizzatore;

  /** The stato. */
  private String stato; // richiesta - attivo - completato - concluso

  /** The valutazione. */
  private float valutazione; // somma valutazioni giocatori

  /** The num partecipanti. */
  private int numPartecipanti;
  private String tipologia;

  /**
   * Sets the nome.
   *
   * @param nome the new nome
   */
  public void setNome(String nome) {
    this.nome = nome;
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
   * Sets the struttura.
   *
   * @param struttura the new struttura
   */
  public void setStruttura(String struttura) {
    this.struttura = struttura;
  }

  /**
   * Sets the data.
   *
   * @param data the new data
   */
  public void setData(Date data) {
    this.data = data;
  }

  /**
   * Sets the ora.
   *
   * @param ora the new ora
   */
  public void setOra(int ora) {
    this.ora = ora;
  }

  /**
   * Sets the gestore.
   *
   * @param gestore the new gestore
   */
  public void setGestore(String gestore) {
    this.gestore = gestore;

  }

  /**
   * Sets the organizzatore.
   *
   * @param organizzatore the new organizzatore
   */
  public void setOrganizzatore(String organizzatore) {
    this.organizzatore = organizzatore;
  }

  /**
   * Sets the stato.
   *
   * @param stato the new stato
   */
  public void setStato(String stato) {
    this.stato = stato;
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
   * Sets the num partecipanti.
   *
   * @param numPartecipanti the new num partecipanti
   */
  public void setNumPartecipanti(int numPartecipanti) {
    this.numPartecipanti = numPartecipanti;
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
   * Gets the descrizione.
   *
   * @return the descrizione
   */
  public String getDescrizione() {
    return descrizione;
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
   * Gets the data.
   *
   * @return the data
   */
  public Date getData() {
    return data;
  }

  /**
   * Gets the ora.
   *
   * @return the ora
   */
  public int getOra() {
    return ora;
  }

  /**
   * Gets the gestore.
   *
   * @return the gestore
   */
  public String getGestore() {
    return gestore;
  }

  /**
   * Gets the organizzatore.
   *
   * @return the organizzatore
   */
  public String getOrganizzatore() {
    return organizzatore;
  }

  /**
   * Gets the stato.
   *
   * @return the stato
   */
  public String getStato() {
    return stato;
  }

  /**
   * Gets the valutazione.
   *
   * @return the valutazione
   */
  public float getValutazione() {
    return valutazione;
  }

  public String getTipologia() {
    return tipologia;
  }

  public void setTipologia(String tipologia) {
    this.tipologia = tipologia;
  }

  /**
   * Gets the num partecipanti.
   *
   * @return the num partecipanti
   */
  public int getNumPartecipanti() {
    return numPartecipanti;
  }

  /**
   * Gets the media.
   *
   * @return the media
   */
  public float getMedia() {
    return valutazione / numPartecipanti;
  }

  /**
   * Aggiungi G.
   */
  public void aggiungiG() {
    numPartecipanti++;
  }

  /**
   * Rimuovi G.
   */
  public void rimuoviG() {
    numPartecipanti--;
  }

  /**
   * Checks if is finished.
   *
   * @return true, if is finished
   */
  public boolean isFinished() {
    Date now = new Date(System.currentTimeMillis());
    if (now.after(this.getData()) && this.getStato().equals("completato")) {
      return true;
    }
    return false;
  }

  /**
   * Precedenza.
   *
   * @param altro the altro
   * @return true, if successful
   */
  public boolean precedenza(EventoBean altro) {
    Date da = altro.getData();
    if (da.after(this.getData())) {
      return true;
    }
    return false;
  }

  /**
   * Ordina per data.
   *
   * @param eventi the eventi
   * @return the array list
   */
  public static ArrayList<EventoBean> ordinaPerData(ArrayList<EventoBean> eventi) {

    Collections.sort(eventi, new SortByDate());
    return eventi;

  }

  /**
   * Ordina per data R.
   *
   * @param eventi the eventi
   * @return the array list
   */
  public static ArrayList<EventoBean> ordinaPerDataR(ArrayList<EventoBean> eventi) {

    Collections.sort(eventi, new SortByDate().reversed());
    return eventi;

  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "EventoBean{" +
            "nome='" + nome + '\'' +
            ", descrizione='" + descrizione + '\'' +
            ", struttura='" + struttura + '\'' +
            ", data=" + data +
            ", ora=" + ora +
            ", gestore='" + gestore + '\'' +
            ", organizzatore='" + organizzatore + '\'' +
            ", stato='" + stato + '\'' +
            ", valutazione=" + valutazione +
            ", numPartecipanti=" + numPartecipanti +
            ", tipologia='" + tipologia + '\'' +
            '}';
  }
}
