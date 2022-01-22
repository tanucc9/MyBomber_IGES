
import integrazione.control.AllTestIntegrazioneControl;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import unita.control.AllTestUnitaControl;
import unita.model.AllTestUnitaModel;

/**
 * The Class AllTest.
 */
@RunWith(Suite.class)
@SuiteClasses({ AllTestIntegrazioneControl.class, AllTestUnitaControl.class, AllTestUnitaModel.class

})

public class AllTest {

}
