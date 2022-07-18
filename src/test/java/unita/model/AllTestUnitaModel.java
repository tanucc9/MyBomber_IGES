package unita.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * The Class AllTestUnitaModel.
 */
@RunWith(Suite.class)
@SuiteClasses({ TestRecensioneDAO.class, TestEventoDAO.class, TestPartecipazioneDAO.class,
    TestGiocatoreDAO.class, TestGestoreDAO.class, TestStrutturaDAO.class, TestEventoBean.class,
    TestLogoSquadraDAO.class, TestPartecipazioneSquadraDAO.class, TestSquadraDAO.class
})

public class AllTestUnitaModel {}
