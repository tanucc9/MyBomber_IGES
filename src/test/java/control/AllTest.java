package control;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ /*TestLogin.class, TestAreaUtenteServlet.class, TestCronologiaEventiServlet.class,*/ 
	TestRegistrazioneServlet.class, TestCronologiaEventiServlet.class, TestStrutturaServlet.class, 
	TestLogout.class, TestAreaUtenteServlet.class
})

public class AllTest {

}
