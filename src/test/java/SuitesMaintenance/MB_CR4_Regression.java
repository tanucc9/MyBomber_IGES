package SuitesMaintenance;

import integrazione.control.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import unita.control.*;
import unita.model.TestEventoDAO;

@RunWith(Suite.class)
@SuiteClasses({
        TestAreaUtenteServlet.class,
        TestCreaEventoServlet.class,
        TestEventiRecentiServlet.class,
        TestPartecipaEventiServlet.class,
        TestRichiesteEventiServlet.class,
        TestEventoDAO.class,
        TestIntegrazionePartecipaEventi.class,
        TestIntegrazioneCreaEvento.class,
        TestIntegrazioneRichiesteEventi.class,
        TestIntegrazioneEventiRecenti.class
})

public class MB_CR4_Regression {}
