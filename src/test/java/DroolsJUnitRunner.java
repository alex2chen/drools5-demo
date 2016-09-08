import com.xfboy.junit.DroolsInjector;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * DroolsJUnitRunner
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
public class DroolsJUnitRunner extends BlockJUnit4ClassRunner {
    public DroolsJUnitRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected Object createTest() throws Exception {
        Object testObject = super.createTest();
        new DroolsInjector().initDrools(testObject);
        return testObject;
    }
}
