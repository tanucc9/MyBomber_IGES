package integrazione.control;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestIntegrazioneRichiesteEventi.class, 
	TestIntegrazioneCronologiaEventi.class,TestIntegrazioneRegistrazione.class,
	TestIntegrazioneStruttura.class,TestIntegrazioneLogin.class, 
	TestIntegrazionePartecipaEventi.class,TestIntegrazioneRecensione.class, 
	TestIntegrazioneCreaEvento.class, TestIntegrazioneEventiRecenti.class  
})

public class AllTest {

}
