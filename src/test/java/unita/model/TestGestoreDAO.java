package unita.model;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import junit.framework.TestCase;
import model.utente.gestore.GestoreBean;
import model.utente.gestore.GestoreDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.HashTool;

// TODO: Auto-generated Javadoc
/**
 * The Class TestGestoreDAO.
 */
public class TestGestoreDAO extends TestCase {

  /** Utility tool for hashing. */
  private HashTool hashTool;

  /** The bean. */
  private GestoreBean bean;

  private String password;

  /** The tester. */
  private GestoreDAO tester = new GestoreDAO();

  /**
   * Sets the up.
   *
   * @throws Exception the exception
   */
  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    password = "gaetano";
    hashTool = new HashTool();
    bean = new GestoreBean();
    bean.setEmail("zgaetano@olio.it");
    bean.setNome("gaetano");
    bean.setCognome("rossi");
    bean.setEncPassword(hashTool.hashSHA256(password));
    bean.setTelefono("3923415443");
    bean.setStruttura("playo");

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
    tester.doDelete(bean.getEmail());
  }

  /**
   * Test do retrieve by key.
   */
  @Test
  public void testDoRetrieveByKey() {
    assertEquals(bean.toString(), tester.doRetrieveByKey(bean.getEmail()).toString());
  }

  /**
   * Test do retrieve by struttura.
   */
  @Test
  public void testDoRetrieveByStruttura() {
    GestoreBean gi = new GestoreBean();
    gi.setEmail("gino@gino.it");
    gi.setNome("gino");
    gi.setCognome("pozzo");
    gi.setEncPassword(hashTool.hashSHA256("gino"));
    gi.setTelefono("3923415443");
    gi.setStruttura("playk");
    assertEquals(gi.toString(), tester.doRetrieveByStruttura(gi.getStruttura()).toString());
  }

  /**
   * Test do save.
   */
  @Test
  public void testDoSave() {
    GestoreBean inserito = tester.doRetrieveByKey(bean.getEmail());
    assertEquals(bean.toString(), inserito.toString());
  }

  /**
   * Test do delete.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoDelete() throws SQLException {
    tester.doDelete(bean.getEmail());
    assertEquals(null, tester.doRetrieveByKey(bean.getEmail()));
  }

  /**
   * Test do update.
   *
   * @throws SQLException the SQL exception
   */
  @Test
  public void testDoUpdate() throws SQLException, NoSuchAlgorithmException {
    bean.setNome("Pasquale");
    tester.doUpdate(bean);
    GestoreBean mod = tester.doRetrieveByKey(bean.getEmail());
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
    GestoreBean g2 = new GestoreBean();
    GestoreBean g3 = new GestoreBean();
    GestoreBean g4 = new GestoreBean();
    g2.setEmail("gino@gino.it");
    g2.setNome("gino");
    g2.setCognome("pozzo");
    g2.setEncPassword(hashTool.hashSHA256("gino"));
    g2.setTelefono("3923415443");
    g2.setStruttura("playk");

    g3.setEmail("bario@bario.it");
    g3.setNome("piero");
    g3.setCognome("rossi");
    g3.setEncPassword(hashTool.hashSHA256("bario"));
    g3.setTelefono("3223415443");
    g3.setStruttura("playb");

    list.add(g3.toString());
    list.add(g2.toString());

    g4.setEmail("olio@olio.it");
    g4.setNome("olio");
    g4.setCognome("rossi");
    g4.setEncPassword(hashTool.hashSHA256("olio"));
    g4.setTelefono("3923415443");
    g4.setStruttura("playo");

    list.add(g4.toString());

    list.add(bean.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<GestoreBean> gestori = tester.doRetrieveAll();
    for (GestoreBean p : gestori) {
      list2.add(p.toString());
    }

    assertEquals(list, list2);

  }

  @Test
  public void testDoRetrieveByAuth() throws NoSuchAlgorithmException {
    assertEquals(bean.toString(), tester.doRetrieveByAuth(bean.getEmail(), password).toString());
  }
}
