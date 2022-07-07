package unita.model;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import junit.framework.TestCase;
import model.squadra.SquadraBean;
import model.squadra.SquadraDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSquadraDAO extends TestCase {

    /** The tester. */
    private SquadraDAO tester;
    private SquadraBean squadra;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        tester = new SquadraDAO();
        squadra = new SquadraBean();
        squadra.setNome("test");
        squadra.setNomeAbbreviato("tes");
        squadra.setCitta("Salerno");
        squadra.setDescrizione("Lorem ipsum lorem ipsum lorem ipsum lorem ipsum.");
        squadra.setLogo(2);
        squadra.setCapitano("simone@simone.it");
        tester.doSave(squadra);

        // get the id
        squadra.setIdSquadra(tester.doRetrieveByName(squadra.getNome()).getIdSquadra());
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
        tester.doDelete(squadra.getIdSquadra());
    }

    /**
     * Test do retrieve by key.
     */
    @Test
    public void testDoRetrieveByKey() {
        assertEquals(squadra.toString(), tester.doRetrieveByKey(squadra.getIdSquadra()).toString());
    }

    @Test
    public void testDoRetrieveByName() {
        assertEquals(squadra.toString(), tester.doRetrieveByName(squadra.getNome()).toString());
    }

    /**
     * Test do save.
     */
    @Test
    public void testDoSave() {
        SquadraBean ins = tester.doRetrieveByKey(squadra.getIdSquadra());
        assertEquals(squadra.toString(), ins.toString());
    }

    /**
     * Test do update.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testDoUpdate() throws SQLException, NoSuchAlgorithmException {
        squadra.setNome("test");
        tester.doUpdate(squadra);
        SquadraBean mod = tester.doRetrieveByKey(squadra.getIdSquadra());
        assertEquals(squadra.toString(), mod.toString());
    }

    /**
     * Test do retrieve all.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testDoRetrieveAll() throws SQLException {
        ArrayList<String> expected = new ArrayList<String>();
        ArrayList<String> actual = new ArrayList<String>();

        SquadraBean sq2 = new SquadraBean();
        SquadraBean sq3 = new SquadraBean();
        sq2.setIdSquadra(2);
        sq2.setNome("tigers");
        sq2.setNomeAbbreviato("tig");
        sq2.setCitta("Salerno");
        sq2.setDescrizione("Lorem ipsum lorem ipsum lorem ipsum lorem ipsum.");
        sq2.setLogo(2);
        sq2.setCapitano("gio4@email.it");

        sq3.setIdSquadra(3);
        sq3.setNome("Lyons");
        sq3.setNomeAbbreviato("lyo");
        sq3.setCitta("Napoli");
        sq3.setDescrizione("Lorem ipsum lorem ipsum lorem ipsum lorem ipsum.");
        sq3.setLogo(3);
        sq3.setCapitano("mario@mario.it");

        expected.add(sq2.toString());
        expected.add(sq3.toString());
        expected.add(squadra.toString());

        ArrayList<SquadraBean> res = tester.doRetrieveAll();
        for (SquadraBean sq : res)
            actual.add(sq.toString());

        assertEquals(expected, actual);
    }

    /**
     * Test do delete.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testDoDelete() throws SQLException {
        tester.doDelete(squadra.getIdSquadra());
        assertEquals(null, tester.doRetrieveByKey(squadra.getIdSquadra()));
    }

}
