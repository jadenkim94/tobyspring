package me.jaden.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalTest {
    @Test
    void conditional() {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(SampleBeanConfiguration.class);
//        context.register(SampleBean2Configuration.class);
//        context.refresh();
//        assertThat(context.getBean(SampleBean.class)).isNotNull();
//        assertThatThrownBy(() -> context.getBean(SampleBean2.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(SampleBeanConfiguration.class, SampleBean2Configuration.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(SampleBean.class);
                    assertThat(context).doesNotHaveBean(SampleBean2.class);
                });
    }

   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.TYPE)
   @Conditional(BooleanCondition.class)
   @interface BooleanConditional {
        boolean value();
   }

    @Configuration
    @BooleanConditional(true)
    static class SampleBeanConfiguration {
        @Bean
        SampleBean sampleBean() {
            return new SampleBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class SampleBean2Configuration {
        @Bean
        SampleBean2 sampleBean2() {
            return new SampleBean2();
        }

    }

    static class SampleBean {
        public SampleBean() {
        }
    }
    static class SampleBean2 {
        public SampleBean2() {
        }
    }

    static class SampleBeanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class SampleBean2Condition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

    private static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes =
                    metadata.getAnnotationAttributes(BooleanConditional.class.getName());

            Boolean condition = (Boolean) annotationAttributes.get("value");
            return condition;
        }
    }
}
