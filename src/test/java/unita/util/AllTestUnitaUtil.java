package unita.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		TestHashTool.class,
		TestSlugGenerator.class,
})

public class AllTestUnitaUtil {
}
