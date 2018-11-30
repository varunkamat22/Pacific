package com.pacific.core.schemas.tests.resources;

import com.pacific.core.schemas.SchemaDiscoverable;
import com.pacific.core.schemas.annotations.Attribute;
import com.pacific.core.schemas.annotations.Schema;
import org.springframework.stereotype.Component;
import com.pacific.core.schemas.objects.Attribute.Type;

@Component
@Schema(name = "Contact", nameSpace = "com.pacific.core", description = "Represents the Contact related info of a Person")
public class Contact implements SchemaDiscoverable{

    @Attribute(name = "id", dataType = Type.STRING, generated = true)
    private String id;

    @Attribute(name = "firstName", dataType = Type.STRING, required = true, creatable = true, updatable = true)
    private String firstName;

    @Attribute(name = "lastName", dataType = Type.STRING, creatable = true, updatable = true)
    private String lastName;

    @Attribute(name = "testField", description = "Just a test field", required = true, dataType = Type.STRING, creatable = true, updatable = true, multivalued = true)
    private String[] testField;

    @Attribute(name = "email", referenceSchemaId = "com.pacific.core:Email", required = true, dataType = Type.EMBEDDED, creatable = true, updatable = true)
    private Email email;

    @Attribute(name = "relatedContacts", dataType = Type.STRING, referenceSchemaId = "com.pacific.core:Contact", multivalued = true, creatable = true, updatable = true)
    private String[] relatedContacts;

}
