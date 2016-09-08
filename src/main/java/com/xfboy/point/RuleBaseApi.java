package com.xfboy.point;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;

/**
 * RuleBaseApi
 *
 * 此api过于旧请替换
 *
 * @author alex.chen
 * @version 1.0.0
 * @date 2016/8/7
 */
public class RuleBaseApi {
    private static RuleBase ruleBase;
    public static RuleBase getRuleBase() {
        return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();
    }
}
