package com.pacific.core.schemas.tests;

import com.pacific.core.schemas.SchemaBuilder;
import com.pacific.core.schemas.SchemaDiscoverable;
import com.pacific.core.schemas.objects.Schema;
import com.pacific.core.schemas.tests.config.DIConfig;
import com.pacific.core.schemas.tests.resources.Contact;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.inject.Inject;
import com.pacific.core.schemas.objects.Attribute.Type;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DIConfig.class})
public class TestSchemaBuilder {

    @Inject
    private SchemaBuilder schemaBuilder;

    @Inject
    private SchemaDiscoverable contact;

    @Test
    public void testSchemaIsBuilt() {
        assertNotEquals("SchemaBuilder built 0 schemas",0, schemaBuilder.getSchemas().size());
    }

    @Test
    public void testIfExpectedSchemaIsAvaialble() {
        Schema contactSchema = schemaBuilder.getSchemas().get("com.pacific.core:Contact");
        assertNotNull("Contact schema not found", contactSchema);
        Schema emailSchema = schemaBuilder.getSchemas().get("com.pacific.core:Email");
        assertNotNull("Email schema not found", emailSchema);
    }

    @Test
    public void testBasicSchemaMetadata() {
        String id = "com.pacific.core:Contact";
        Schema contactSchema = schemaBuilder.getSchemas().get(id);

        com.pacific.core.schemas.annotations.Schema schemaAnnotation = Contact.class.getDeclaredAnnotation(com.pacific.core.schemas.annotations.Schema.class);
        assertEquals("Contact schema name is not correct", schemaAnnotation.name(), contactSchema.getName());
    }

    @Test
    public void testAttributeCount() {
        String id = "com.pacific.core:Contact";
        Schema contactSchema = schemaBuilder.getSchemas().get(id);

        int fieldCount = Contact.class.getDeclaredFields().length;
        assertEquals("The attributes count don't match", fieldCount, contactSchema.getAttributes().size());
    }

    @Test
    public void testAttributeMetadata() {
        String id = "com.pacific.core:Contact";
        Schema contactSchema = schemaBuilder.getSchemas().get(id);

        com.pacific.core.schemas.objects.Attribute testFieldAttr = contactSchema.getAttributes().stream().filter(attribute -> attribute.getName().equals("testField")).limit(1).findFirst().get();
        assertNotNull("testField attribute not found in contact schema", testFieldAttr);
        assertEquals("testField Creatable metadata not correct in contact schema", true, testFieldAttr.isCreatable());
        assertEquals("testField Generated metadata not correct in contact schema", false, testFieldAttr.isGenerated());
        assertEquals("testField Multivalued metadata not correct in contact schema", true, testFieldAttr.isMultivalued());
        assertEquals("testField Required metadata not correct in contact schema", true, testFieldAttr.isRequired());
        assertEquals("testField Updatable metadata not correct in contact schema", true, testFieldAttr.isUpdatable());
        assertEquals("testField DataType metadata not correct in contact schema", Type.STRING, testFieldAttr.getDataType());
    }

    @Test
    public void testEmbeddedAttributeMetadata() {
        String id = "com.pacific.core:Contact";
        Schema contactSchema = schemaBuilder.getSchemas().get(id);

        com.pacific.core.schemas.objects.Attribute emailAttr = contactSchema.getAttributes().stream().filter(attribute -> attribute.getName().equals("email")).limit(1).findFirst().get();
        assertNotNull("email attribute not found in contact schema", emailAttr);
        assertEquals("email DataType metadata not correct in contact schema", Type.EMBEDDED, emailAttr.getDataType());
        assertNotNull("email subSchema is null in contact schema", emailAttr.getSubSchema());
        assertEquals("email subSchema id is incorrect in Contact schema", "com.pacific.core:Email", emailAttr.getSubSchema().getId());
    }

    @Test
    public void testNonEmbeddedReferencedAttributeMetadata() {
        String id = "com.pacific.core:Contact";
        Schema contactSchema = schemaBuilder.getSchemas().get(id);

        com.pacific.core.schemas.objects.Attribute relatedContactsAttr = contactSchema.getAttributes().stream().filter(attribute -> attribute.getName().equals("relatedContacts")).limit(1).findFirst().get();
        assertNotNull("relatedContacts attribute not found in contact schema", relatedContactsAttr);
        assertEquals("relatedContacts DataType metadata not correct in contact schema", Type.STRING, relatedContactsAttr.getDataType());
        assertNull("relatedContacts subSchema is not null in contact schema", relatedContactsAttr.getSubSchema());
        assertEquals("relatedContacts refs is not correct in contact schema", id, relatedContactsAttr.getRefs());
    }
}
