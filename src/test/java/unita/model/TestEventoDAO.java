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
    tester.doSave(bean);
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
    tester.doDelete(bean.getNome());
  }

  /**
   * Test do retrieve by key.
   */
  @Test
  public void testDoRetrieveByKey() {
    assertEquals(bean.toString(), tester.doRetrieveByKey(bean.getNome()).toString());
  }

  /**
   * Test do save.
   */
  @Test
  public void testDoSave() {
    EventoBean inserito = tester.doRetrieveByKey(bean.getNome());
    assertEquals(bean.toString(), inserito.toString());
  }

  /**
   * Test do delete.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoDelete() throws SQLException {
    tester.doDelete(bean.getNome());
    assertEquals(null, tester.doRetrieveByKey(bean.getNome()));
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
    EventoBean mod = tester.doRetrieveByKey(bean.getNome());
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

    EventoBean g2 = new EventoBean();
    EventoBean g3 = new EventoBean();
    EventoBean g4 = new EventoBean();

    g2.setNome("Evento 333");
    g2.setDescrizione("Prova descrizione");
    g2.setStruttura("playk");
    g2.setData(Date.valueOf("2022-01-16"));
    g2.setOra(22);
    g2.setGestore("gino@gino.it");
    g2.setOrganizzatore("simone@simone.it");
    g2.setStato("richiesta");
    g2.setValutazione(0);
    g2.setNumPartecipanti(0);

    list.add(g2.toString());
    list.add(bean.toString());

    g4.setNome("evento2");
    g4.setDescrizione("grande evento");
    g4.setStruttura("playk");
    g4.setData(Date.valueOf("2022-01-03"));
    g4.setOra(2);
    g4.setGestore("gino@gino.it");
    g4.setOrganizzatore("simone@simone.it");
    g4.setStato("completato");
    g4.setValutazione(0);
    g4.setNumPartecipanti(10);

    g3.setNome("evento3");
    g3.setDescrizione("sdfghgfds");
    g3.setStruttura("playk");
    g3.setData(Date.valueOf("2022-01-15"));
    g3.setOra(1);
    g3.setGestore("gino@gino.it");
    g3.setOrganizzatore("simone@simone.it");
    g3.setStato("attivo");
    g3.setValutazione(0);
    g3.setNumPartecipanti(3);

    list.add(g4.toString());

    list.add(g3.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveAll();
    for (EventoBean p : eventi) {
      list2.add(p.toString());
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
    EventoBean g3 = new EventoBean();
    g3.setNome("evento3");
    g3.setDescrizione("sdfghgfds");
    g3.setStruttura("playk");
    g3.setData(Date.valueOf("2022-01-15"));
    g3.setOra(1);
    g3.setGestore("gino@gino.it");
    g3.setOrganizzatore("simone@simone.it");
    g3.setStato("attivo");
    g3.setValutazione(0);
    g3.setNumPartecipanti(3);
    list.add(g3.toString());

    ArrayList<String> list2 = new ArrayList<>();
    /*ArrayList<EventoBean> eventi = tester.doRetrieveEventiRecenti(g3.getOrganizzatore());
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);*/

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

    EventoBean g3 = new EventoBean();
    g3.setNome("evento3");
    g3.setDescrizione("sdfghgfds");
    g3.setStruttura("playk");
    g3.setData(Date.valueOf("2022-01-15"));
    g3.setOra(1);
    g3.setGestore("gino@gino.it");
    g3.setOrganizzatore("simone@simone.it");
    g3.setStato("attivo");
    g3.setValutazione(0);
    g3.setNumPartecipanti(3);

    list.add(g3.toString());

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

    EventoBean g2 = new EventoBean();
    g2.setNome("Evento 333");
    g2.setDescrizione("Prova descrizione");
    g2.setStruttura("playk");
    g2.setData(Date.valueOf("2022-01-16"));
    g2.setOra(22);
    g2.setGestore("gino@gino.it");
    g2.setOrganizzatore("simone@simone.it");
    g2.setStato("richiesta");
    g2.setValutazione(0);
    g2.setNumPartecipanti(0);

    list.add(g2.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveRichieste(g2.getGestore());
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

    EventoBean g3 = new EventoBean();
    EventoBean g4 = new EventoBean();

    list.add(bean.toString());

    g4.setNome("evento2");
    g4.setDescrizione("grande evento");
    g4.setStruttura("playk");
    g4.setData(Date.valueOf("2022-01-03"));
    g4.setOra(2);
    g4.setGestore("gino@gino.it");
    g4.setOrganizzatore("simone@simone.it");
    g4.setStato("completato");
    g4.setValutazione(0);
    g4.setNumPartecipanti(10);

    g3.setNome("evento3");
    g3.setDescrizione("sdfghgfds");
    g3.setStruttura("playk");
    g3.setData(Date.valueOf("2022-01-15"));
    g3.setOra(1);
    g3.setGestore("gino@gino.it");
    g3.setOrganizzatore("simone@simone.it");
    g3.setStato("attivo");
    g3.setValutazione(0);
    g3.setNumPartecipanti(3);

    list.add(g3.toString());

    list.add(g4.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveEventiGestore(g3.getGestore());
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

    EventoBean g4 = new EventoBean();

    g4.setNome("evento2");
    g4.setDescrizione("grande evento");
    g4.setStruttura("playk");
    g4.setData(Date.valueOf("2022-01-03"));
    g4.setOra(2);
    g4.setGestore("gino@gino.it");
    g4.setOrganizzatore("simone@simone.it");
    g4.setStato("completato");
    g4.setValutazione(0);
    g4.setNumPartecipanti(10);

    list.add(bean.toString());
    list.add(g4.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<EventoBean> eventi = tester.doRetrieveCronologia(g4.getOrganizzatore());
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

    EventoBean g3 = new EventoBean();

    g3.setNome("evento3");
    g3.setDescrizione("sdfghgfds");
    g3.setStruttura("playk");
    g3.setData(Date.valueOf("2022-01-15"));
    g3.setOra(1);
    g3.setGestore("gino@gino.it");
    g3.setOrganizzatore("simone@simone.it");
    g3.setStato("attivo");
    g3.setValutazione(0);
    g3.setNumPartecipanti(3);
    list.add(g3.toString());
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

    EventoBean g3 = new EventoBean();
    EventoBean g4 = new EventoBean();

    g3.setNome("evento3");
    g3.setDescrizione("sdfghgfds");
    g3.setStruttura("playk");
    g3.setData(Date.valueOf("2022-01-15"));
    g3.setOra(1);
    g3.setGestore("gino@gino.it");
    g3.setOrganizzatore("simone@simone.it");
    g3.setStato("attivo");
    g3.setValutazione(0);
    g3.setNumPartecipanti(3);

    list.add(g3.toString());

    ArrayList<String> list2 = new ArrayList<>();
    /*ArrayList<EventoBean> eventi = tester.doRetrieveEventiRecenti(g3.getOrganizzatore());
    for (EventoBean p : eventi) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);*/

  }

}
