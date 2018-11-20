package com.pacific.core.schemas.objects;

import com.pacific.core.schemas.SchemaDiscoverable;
import java.util.ArrayList;

public class StackedSchema extends Schema{
    private SchemaDiscoverable schemaDiscoverable;

    public StackedSchema(SchemaDiscoverable schemaDiscoverable) {
        super("", "", true, new ArrayList<>());
        this.schemaDiscoverable = schemaDiscoverable;
    }

    public SchemaDiscoverable getSchemaDiscoverable() {
        return schemaDiscoverable;
    }

    public void setSchemaDiscoverable(SchemaDiscoverable schemaDiscoverable) {
        this.schemaDiscoverable = schemaDiscoverable;
    }
}
