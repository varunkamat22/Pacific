package com.pacific.core.schemas.util;

import com.pacific.core.schemas.SchemaDiscoverable;
import com.pacific.core.schemas.annotations.Attribute;
import com.pacific.core.schemas.objects.Schema;
import com.pacific.core.schemas.objects.Attribute.AttributeBuilder;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SchemaUtil {

    public static Schema createSchemaObject(SchemaDiscoverable schemaResource) {
        Field[] fields = schemaResource.getClass().getDeclaredFields();
        List<com.pacific.core.schemas.objects.Attribute> attributes = new ArrayList<>();

        for (Field field : fields) {
            if (field.getDeclaredAnnotation(Attribute.class) == null) {
                continue;
            } else {
                attributes.add(buildAttribute(field.getAnnotation(Attribute.class)));
            }
        }
        com.pacific.core.schemas.annotations.Schema schemaAnnotation = schemaResource.getClass()
                                                                       .getAnnotation(com.pacific.core.schemas.annotations.Schema.class);
        return new Schema(getSchemaId(schemaResource), schemaAnnotation.name(),
                          schemaAnnotation.publishSchema(), attributes);
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
