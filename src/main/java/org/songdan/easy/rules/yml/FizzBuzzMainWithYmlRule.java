package org.songdan.easy.rules.yml;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
import org.jeasy.rules.mvel.MVELRuleFactory;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * @author: Songdan
 * @create: 2019-11-25 18:59
 **/
public class FizzBuzzMainWithYmlRule {

    public static void main(String[] args) throws FileNotFoundException {
        // create a rules engine
        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(true);
        RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);
        InputStreamReader inputStreamReader = new InputStreamReader(FizzBuzzMainWithYmlRule.class.getClassLoader().getResourceAsStream("rules.yml"));
        // create rules
        Rules rules = MVELRuleFactory.createRulesFrom(inputStreamReader);
        // fire rules
        Facts facts = new Facts();
        for (int i = 1; i <= 100; i++) {
            facts.put("number", i);
            fizzBuzzEngine.fire(rules, facts);
            System.out.println();
        }
    }

}
