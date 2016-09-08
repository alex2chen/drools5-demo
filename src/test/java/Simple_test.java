import com.xfboy.simple.Customer;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.QueryResult;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.Activation;
import org.drools.runtime.rule.AgendaFilter;
import org.drools.runtime.rule.QueryResults;
import org.drools.runtime.rule.QueryResultsRow;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Simple_test
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/6
 */
public class Simple_test {

    private static StatefulKnowledgeSession session;
    private static String[] ruleFile = {"listCustomer.drl","simple.drl"};

    @BeforeClass
    public static void initSession() {
        System.out.println("init");
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        //编译
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        for (String f : ruleFile) {
            knowledgeBuilder.add(new ClassPathResource(f), ResourceType.DRL);
        }
        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException(knowledgeBuilder.getErrors().toString());
        }
        //规则收集
//        KnowledgeBaseConfiguration kbConf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
//        kbConf.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");//不起作用，为毛
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        session = kbase.newStatefulKnowledgeSession();

    }

    @AfterClass
    public static void destrSession() {
        System.out.println("des");
        if (session!=null){
            session.dispose();
        }
    }

    @Test
    public void listCustomer_test() {
        Customer cus1=new Customer();
        cus1.setName("张三");
        Customer cus2=new Customer();
        cus2.setName("李四");
        Customer cus3=new Customer();
        cus3.setName("Alex");
        Customer cus4=new Customer();
        cus4.setName("李小龙");
        session.insert(cus1);
        session.insert(cus2);
        session.insert(cus3);
        session.insert(cus4);
        //执行
        session.fireAllRules();

    }

    @Test
    public void query_test() {
        Customer customer=new Customer(){{
            setName("Alex");
        }};
        session.insert(customer);
        //执行
        session.fireAllRules();

        QueryResults results=session.getQueryResults("query customer1 count");
        System.out.println("query customer1 count:"+results.size());
        QueryResults results2=session.getQueryResults("query customer2 count");
        System.out.println("query customer2 count:"+results2.size());

        QueryResults queryLi=session.getQueryResults("queryLi");
        if (queryLi.size()>0){
            for (QueryResultsRow item:queryLi){
                Customer customer1=(Customer) item.get("$cus");
                System.out.println(customer1);
            }
        }
        System.out.println("执行参数化查询");
        QueryResults searchCus=session.getQueryResults("searchCus",new Object[]{new Integer(20),"李龙"});
        if (searchCus.size()>0){
            for (QueryResultsRow item:searchCus){
                Customer customer1=(Customer) item.get("$cus");
                System.out.println(customer1);
            }
        }

    }
}
