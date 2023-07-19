package com.trevzhang.demo.test;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author zhangchunguang.zcg
 * @since 2023/7/19 10:41
 */
public class SpelTest {
    @Test
    public void testSpel() {
        exampleMethod("1A", "2B", "3C");
    }

    public void exampleMethod(Object obj1, Object obj2, Object obj3) {
        String message = "obj1: " + obj1 + ", obj2: " + obj2 + ", obj3: " + obj3;
        System.out.println(message);

        // 使用SPEL表达式获取参数内容
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("obj1", obj1);
        context.setVariable("obj2", obj2);
        context.setVariable("obj3", obj3);
        String spelExpression = "#obj1 + ', ' + #obj2 + ', ' + #obj3";
        String result = (String) new SpelExpressionParser().parseExpression(spelExpression).getValue(context);
        System.out.println(result);
    }
}
