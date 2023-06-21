package me.jaden.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String [] {
                "me.jaden.config.autoconfig.DispatcherServletConfiguration",
                "me.jaden.config.autoconfig.TomcatWebServerConfiguration",
        };
    }
}
