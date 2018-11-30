package com.pacific.core.schemas.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.pacific.core.schemas.objects.Attribute.Type;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Attribute {
    String name();
    Type dataType();
    String description() default "";
    boolean required() default false;
    boolean creatable() default false;
    boolean updatable() default false;
    boolean generated() default false;
    boolean multivalued() default false;
    String referenceSchemaId() default "";
}
