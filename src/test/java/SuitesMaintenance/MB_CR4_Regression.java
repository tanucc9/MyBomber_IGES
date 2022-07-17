package SuitesMaintenance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import unita.model.TestEventoBean;

@RunWith(Suite.class)
@SuiteClasses({
		TestEventoBean.class,
})

public class MB_CR4_Regression {
}
