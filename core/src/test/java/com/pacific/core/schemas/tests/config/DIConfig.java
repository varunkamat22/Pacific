package com.pacific.core.schemas.tests.config;

import com.pacific.core.schemas.annotations.Schema;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@Configuration
@ComponentScan(basePackages = {"com.pacific.core.schemas.**"})
public class DIConfig {
}
