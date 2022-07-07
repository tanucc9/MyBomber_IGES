package unita.model;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import junit.framework.TestCase;
import model.utente.giocatore.GiocatoreBean;
import model.utente.giocatore.GiocatoreDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.HashTool;

/**
 * The Class TestGiocatoreDAO.
 */
public class TestGiocatoreDAO extends TestCase {

  /** Utility tool for hashing. */
  private HashTool hashTool;

  /** The bean. */
  private GiocatoreBean bean;

  /** The tester. */
  private GiocatoreDAO tester = new GiocatoreDAO();

  String password;

  /**
   * Sets the up.
   *
   * @throws Exception the exception
   */
  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
	hashTool = new HashTool();
    password = "simone";
    bean = new GiocatoreBean();
    bean.setUsername("simone45");
    bean.setEmail("simon@simon.it");
    bean.setNome("Simone");
    bean.setCognome("Graziano");
    bean.setEncPassword(hashTool.hashSHA256("simone"));
    bean.setTelefono("3324561273");
    bean.setDataNascita(Date.valueOf("2000-05-09"));
    bean.setNazioneResidenza("Italia");
    bean.setProvinciaResidenza("Napoli");
    bean.setCittaResidenza("Napoli");
    bean.setCapResidenza("80000");
    bean.setValutazione(0);
    bean.setIdSquadra(2);
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
   * Test do retrieve by username.
   */
  @Test
  public void testDoRetrieveByUsername() {
    assertEquals(bean.toString(), tester.doRetrieveByUsername(bean.getUsername()).toString());
  }

  /**
   * Test do save.
   */
  @Test
  public void testDoSave() {
    GiocatoreBean inserito = tester.doRetrieveByKey(bean.getEmail());
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
    GiocatoreBean mod = tester.doRetrieveByKey(bean.getEmail());
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
    GiocatoreBean g2 = new GiocatoreBean();
    GiocatoreBean g3 = new GiocatoreBean();
    GiocatoreBean g4 = new GiocatoreBean();
    GiocatoreBean g5 = new GiocatoreBean();

    g2.setUsername("gio");
    g2.setEmail("gio4@email.it");
    g2.setNome("Giovanni");
    g2.setCognome("Falco");
    g2.setEncPassword(hashTool.hashSHA256("Gio"));
    g2.setTelefono("3334562167");
    g2.setDataNascita(Date.valueOf("2001-11-16"));
    g2.setNazioneResidenza("Italia");
    g2.setProvinciaResidenza("Caserta");
    g2.setCittaResidenza("Caserta");
    g2.setCapResidenza("89976");
    g2.setValutazione(0);
    g2.setIdSquadra(2);
    list.add(g2.toString());

    g3.setUsername("mario");
    g3.setEmail("mario@mario.it");
    g3.setNome("Mario");
    g3.setCognome("Calabrese");
    g3.setEncPassword(hashTool.hashSHA256("mario"));
    g3.setTelefono("3452167543");
    g3.setDataNascita(Date.valueOf("2000-03-03"));
    g3.setNazioneResidenza("Italia");
    g3.setProvinciaResidenza("Avellino");
    g3.setCittaResidenza("Avellino");
    g3.setCapResidenza("80076");
    g3.setValutazione(0);
    g3.setIdSquadra(3);
    list.add(g3.toString());

    g4.setUsername("pino");
    g4.setEmail("pino@pino.it");
    g4.setNome("Pino");
    g4.setCognome("Inglese");
    g4.setEncPassword(hashTool.hashSHA256("pino"));
    g4.setTelefono("3665423187");
    g4.setDataNascita(Date.valueOf("2000-09-09"));
    g4.setNazioneResidenza("Italia");
    g4.setProvinciaResidenza("Napoli");
    g4.setCittaResidenza("Napoli");
    g4.setCapResidenza("80000");
    g4.setValutazione(0);
    list.add(g4.toString());
    list.add(tester.doRetrieveByKey(bean.getEmail()).toString());

    g5.setUsername("simone");
    g5.setEmail("simone@simone.it");
    g5.setNome("Simone");
    g5.setCognome("Graziano");
    g5.setEncPassword(hashTool.hashSHA256("simone"));
    g5.setTelefono("3324156789");
    g5.setDataNascita(Date.valueOf("1999-08-12"));
    g5.setNazioneResidenza("Italia");
    g5.setProvinciaResidenza("Milano");
    g5.setCittaResidenza("Milano");
    g5.setCapResidenza("78654");
    g5.setValutazione(0);
    list.add(g5.toString());

    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<GiocatoreBean> giocatori = tester.doRetrieveAll();
    for (GiocatoreBean g : giocatori) {
      list2.add(g.toString());
    }

    assertEquals(list, list2);
  }

  @Test
  public void testDoRetrieveByAuth() throws NoSuchAlgorithmException {
    assertEquals(bean.toString(), tester.doRetrieveByAuth(bean.getEmail(), password).toString());
  }

  @Test
  public void testDoLeaveTeam() throws SQLException, NoSuchAlgorithmException {
    bean.setIdSquadra(0);
    tester.doLeaveTeam(bean);
    GiocatoreBean mod = tester.doRetrieveByKey(bean.getEmail());
    assertEquals(bean.toString(), mod.toString());
  }

  @Test
  public void testDoRetrieveBySquadra() throws SQLException {
    ArrayList<String> expected = new ArrayList<String>();
    ArrayList<String> actual = new ArrayList<String>();

    GiocatoreBean g = new GiocatoreBean();
    g.setUsername("gio");
    g.setEmail("gio4@email.it");
    g.setNome("Giovanni");
    g.setCognome("Falco");
    g.setEncPassword(hashTool.hashSHA256("Gio"));
    g.setTelefono("3334562167");
    g.setDataNascita(Date.valueOf("2001-11-16"));
    g.setNazioneResidenza("Italia");
    g.setProvinciaResidenza("Caserta");
    g.setCittaResidenza("Caserta");
    g.setCapResidenza("89976");
    g.setValutazione(0);
    g.setIdSquadra(2);

    expected.add(g.toString());
    expected.add(bean.toString());

    ArrayList<GiocatoreBean> res = tester.doRetrieveBySquadra(2);
    for (GiocatoreBean gio : res) {
      actual.add(gio.toString());
    }

    assertEquals(expected, actual);
  }

}
