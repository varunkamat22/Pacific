package com.pacific.core.schemas.tests.resources;

import com.pacific.core.schemas.SchemaBuilder;
import com.pacific.core.schemas.SchemaDiscoverable;
import com.pacific.core.schemas.annotations.Attribute;
import com.pacific.core.schemas.annotations.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import com.pacific.core.schemas.objects.Attribute.Type;

@Component
@Schema(name = "Contact", nameSpace = "com.pacific.core")
public class Contact implements SchemaDiscoverable{

    @Attribute(name = "id", dataType = Type.STRING, generated = true)
    private String id;

    @Attribute(name = "firstName", dataType = Type.STRING, required = true, creatable = true, updatable = true)
    private String firstName;

    @Attribute(name = "lastName", dataType = Type.STRING, creatable = true, updatable = true)
    private String lastName;

    @Attribute(name = "testField", required = true, dataType = Type.STRING, creatable = true, updatable = true, generated = true, multivalued = true)
    private String testField;
}
