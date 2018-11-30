package com.pacific.core.schemas.objects;

import com.pacific.core.schemas.SchemaDiscoverable;

public class StackedSchema extends Schema{
    private SchemaDiscoverable schemaDiscoverable;

    public StackedSchema(SchemaDiscoverable schemaDiscoverable) {
        super("", "");
        this.schemaDiscoverable = schemaDiscoverable;
    }

    public SchemaDiscoverable getSchemaDiscoverable() {
        return schemaDiscoverable;
    }

    public void setSchemaDiscoverable(SchemaDiscoverable schemaDiscoverable) {
        this.schemaDiscoverable = schemaDiscoverable;
    }
}
