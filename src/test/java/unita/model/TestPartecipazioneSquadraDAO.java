package unita.model;

import java.sql.SQLException;
import junit.framework.TestCase;
import model.partecipazione.PartecipazioneSquadraBean;
import model.partecipazione.PartecipazioneSquadraDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestPartecipazioneSquadraDAO extends TestCase {

    /** The tester. */
    private PartecipazioneSquadraDAO tester;
    private PartecipazioneSquadraBean ps;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        tester = new PartecipazioneSquadraDAO();
        ps = new PartecipazioneSquadraBean();
        ps.setIdSquadra(3);
        ps.setIdEvento("evento-squadra-2");
        tester.doSave(ps);
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
        tester.doDelete(ps);
    }

    /**
     * Test do retrieve by key.
     */
    @Test
    public void testDoRetrieveByKey() throws SQLException {
        assertEquals(ps.toString(), tester.doRetrieveByKey(ps).toString());
    }

    /**
     * Test do save.
     */
    @Test
    public void testDoSave() throws SQLException {
        PartecipazioneSquadraBean ins = tester.doRetrieveByKey(ps);
        assertEquals(ps.toString(), ins.toString());
    }

    /**
     * Test do delete.
     *
     * @throws SQLException the SQL exception
     */
    @Test
    public void testDoDelete() throws SQLException {
        tester.doDelete(ps);
        assertEquals(null, tester.doRetrieveByKey(ps));
    }

}
