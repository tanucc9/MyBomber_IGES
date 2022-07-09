package SuitesMaintenance;

import integrazione.control.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import unita.control.*;
import unita.model.TestEventoDAO;
import unita.model.TestGestoreDAO;
import unita.model.TestGiocatoreDAO;

@RunWith(Suite.class)
@SuiteClasses({
        TestLoginServlet.class, TestLogoutServlet.class, TestRegistrazioneServlet.class,
        TestGestoreDAO.class, TestGiocatoreDAO.class, TestIntegrazioneLogin.class,
        TestIntegrazioneRegistrazione.class
})

public class MB_CR1_Regression {}
