package me.jaden.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

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

    @Configuration
    @Conditional(SampleBeanCondition.class)
    static class SampleBeanConfiguration {
        @Bean
        SampleBean sampleBean() {
            return new SampleBean();
        }
    }

    @Configuration
    @Conditional(SampleBean2Condition.class)
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
}
