package com.pacific.core.schemas.util;

import com.pacific.core.schemas.SchemaDiscoverable;
import com.pacific.core.schemas.annotations.Attribute;
import com.pacific.core.schemas.objects.Schema;
import com.pacific.core.schemas.objects.Attribute.AttributeBuilder;
import com.pacific.core.schemas.validators.ValidationResult;
import com.pacific.core.schemas.validators.Validator;
import com.pacific.core.schemas.validators.impl.AttributeObjectValidatorImpl;
import com.pacific.core.schemas.validators.impl.SchemaObjectValidatorImpl;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SchemaUtil {

    public static Schema createSchemaObject(SchemaDiscoverable schemaResource) {
        Field[] fields = schemaResource.getClass().getDeclaredFields();
        List<com.pacific.core.schemas.objects.Attribute> attributes = new ArrayList<>();
        Validator<com.pacific.core.schemas.objects.Attribute> attributeValidator = new AttributeObjectValidatorImpl();

        for (Field field : fields) {
            if (field.getDeclaredAnnotation(Attribute.class) == null) {
                continue;
            } else {
                com.pacific.core.schemas.objects.Attribute attribute = buildAttribute(field.getAnnotation(Attribute.class));
                ValidationResult validationResult = attributeValidator.validate(attribute);
                if (!validationResult.isValid()) {
                    System.err.println("Error occurred while validating Attribute in schema. Below are the errors encountered:");
                    Arrays.stream(validationResult.getMessages()).forEach(error -> System.err.println(error));
                    throw new RuntimeException("Error occurred while validating Attribute in schema");
                }
                attributes.add(attribute);
            }
        }
        com.pacific.core.schemas.annotations.Schema schemaAnnotation = schemaResource.getClass()
                                                                       .getAnnotation(com.pacific.core.schemas.annotations.Schema.class);
        Schema schema = new Schema(getSchemaId(schemaResource), schemaAnnotation.name(),
                          schemaAnnotation.publishSchema(), attributes);

        Validator<Schema> schemaValidator = new SchemaObjectValidatorImpl();
        ValidationResult validationResult = schemaValidator.validate(schema);
        if (!validationResult.isValid()) {
            System.err.println(MessageFormat.format("Error occurred while validating the schema object {0}. " +
                    "Below are the errors encountered:", schema.getId()));
            Arrays.stream(validationResult.getMessages()).forEach(error -> System.err.println(error));
            throw new RuntimeException(MessageFormat.format("Error occurred while validating the schema object {0}.", schema.getId()));
        }

        return schema;
    }

    private static com.pacific.core.schemas.objects.Attribute buildAttribute(Attribute attributeData) {
        AttributeBuilder attributeBuilder = new AttributeBuilder();
        return attributeBuilder.withName(attributeData.name())
                        .withType(attributeData.dataType())
                        .withCreatable(attributeData.creatable())
                        .withGenerated(attributeData.generated())
                        .withMultivalued(attributeData.multivalued())
                        .withRequired(attributeData.required())
                        .withUpdatable(attributeData.updatable())
                        .build();
    }

    public static String getSchemaId(SchemaDiscoverable schemaResource) {
        String name = schemaResource.getClass().getDeclaredAnnotation(com.pacific.core.schemas.annotations.Schema.class).name();
        String nameSpace = schemaResource.getClass().getDeclaredAnnotation(com.pacific.core.schemas.annotations.Schema.class).nameSpace();
        return nameSpace + ":" + name;
    }

}
