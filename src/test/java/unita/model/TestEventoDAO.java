package unita.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import junit.framework.TestCase;
import model.evento.EventoBean;
import model.evento.EventoDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class TestEventoDAO.
 */
public class TestEventoDAO extends TestCase {

  /** The bean. */
  private EventoBean bean;

  /** The tester. */
  private EventoDAO tester = new EventoDAO();

  private EventoBean evento;
  private EventoBean evento2;
  private EventoBean evento3;
  private EventoBean evento4;
  private EventoBean evento5;


  /**
   * Sets the up.
   *
   * @throws Exception the exception
   */
  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    bean = new EventoBean();
    bean.setCode("evento12");
    bean.setNome("evento12");
    bean.setDescrizione("mitico evento");
    bean.setStruttura("playk");
    bean.setData(Date.valueOf("2023-01-03"));
    bean.setOra(2);
    bean.setGestore("gino@gino.it");
    bean.setOrganizzatore("simone@simone.it");
    bean.setStato("completato");
    bean.setValutazione(0);
    bean.setNumPartecipanti(10);
    bean.setTipologia("libero");
    tester.doSave(bean);

    evento = new EventoBean();
    evento2 = new EventoBean();
    evento3 = new EventoBean();
    evento4 = new EventoBean();
    evento5 = new EventoBean();

    evento.setCode("evento-333");
    evento.setNome("Evento 333");
    evento.setDescrizione("Prova descrizione");
    evento.setStruttura("playk");
    evento.setData(Date.valueOf("2022-01-16"));
    evento.setOra(22);
    evento.setGestore("gino@gino.it");
    evento.setOrganizzatore("simone@simone.it");
    evento.setStato("richiesta");
    evento.setValutazione(0);
    evento.setNumPartecipanti(0);
    evento.setTipologia("libero");

    evento4.setCode("evento-squadra");
    evento4.setNome("evento squadra");
    evento4.setDescrizione("descrizione.");
    evento4.setStruttura("playk");
    evento4.setData(Date.valueOf("2022-01-15"));
    evento4.setOra(22);
    evento4.setGestore("gino@gino.it");
    evento4.setOrganizzatore("gio4@email.it");
    evento4.setStato("completato");
    evento4.setValutazione(0);
    evento4.setNumPartecipanti(2);
    evento4.setTipologia("squadra");

    evento5.setCode("evento-squadra-2");
    evento5.setNome("evento squadra 2");
    evento5.setDescrizione("descrizione.");
    evento5.setStruttura("playk");
    evento5.setData(Date.valueOf("2022-01-15"));
    evento5.setOra(22);
    evento5.setGestore("gino@gino.it");
    evento5.setOrganizzatore("gio4@email.it");
    evento5.setStato("completato");
    evento5.setValutazione(0);
    evento5.setNumPartecipanti(1);
    evento5.setTipologia("squadra");

    evento2.setCode("evento2");
    evento2.setNome("evento2");
    evento2.setDescrizione("grande evento");
    evento2.setStruttura("playk");
    evento2.setData(Date.valueOf("2022-01-03"));
    evento2.setOra(2);
    evento2.setGestore("gino@gino.it");
    evento2.setOrganizzatore("simone@simone.it");
    evento2.setStato("completato");
    evento2.setValutazione(0);
    evento2.setNumPartecipanti(10);
    evento2.setTipologia("libero");

