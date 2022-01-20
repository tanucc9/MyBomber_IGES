package unita.control;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPartecipaEventiServlet.class, TestRecensioneServlet.class , TestAreaUtenteServlet.class, TestCronologiaEventiServlet.class, 
	TestRegistrazioneServlet.class, TestLoginServlet.class, TestStrutturaServlet.class, 
	TestLogoutServlet.class, TestRichiesteEventiServlet.class, TestEventiRecentiServlet.class,
	TestCreaEventoServlet.class
})

public class AllTest {

}
