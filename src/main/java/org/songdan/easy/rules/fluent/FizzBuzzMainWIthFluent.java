package org.songdan.easy.rules.fluent;

import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.core.RulesEngineParameters;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.support.UnitRuleGroup;
import org.songdan.easy.rules.model.NumObj;

/**
 * @author: Songdan
 * @create: 2019-11-25 19:13
 **/
public class FizzBuzzMainWIthFluent {

    public static void main(String[] args) {
        Rule fizzRule = new RuleBuilder().name("fizzRule").description("fizz num").when(new Condition() {
            public boolean evaluate(Facts facts) {
                Integer num = facts.get("num");
                return num % 5 == 0;
            }
        }).then(new Action() {
            public void execute(Facts facts) throws Exception {
                System.out.println(facts.get("num")+" is a fizz num!!!");
            }
        }).priority(1).build();
        Rule buzzRule = new RuleBuilder().name("fizzRule").description("fizz num").when(new Condition() {
            public boolean evaluate(Facts facts) {
                Integer num = facts.get("num");
                return num % 7 == 0;
            }
        }).then(new Action() {
            public void execute(Facts facts) throws Exception {
                System.out.println(facts.get("num")+" is a buzz num!!!");
            }
        }).priority(2).build();
        UnitRuleGroup unitRuleGroup = new UnitRuleGroup("mixRule","fizz and buzz num ",3);
        unitRuleGroup.addRule(fizzRule);
        unitRuleGroup.addRule(buzzRule);

        MVELRule mvelRule = new MVELRule()
                .name("weather rule")
                .description("if it rains then take an umbrella")
                .when("obj.generate() == 10")
                .then("System.out.println(\"It rains, take an umbrella!\");");

        RulesEngineParameters parameters = new RulesEngineParameters().skipOnFirstAppliedRule(false);
        RulesEngine fizzBuzzEngine = new DefaultRulesEngine(parameters);
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(fizzRule);
        rules.register(buzzRule);
        rules.register(unitRuleGroup);
        rules.register(mvelRule);
        for (int i = 0; i < 100; i++) {
            facts.put("num",i);
            fizzBuzzEngine.fire(rules,facts);
            System.out.println();
        }
        facts.put("obj", new NumObj(10));
        fizzBuzzEngine.fire(rules,facts);
    }

}