    evento3.setCode("evento3");
    evento3.setNome("evento3");
    evento3.setDescrizione("sdfghgfds");
    evento3.setStruttura("playk");
    evento3.setData(Date.valueOf("2022-01-15"));
    evento3.setOra(1);
    evento3.setGestore("gino@gino.it");
    evento3.setOrganizzatore("simone@simone.it");
    evento3.setStato("attivo");
    evento3.setValutazione(0);
    evento3.setNumPartecipanti(3);
    evento3.setTipologia("libero");
  }

  /**
   * Tear down.
   *
   * @throws Exception the exception
   */
  @Override
  @After
  public void tearDown() throws Exception {
    super.tearDown();
    tester.doDelete(bean.getCode());
  }

  /**
   * Test do retrieve by key.
   */
  @Test
  public void testDoRetrieveByKey() {
    assertEquals(bean.toString(), tester.doRetrieveByKey(bean.getCode()).toString());
  }

  /**
   * Test do save.
   */
  @Test
  public void testDoSave() {
    EventoBean inserito = tester.doRetrieveByKey(bean.getCode());
    assertEquals(bean.toString(), inserito.toString());
  }

  /**
   * Test do delete.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoDelete() throws SQLException {
    tester.doDelete(bean.getCode());
    assertEquals(null, tester.doRetrieveByKey(bean.getCode()));
  }

  /**
   * Test do update.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoUpdate() throws SQLException {
    bean.setDescrizione("bellissimo");
    tester.doUpdate(bean);
    EventoBean mod = tester.doRetrieveByKey(bean.getCode());
    assertEquals(bean.toString(), mod.toString());
  }

  /**
   * Test do retrieve all.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveAll() throws SQLException {

    ArrayList<String> list = new ArrayList<>();

    list.add(evento.toString());
    list.add(evento4.toString());
    list.add(evento5.toString());
    list.add(bean.toString());
    list.add(evento2.toString());
    list.add(evento3.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveAll();
    for (EventoBean e : eventi) {
      list2.add(e.toString());
    }

    assertEquals(list, list2);
  }

  /**
   * Test do retrieve eventi recenti.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveEventiRecenti() throws SQLException {
    // nel database simone deve aver partecipato all'evento2
    ArrayList<String> list = new ArrayList<>();

    list.add(evento3.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveEventiRecenti(evento3.getOrganizzatore());
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);

  }

  /**
   * Test do retrieve eventi attivi.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveEventiAttivi() throws SQLException {
    // nel database simone deve aver partecipato all'evento2
    ArrayList<String> list = new ArrayList<>();

    list.add(evento3.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveEventiAttivi();
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);

  }

  /**
   * Test do retrieve richieste.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveRichieste() throws SQLException {
    // nel database simone deve aver partecipato all'evento2
    ArrayList<String> list = new ArrayList<>();

    list.add(evento.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveRichieste(evento.getGestore());
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);

  }

  /**
   * Test do retrieve eventi gestore.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveEventiGestore() throws SQLException {
    // nel database simone deve aver partecipato all'evento2
    ArrayList<String> list = new ArrayList<>();

    list.add(bean.toString());
    list.add(evento4.toString());
    list.add(evento5.toString());
    list.add(evento3.toString());
    list.add(evento2.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveEventiGestore(evento.getGestore());
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);

  }

  /**
   * Test do retrieve cronologia.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveCronologia() throws SQLException {

    ArrayList<String> list = new ArrayList<>();

    list.add(bean.toString());
    list.add(evento2.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveCronologia(evento.getOrganizzatore());
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);

  }

  /**
   * Test do retrieve eventi.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveEventi() throws SQLException {
    // piero deve esistere e non partecipare a evento3
    ArrayList<String> list = new ArrayList<>();

    list.add(evento3.toString());
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveEventi("piero@piero.it");
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);

  }

  /**
   * Test do retrieve eventi recenti giocatore.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveEventiRecentiGiocatore() throws SQLException {
    // simone deve aver partecipato solo a gli eventi già accettati evento3 e
    // evento2
    ArrayList<String> list = new ArrayList<>();

    list.add(evento3.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveEventiRecenti(evento3.getOrganizzatore());
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);

  }

  @Test
  public void testDoRetrieveEventiRecentiSquadra() throws SQLException {
    ArrayList<String> list = new ArrayList<>();
    list.add(evento4.toString());
    list.add(evento5.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveEventiRecentiSquadra(2);
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);

  }

}
