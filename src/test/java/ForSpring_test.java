import com.xfboy.login.LoginService;
import com.xfboy.login.Vip;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ForSpring_test
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/8
 */
public class ForSpring_test {
    @Test
    public void login_test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:beans-drools.xml");
        if (applicationContext != null) {
            LoginService loginService = (LoginService) applicationContext.getBean("loginService");
            StatefulKnowledgeSession ksession = (StatefulKnowledgeSession) applicationContext.getBean("ksession");
            Vip vip=new Vip();
            vip.setName("alex");
            vip.setPassword("1");
            loginService.checkLogin(ksession,vip);
            System.out.println("exit");
        }
    }

}
