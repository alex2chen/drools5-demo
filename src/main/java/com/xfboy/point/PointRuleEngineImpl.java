package com.xfboy.point;

import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.io.ResourceFactory;
import org.drools.io.impl.ClassPathResource;
import org.drools.spi.Activation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PointRuleEngineImpl
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/7
 */
public class PointRuleEngineImpl implements PointRuleEngine {
    private RuleBase ruleBase;
    private static String[] ruleFile = {"addpoint.drl","subpoint.drl"};
    /**
     * 获取测试脚本文件
     *
     * @return
     */
    @Deprecated
    private List<String> getTestDrlFile() {
        List<String> drlFilePath = new ArrayList<String>();
        drlFilePath
                .add(this.getClass().getResource("addpoint.drl").getFile());
        drlFilePath
                .add(this.getClass().getResource("subpoint.drl").getFile());
        System.out.println(drlFilePath);
        return drlFilePath;
    }

    /* (non-Javadoc)
     * @see com.drools.demo.point.PointRuleEngine#initEngine()
     */
    public void initEngine() {
        // 设置时间格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        ruleBase = RuleBaseApi.getRuleBase();
        try {
            PackageBuilder backageBuilder = getPackageBuilderFromDrlFile();
            ruleBase.addPackages(backageBuilder.getPackages());
        } catch (DroolsParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see com.drools.demo.point.PointRuleEngine#refreshEnginRule()
     */
    public void refreshEnginRule() {
        ruleBase = RuleBaseApi.getRuleBase();
        org.drools.rule.Package[] packages = ruleBase.getPackages();
        for (org.drools.rule.Package pg : packages) {
            ruleBase.removePackage(pg.getName());
        }

        initEngine();
    }

    /* (non-Javadoc)
     * @see com.drools.demo.point.PointRuleEngine#executeRuleEngine(com.drools.demo.point.PointDomain)
     */
    public void executeRuleEngine(final PointDomain pointDomain) {
        if (null == ruleBase.getPackages() || 0 == ruleBase.getPackages().length) {
            return;
        }

        StatefulSession statefulSession = ruleBase.newStatefulSession();
        statefulSession.insert(pointDomain);

        // fire
        statefulSession.fireAllRules(new org.drools.spi.AgendaFilter() {
            public boolean accept(Activation activation) {
                return !activation.getRule().getName().contains("_test");
            }
        });

        statefulSession.dispose();
    }

    /**
     * 从Drl规则文件中读取规则
     *
     * @return
     * @throws Exception
     */
    private PackageBuilder getPackageBuilderFromDrlFile() throws Exception {
        // 获取测试脚本文件
//        List<String> drlFilePath = getTestDrlFile();
        // 装载测试脚本文件
//        List<Reader> readers = readRuleFromDrlFile(drlFilePath);

        PackageBuilder backageBuilder = new PackageBuilder();
//        for (Reader r : readers) {
//            backageBuilder.addPackageFromDrl(r);
//        }
        for (String f:ruleFile){
            String path=this.getClass().getClassLoader().getResource(f).getPath();
            backageBuilder.addPackageFromDrl(ResourceFactory.newFileResource(path));
        }
        // 检查脚本是否有问题
        if (backageBuilder.hasErrors()) {
            throw new Exception(backageBuilder.getErrors().toString());
        }

        return backageBuilder;
    }

    /**
     * 装载测试脚本文件
     *
     * @param drlFilePath
     * @return
     * @throws FileNotFoundException
     */
    @Deprecated
    private List<Reader> readRuleFromDrlFile(List<String> drlFilePath) throws FileNotFoundException {
        if (null == drlFilePath || 0 == drlFilePath.size()) {
            return null;
        }

        List<Reader> readers = new ArrayList<Reader>();

        for (String ruleFilePath : drlFilePath) {
            readers.add(new FileReader(new File(ruleFilePath)));
        }

        return readers;
    }
}

