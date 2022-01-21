
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import integrazione.control.AllTestIntegrazioneControl;
import unita.control.AllTestUnitaControl;
import unita.model.AllTestUnitaModel;

@RunWith(Suite.class)
@SuiteClasses({ AllTestIntegrazioneControl.class, AllTestUnitaControl.class, AllTestUnitaModel.class 
	
})

public class AllTest {

}
