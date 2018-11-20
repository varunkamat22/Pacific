package com.pacific.core.schemas;

import com.pacific.core.schemas.objects.Schema;
import com.pacific.core.schemas.objects.StackedSchema;
import com.pacific.core.schemas.util.SchemaUtil;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.text.MessageFormat;
import java.util.*;

/**
 * This class will build schema objects for every resource
 * marked with @Schema.
 */
@Singleton
@Named("com.pacific.core.schemas.SchemaBuilder")
public class SchemaBuilder {
    private Map<String, Schema> schemaCache = null;
    private List<SchemaDiscoverable> schemaDiscoverables;

    @Inject
    private SchemaBuilder(List<SchemaDiscoverable> schemaDiscoverables){
        this.schemaDiscoverables = schemaDiscoverables;
        buildSchema();
    }

    public Map<String, Schema> getSchemas() {
        return Collections.unmodifiableMap(schemaCache);
    }

    private void buildSchema() {
        schemaCache = new HashMap<>();
        Set<String> schemaIds = new HashSet<>();

        System.out.println("Started to build schemas..");

        if (schemaDiscoverables == null || schemaDiscoverables.isEmpty()) {
            System.out.println("No objects found to build schemas..");
        } else {
            //This stack will keep track of Schemas that have embedded objects.
            Stack<StackedSchema> stackedSchemas = new Stack<>();

            schemaDiscoverables.stream()
                         .filter(schemaResource ->
                                 schemaResource.getClass().getDeclaredAnnotation(com.pacific.core.schemas.annotations.Schema.class) != null
                         )
                         .forEach(schemaResource -> {
                            String id = SchemaUtil.getSchemaId(schemaResource);
                            if (schemaIds.contains(id)){
                                throw new RuntimeException(MessageFormat.format("Duplicate schema found {0}", id));
                            }
                            Schema schema = SchemaUtil.createSchemaObject(schemaResource, true, schemaCache);
                            if(schema instanceof StackedSchema) {
                                stackedSchemas.push((StackedSchema) schema);
                            } else {
                                schemaIds.add(id);
                                schemaCache.put(id, schema);
                            }
                         });

            //At this point all embedded schemas are built. Now build parent schemas.
            while(!stackedSchemas.isEmpty()) {
                StackedSchema stackedSchema = stackedSchemas.pop();

                String id = SchemaUtil.getSchemaId(stackedSchema.getSchemaDiscoverable());
                if (schemaIds.contains(id)){
                    throw new RuntimeException(MessageFormat.format("Duplicate schema found {0}", id));
                }

                Schema schema = SchemaUtil.createSchemaObject(stackedSchema.getSchemaDiscoverable(), false, schemaCache);
                schemaIds.add(id);
                schemaCache.put(id, schema);
            }
        }

    }

}
