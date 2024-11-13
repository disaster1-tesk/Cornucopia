package com.disaster.ioc.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Objects;

public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if (Objects.nonNull(conditionContext.getEnvironment().getProperty("condition")) && conditionContext.getEnvironment().getProperty("condition").equals("测试")){
            return true;
        }
        return false;
    }
}
