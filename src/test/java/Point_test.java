import com.xfboy.point.PointDomain;
import com.xfboy.point.PointRuleEngine;
import com.xfboy.point.PointRuleEngineImpl;
import org.drools.io.ResourceFactory;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Point_test
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/7
 */
public class Point_test {
    @Test
    public void execut_test() throws IOException {
        PointRuleEngine pointRuleEngine = new PointRuleEngineImpl();
        pointRuleEngine.initEngine();
        final PointDomain pointDomain = new PointDomain();
        pointDomain.setUserName("hello kity");
        pointDomain.setBackMondy(100d);
        pointDomain.setBuyMoney(500d);
        pointDomain.setBackNums(1);
        pointDomain.setBuyNums(5);
        pointDomain.setBillThisMonth(5);
        pointDomain.setBirthDay(true);
        pointDomain.setPoint(0l);

        pointRuleEngine.executeRuleEngine(pointDomain);

        System.out.println("执行完毕BillThisMonth：" + pointDomain.getBillThisMonth());
        System.out.println("执行完毕BuyMoney：" + pointDomain.getBuyMoney());
        System.out.println("执行完毕BuyNums：" + pointDomain.getBuyNums());
        System.out.println("执行完毕规则引擎决定发送积分：" + pointDomain.getPoint());

    }
}
