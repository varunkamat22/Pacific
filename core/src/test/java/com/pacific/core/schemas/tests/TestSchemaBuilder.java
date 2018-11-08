package com.pacific.core.schemas.tests;

import com.pacific.core.schemas.SchemaBuilder;
import com.pacific.core.schemas.SchemaDiscoverable;
import com.pacific.core.schemas.annotations.Attribute;
import com.pacific.core.schemas.objects.Schema;
import com.pacific.core.schemas.tests.config.DIConfig;
import com.pacific.core.schemas.tests.resources.Contact;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.inject.Inject;
import java.lang.reflect.Field;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DIConfig.class})
public class TestSchemaBuilder {

    @Inject
    private SchemaBuilder schemaBuilder;

    @Inject
    private SchemaDiscoverable contact;

    @Test
    public void testSchemaIsBuilt() {
        assertEquals("SchemaBuilder built 0 schemas",1, schemaBuilder.getSchemas().size());
    }

    @Test
    public void testIfExpectedSchemaIsAvaialble() {
        String id = "com.pacific.core:Contact";
        Schema contactSchema = schemaBuilder.getSchemas().stream().filter(schema -> schema.getId().equals(id)).limit(1).findFirst().get();
        assertNotNull("Contact schema not found", contactSchema);
    }

    @Test
    public void testBasicSchemaMetadata() {
        String id = "com.pacific.core:Contact";
        Schema contactSchema = schemaBuilder.getSchemas().stream().filter(schema -> schema.getId().equals(id)).limit(1).findFirst().get();

        com.pacific.core.schemas.annotations.Schema schemaAnnotation = Contact.class.getDeclaredAnnotation(com.pacific.core.schemas.annotations.Schema.class);
        assertEquals("Contact schema name is not correct", schemaAnnotation.name(), contactSchema.getName());
    }

    @Test
    public void testAttributeCount() {
        String id = "com.pacific.core:Contact";
        Schema contactSchema = schemaBuilder.getSchemas().stream().filter(schema -> schema.getId().equals(id)).limit(1).findFirst().get();

        int fieldCount = Contact.class.getDeclaredFields().length;
        assertEquals("The attributes count don't match", fieldCount, contactSchema.getAttributes().size());
    }

    @Test
    public void testAttributeMetadata() {
        String id = "com.pacific.core:Contact";
        Schema contactSchema = schemaBuilder.getSchemas().stream().filter(schema -> schema.getId().equals(id)).limit(1).findFirst().get();

        com.pacific.core.schemas.objects.Attribute firstNameAttr = contactSchema.getAttributes().stream().filter(attribute -> attribute.getName().equals("testField")).limit(1).findFirst().get();
        assertNotNull("testField attribute not found in contact schema", firstNameAttr);
        assertEquals("", true, firstNameAttr.isCreatable());
        assertEquals("", true, firstNameAttr.isGenerated());
        assertEquals("", true, firstNameAttr.isMultivalued());
        assertEquals("", true, firstNameAttr.isRequired());
        assertEquals("", true, firstNameAttr.isUpdatable());
    }
    
}
