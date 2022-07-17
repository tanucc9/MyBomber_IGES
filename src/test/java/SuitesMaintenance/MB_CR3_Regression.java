package SuitesMaintenance;

import integrazione.control.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import unita.control.TestCreaEventoServlet;
import unita.control.TestPartecipaEventiServlet;
import unita.control.TestRecensioneServlet;
import unita.control.TestRichiesteEventiServlet;
import unita.model.TestEventoDAO;
import unita.model.TestPartecipazioneDAO;
import unita.model.TestPartecipazioneSquadraDAO;
import unita.model.TestRecensioneDAO;

@RunWith(Suite.class)
@SuiteClasses({
		TestCreaEventoServlet.class,
		TestPartecipaEventiServlet.class,
		TestRecensioneServlet.class,
		TestRichiesteEventiServlet.class,
		TestEventoDAO.class,
		TestPartecipazioneDAO.class,
		TestPartecipazioneSquadraDAO.class,
		TestRecensioneDAO.class,
		TestIntegrazioneCreaEvento.class,
		TestIntegrazionePartecipaEventi.class,
		TestIntegrazioneRecensione.class,
		TestIntegrazioneRichiesteEventi.class,
})

public class MB_CR3_Regression {
}
