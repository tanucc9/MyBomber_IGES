package unita.model;

import java.sql.SQLException;
import java.util.ArrayList;
import junit.framework.TestCase;
import model.squadra.LogoSquadraBean;
import model.squadra.LogoSquadraDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLogoSquadraDAO extends TestCase {

    /** The tester. */
    private LogoSquadraDAO tester;
    private LogoSquadraBean logo;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        tester = new LogoSquadraDAO();
        logo = new LogoSquadraBean();
        logo.setNome("logo_test");
        logo.setUrl("img/logo_squadra/logo_test.jpg");
        tester.doSave(logo);

        // get the id
        logo.setIdLogoSquadra(tester.doRetrieveId().getIdLogoSquadra());
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
        tester.doDelete(logo.getIdLogoSquadra());
    }

    /**
     * Test do retrieve by key.
     */
    @Test
    public void testDoRetrieveByKey() throws SQLException {
        assertEquals(logo.toString(), tester.doRetrieveByKey(logo.getIdLogoSquadra()).toString());
    }

    /**
     * Test do save.
     */
    @Test
    public void testDoSave() throws SQLException {
        LogoSquadraBean ins = tester.doRetrieveByKey(logo.getIdLogoSquadra());
        assertEquals(logo.toString(), ins.toString());
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

        LogoSquadraBean l1 = new LogoSquadraBean();
        LogoSquadraBean l2 = new LogoSquadraBean();

        l1.setIdLogoSquadra(2);
        l1.setNome("logo_club1");
        l1.setUrl("img/logo_squadra/logo_club1.jpg");

        l2.setIdLogoSquadra(3);
        l2.setNome("logo_club2");
        l2.setUrl("img/logo_squadra/logo_club2.jpg");

        expected.add(l1.toString());
        expected.add(l2.toString());
        expected.add(logo.toString());

        ArrayList<LogoSquadraBean> res = tester.doRetrieveAll();
        for (LogoSquadraBean l : res)
            actual.add(l.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testDoRetrieveId() throws SQLException {
        assertEquals(logo.toString(), tester.doRetrieveId().toString());
    }


    /**
     * Test do delete.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testDoDelete() throws SQLException {
        tester.doDelete(logo.getIdLogoSquadra());
        assertEquals(null, tester.doRetrieveByKey(logo.getIdLogoSquadra()));
    }

}
