package org.songdan.easy.rules.anno;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.support.UnitRuleGroup;

/**
 * @author: Songdan
 * @create: 2019-11-25 18:01
 **/
public class RuleClass {

    @Rule(priority = 1)
    public static class FizzRule {
        @Condition
        public boolean isFizz(@Fact("number") int number) {
            return number % 5 == 0;
        }
        @Action
        public void printFizz() {
            System.out.print("fizz");
        }
    }

    @Rule(priority = 2)
    public static class BuzzRule {
        @Condition
        public boolean isBuzz(@Fact("number") int number) {
            return number % 7 == 0;
        }
        @Action
        public void printBuzz() {
            System.out.print("buzz");
        }
    }

    public static class FizzBuzzRule extends UnitRuleGroup {
        public FizzBuzzRule(Object... rules) {
            for (Object rule : rules) {
                addRule(rule);
            }
        }
        @Override
        public int getPriority() {
            return 0;
        }
    }

    @Rule(priority = 3)
    public static class NonFizzBuzzRule {
        @Condition
        public boolean isNotFizzNorBuzz(@Fact("number") int number) {
            return number % 5 != 0 || number % 7 != 0;
        }
        @Action
        public void printInput(@Fact("number") int number) {
            System.out.print(number);
        }
    }

}
