package unita.model;

import java.sql.SQLException;
import java.util.ArrayList;
import junit.framework.TestCase;
import model.recensione.RecensioneBean;
import model.recensione.RecensioneDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class TestRecensioneDAO.
 */
public class TestRecensioneDAO extends TestCase {

  /** The bean. */
  private RecensioneBean bean;

  /** The tester. */
  private RecensioneDAO tester = new RecensioneDAO();

  /**
   * Sets the up.
   *
   * @throws Exception the exception
   */
  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    bean = new RecensioneBean();
    bean.setRecensore("simone@simone.it");
    bean.setRecensito("pino@pino.it");
    bean.setNomeEvento("Evento 333");
    bean.setRecensione(4);
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
    tester.doDelete(bean.getRecensore(), bean.getRecensito(), bean.getNomeEvento());
  }

  /**
   * Test do retrieve by key.
   */
  @Test
  public void testDoRetrieveByKey() {
    assertEquals(bean.toString(), tester
        .doRetrieveByKey(bean.getRecensore(), bean.getRecensito(), bean.getNomeEvento()).toString());
  }

  /**
   * Test do save.
   */
  @Test
  public void testDoSave() {
    RecensioneBean inserito = tester.doRetrieveByKey(bean.getRecensore(), bean.getRecensito(),
        bean.getNomeEvento());
    assertEquals(bean.toString(), inserito.toString());
  }

  /**
   * Test do delete.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoDelete() throws SQLException {
    tester.doDelete(bean.getRecensore(), bean.getRecensito(), bean.getNomeEvento());
    assertEquals(null,
        tester.doRetrieveByKey(bean.getRecensore(), bean.getRecensito(), bean.getNomeEvento()));
  }

  /**
   * Test do update.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoUpdate() throws SQLException {
    bean.setDescrizione("bravo");
    tester.doUpdate(bean);
    RecensioneBean r2 = new RecensioneBean();
    r2 = tester.doRetrieveByKey(bean.getRecensore(), bean.getRecensito(), bean.getNomeEvento());
    assertEquals(bean.toString(), r2.toString());
  }

  /**
   * Test do retrieve all.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveAll() throws SQLException {
    ArrayList<String> list = new ArrayList<>();
    RecensioneBean r2 = new RecensioneBean();

    r2.setRecensore("pino@pino.it");
    r2.setRecensito("mario@mario.it");
    r2.setNomeEvento("evento3");
    r2.setRecensione(2);
    r2.setDescrizione("scarso");
    list.add(r2.toString());

    r2.setRecensore("pino@pino.it");
    r2.setRecensito("simone@simone.it");
    r2.setNomeEvento("evento3");
    r2.setRecensione(2);
    r2.setDescrizione("scarso");
    list.add(r2.toString());
    list.add(bean.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<RecensioneBean> recensioni = tester.doRetrieveAll();
    for (RecensioneBean r : recensioni) {
      list2.add(r.toString());
    }

    assertEquals(list, list2);
  }

  /**
   * Test do retrieve media.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveMedia() throws SQLException {
    float r1 = 2;
    String recensito = "simone@simone.it";

    float r2 = tester.doRetrieveMedia(recensito);

    assertEquals(r1, r2);
  }

  /**
   * Test do retrieve da recensire.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveDaRecensire() throws SQLException {
    ArrayList<String> list = new ArrayList<>();

    list.add("pino@pino.it");
    list.add("simone@simone.it");
    ArrayList<String> list2 = new ArrayList<>();
    list2 = tester.doRetrieveDaRecensire("mario@mario.it", "evento3");

    assertEquals(list, list2);
  }

  /**
   * Test do retrieve recensiti.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoRetrieveRecensiti() throws SQLException {
    ArrayList<String> list = new ArrayList<>();
    RecensioneBean r2 = new RecensioneBean();

    r2.setRecensore("pino@pino.it");
    r2.setRecensito("mario@mario.it");
    r2.setNomeEvento("evento3");
    r2.setRecensione(2);
    r2.setDescrizione("scarso");
    list.add(r2.toString());

    r2.setRecensore("pino@pino.it");
    r2.setRecensito("simone@simone.it");
    r2.setNomeEvento("evento3");
    r2.setRecensione(2);
    r2.setDescrizione("scarso");
    list.add(r2.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<RecensioneBean> recensioni = tester.doRetrieveRecensiti(r2.getRecensore(),
        r2.getNomeEvento());
    for (RecensioneBean r : recensioni) {
      list2.add(r.toString());
    }

    assertEquals(list, list2);
  }

}