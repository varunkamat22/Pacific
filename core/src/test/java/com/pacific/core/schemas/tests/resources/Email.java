package com.pacific.core.schemas.tests.resources;

import com.pacific.core.schemas.SchemaDiscoverable;
import com.pacific.core.schemas.annotations.Attribute;
import com.pacific.core.schemas.annotations.Schema;
import org.springframework.stereotype.Component;

@Component
@Schema(name = "Email", nameSpace = "com.pacific.core")
public class Email implements SchemaDiscoverable{

    @Attribute(name = "emailId", dataType = com.pacific.core.schemas.objects.Attribute.Type.STRING, creatable = true, updatable = true, required = true)
    private String emailId;

    @Attribute(name = "sendNotifications", dataType = com.pacific.core.schemas.objects.Attribute.Type.BOOLEAN, creatable = true, updatable = true)
    private boolean sendNotifications;

}
