import com.xfboy.junit.DroolsInjector;
import com.xfboy.junit.annotations.DrlFiles;
import com.xfboy.junit.annotations.DrlSession;
import com.xfboy.junit.fact.Customer;
import com.xfboy.junit.fact.Purchase;
import org.drools.StatefulSession;
import org.junit.Before;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.junit.Assert.*;

/**
 * RunnerjunitTest2
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
@DrlFiles(value = "purchase.drl", location = "/")
public class JUnitRunnerTest2 {
    @DrlSession
    private StatefulSession session;

    @Before
    public void initDrools() throws Exception {
        new DroolsInjector().initDrools(this);
    }

    @Test
    public void should_set_discount() {
        Purchase purchase = new Purchase(new Customer(17));
        session.insert(purchase);
        session.fireAllRules();
        assertTrue(purchase.getTicket().isDiscount());
    }

    @Test
    public void file_not_found() throws Exception {
        NotExistingFileTestClass testClass = new NotExistingFileTestClass();
        catchException(new DroolsInjector(), Exception.class).initDrools(testClass);
        assertTrue(caughtException() instanceof IllegalArgumentException);
    }

    @Test
    public void complie_error() throws Exception {
        RulesWithErrorsTestClass testClass = new RulesWithErrorsTestClass();
        catchException(new DroolsInjector(), Exception.class).initDrools(testClass);
        assertTrue(caughtException() instanceof IllegalStateException);
    }

    @DrlFiles(value = "rules-with-errors.drl", location = "/")
    private static class NotExistingFileTestClass {
    }

    @DrlFiles(value = "rules-with-errors.drl", location = "/drl/")
    private static class RulesWithErrorsTestClass {
    }
}
