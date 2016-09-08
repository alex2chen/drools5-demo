import com.xfboy.junit.annotations.DrlFiles;
import com.xfboy.junit.annotations.DrlSession;
import com.xfboy.junit.fact.Customer;
import com.xfboy.junit.fact.Purchase;
import org.drools.StatefulSession;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * RunnerBasedTest
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/9/8
 */
@RunWith(DroolsJUnitRunner.class)
@DrlFiles(value = "purchase.drl", location = "/")
public class JUnitRunnerTest {
    @DrlSession
    private StatefulSession session;

    @Test
    public void should_set_discount() {
        Purchase purchase = new Purchase(new Customer(17));
        session.insert(purchase);
        session.fireAllRules();
        assertTrue(purchase.getTicket().isDiscount());
    }

    @Test
    public void should_not_set_discount() {
        Purchase purchase = new Purchase(new Customer(22));

        session.insert(purchase);
        session.fireAllRules();

        assertFalse(purchase.getTicket().isDiscount());
    }
}
