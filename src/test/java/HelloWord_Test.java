import com.xfboy.simple.HelloWord;
import org.drools.*;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.io.ResourceFactory;
import org.drools.rule.Package;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.StatelessKnowledgeSession;
import org.drools.spi.Activation;
import org.drools.spi.AgendaFilter;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * HelloWord_Test
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/5
 */
public class HelloWord_Test {

    @BeforeClass
    public static void init() {

    }

    /*
    *
    * 很旧的使用方法
    *
    * 一直有问题，算了吧
    *
     */
    @Test
    public void testHello() throws IOException, DroolsParserException {
        final String filepath = HelloWord_Test.class.getResource("HelloWord.drl").getFile();
        // 设置时间格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        //compiled package
        final Reader reader = new FileReader(filepath);
        final PackageBuilder pkgBuilder = new PackageBuilder();
        pkgBuilder.addPackageFromDrl(reader);

        // Check the builder for errors
        if (pkgBuilder.hasErrors()) {
            System.out.println("compiled error");
            throw new RuntimeException(pkgBuilder.getErrors().toString());
        }
        //add this package to a ruleBase
        final RuleBase ruleBase = RuleBaseFactory.newRuleBase();
        System.out.println("ruleBase.getPackages:" + ruleBase.getPackages().length);
        for (Package p : ruleBase.getPackages()) {
            ruleBase.removePackage(p.getName());
        }
        ruleBase.addPackage(pkgBuilder.getPackage());

        //open session
        final StatefulSession session = ruleBase.newStatefulSession();
        HelloWord helloWord = new HelloWord() {{
            setSayType(0);
        }};
        session.insert(helloWord);
        //fire
        session.asyncFireAllRules(new AgendaFilter() {
            public boolean accept(Activation activation) {
                System.out.println("asyncFireAllRules:" + activation.getRule().getName());
                return false;
            }
        });
        session.dispose();
        reader.close();

        System.out.println("result:");
        System.out.println(helloWord);
    }

    @Test
    public void testSayHello() {
        /***
         * KnowledgeBuilder的作用就是对编写好的规则这些规则文件进行编译，最终产生一批编译好的规则包（KnowledgePackage）给其它的应用程序使用
         */
        //创建一个KnowledgeBuilder,负责对规则的编译
        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        //添加规则资源到KnowledgeBuilder
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("HelloWord.drl"), ResourceType.DRL);

        if (knowledgeBuilder.hasErrors()) {
            System.out.println("编译失败");
            throw new RuntimeException(knowledgeBuilder.getErrors().toString());
        }
        /**
         * KnowledgeBase 是 Drools 提供的用来收集应用当中知识（knowledge）定义的知识库对象，在一个 KnowledgeBase当中可以包含普通的
         规则（rule）、规则流(rule  flow)、函数定义(function)、用户自定义对象（type  model）等。KnowledgeBase 本身不包含任何业务数据对象，
         业务对象都是插入到由 KnowledgeBase产生的两种类型的session对象当中 （StatefulKnowledgeSession和StatelessKnowledgeSession）
         */
        //创建KnowledgeBase，负责规则收集
        KnowledgeBaseConfiguration kbConf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        kbConf.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbConf);
        kbase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
        HelloWord helloWord = new HelloWord() {{
            setSayType(0);
        }};
        /**
         *
         *StatefulKnowledgeSession对象负责接受业务数据，规则执行，是一种最常用的与规则引擎进行交互的方式，它可以与规则引擎建立一个持续的交互通道
         最后使用完 StatefulKnowledgeSession 对象之后，一定要调用其 dispose()方法以释放相关内存资源
         *
         */
//        StatefulKnowledgeSession session = kbase.newStatefulKnowledgeSession();
//        session.insert(helloWord);
//        session.fireAllRules();
//        session.dispose();
        /**
         * StatelessKnowledgeSession的作用与 StatefulKnowledgeSession 相仿，事实上对 StatefulKnowledgeSession做了包装，使它不需要再调用 dispose()
         方法释放内存资源,它本身所具有的一些特性，在使用时不能进行重复插入 fact的操作、也不能重复的调用fireAllRules()方法来执行所有的规则，它只有
         execute(…)方法，通过这个方法可以实现插入所有的 fact并且可以同时执行所有的规则或规则流，事实上也就是在执行 execute(…)方法的时候就在
         StatelessKnowledgeSession内部执行了 insert()方法、fireAllRules()方法和 dispose()方法

         注：execute对批量方面更好
         *
         */
        StatelessKnowledgeSession session=kbase.newStatelessKnowledgeSession();
        session.execute(helloWord);

        System.out.println("result:");
        System.out.println(helloWord);
    }


}
