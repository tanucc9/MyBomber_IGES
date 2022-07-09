
import integrazione.control.AllTestIntegrazioneControl;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import unita.control.AllTestUnitaControl;
import unita.model.AllTestUnitaModel;
import unita.util.AllTestUnitaUtil;

/**
 * The Class AllTest.
 */
@RunWith(Suite.class)
@SuiteClasses({
        AllTestUnitaUtil.class, AllTestUnitaModel.class, AllTestUnitaControl.class,
        AllTestIntegrazioneControl.class
})

public class AllTest {

}
