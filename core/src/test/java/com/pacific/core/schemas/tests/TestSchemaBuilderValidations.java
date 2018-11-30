package com.pacific.core.schemas.tests;

import com.pacific.core.schemas.SchemaBuilderService;
import com.pacific.core.schemas.SchemaDiscoverable;
import com.pacific.core.schemas.tests.resources.ContactNoAttributes;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TestSchemaBuilderValidations {

    @Test
    public void testAttributeMissingValidation() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<SchemaDiscoverable> schemaDiscoverables = new ArrayList<>();
        schemaDiscoverables.add(new ContactNoAttributes());
        Constructor schemaBuilderConstructor = SchemaBuilderService.class.getDeclaredConstructors()[0];
        schemaBuilderConstructor.setAccessible(true);
        try {
            schemaBuilderConstructor.newInstance(schemaDiscoverables);
        } catch (Exception ex) {
            Assert.assertEquals("An exception should be thrown if Attributes are missing in schema object",
                    "Error occurred while validating the schema object com.pacific.core:ContactNoAttributes.",
                    ex.getCause().getMessage());
        }
    }

}
