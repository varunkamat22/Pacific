package com.pacific.core.schemas;

import com.pacific.core.schemas.objects.Schema;
import com.pacific.core.schemas.util.SchemaUtil;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.text.MessageFormat;
import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;

/**
 * This class will build schema objects for every resource
 * marked with @Schema.
 */
@Singleton
@Named("com.pacific.core.schemas.SchemaBuilder")
public class SchemaBuilder {
    private Set<Schema> schemaCache = null;
    private List<SchemaDiscoverable> schemaDiscoverables;

    @Inject
    private SchemaBuilder(List<SchemaDiscoverable> schemaDiscoverables){
        this.schemaDiscoverables = schemaDiscoverables;
        buildSchema();
    }

    public Set<Schema> getSchemas() {
        return Collections.unmodifiableSet(schemaCache);
    }

    private void buildSchema() {
        schemaCache = new HashSet<>();
        Set<String> schemaIds = new HashSet<>();
        System.out.println("Started to build schemas..");
        if (schemaDiscoverables == null || schemaDiscoverables.isEmpty()) {
            System.out.println("No objects found to build schemas..");
        } else {
            schemaDiscoverables.stream()
                         .filter(schemaResource ->
                                 schemaResource.getClass().getDeclaredAnnotation(com.pacific.core.schemas.annotations.Schema.class) != null
                         )
                         .forEach(schemaResource -> {
                            String id = SchemaUtil.getSchemaId(schemaResource);
                            if (schemaIds.contains(id)){
                                throw new RuntimeException(MessageFormat.format("Duplicate schema found {0}", id));
                            }
                            Schema schema = SchemaUtil.createSchemaObject(schemaResource);

                            schemaIds.add(id);
                            schemaCache.add(schema);
            });
        }

    }

}
