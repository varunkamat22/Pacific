package com.pacific.core.schemas.objects;

import java.util.Collections;
import java.util.List;

public class Schema {
    private String id;
    private String name;
    private String description;
    private boolean publishSchema;
    private List<Attribute> attributes;

    public Schema(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setPublishSchema(boolean publishSchema) {
        this.publishSchema = publishSchema;
    }

    private void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
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

    public String getDescription() {
        return description;
    }

    public boolean isPublishSchema() {
        return publishSchema;
    }

    public static class SchemaBuilder {
        private Schema schema;

        public SchemaBuilder(String id, String name) {
            schema = new Schema(id, name);
        }

        public SchemaBuilder withDescription(String description) {
            schema.setDescription(description);
            return this;
        }

        public SchemaBuilder withPublishSchema(boolean publishSchema) {
            schema.setPublishSchema(publishSchema);
            return this;
        }

        public SchemaBuilder withAttributes(List<Attribute> attributes) {
            schema.setAttributes(attributes);
            return this;
        }

        public Schema build() {
            return schema;
        }
    }
    
}
