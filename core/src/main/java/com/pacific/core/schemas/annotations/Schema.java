package com.pacific.core.schemas.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Schema {
    String name();
    String nameSpace();
    String description() default "";
    boolean publishSchema() default true;
}
