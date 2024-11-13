package com.disaster.mode.behavior.interpreter.spelexp;


import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class Client {
    public static void main(String[] args) {
        //Expression接口下的实现类
        /*A
        Expression接口 表达式接口
        下面有不同的实现类，比如SpelExpression或者CompositeStringExpression。
        使用时候，根据你创建的不同的parser对象，返回不同的Expression
         */
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression("10*(2+1)*1+66");
        int  result  = (Integer)expression.getValue();
        System.out.println(result);
    }
}
