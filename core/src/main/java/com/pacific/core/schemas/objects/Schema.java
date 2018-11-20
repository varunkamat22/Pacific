package com.pacific.core.schemas.objects;

import java.util.Collections;
import java.util.List;

public class Schema {
    private final String id;
    private final String name;
    private final boolean publishSchema;
    private final List<Attribute> attributes;

    public Schema(String id, String name, boolean publishSchema, List<Attribute> attributes) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
        this.publishSchema = publishSchema;
    }

    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
