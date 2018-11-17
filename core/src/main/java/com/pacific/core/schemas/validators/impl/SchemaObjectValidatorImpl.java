package com.pacific.core.schemas.validators.impl;

import com.pacific.core.schemas.objects.Schema;
import com.pacific.core.schemas.validators.ValidationResult;
import com.pacific.core.schemas.validators.Validator;
import java.util.ArrayList;
import java.util.List;

public class SchemaObjectValidatorImpl implements Validator<Schema>{

    @Override
    public ValidationResult validate(Schema schema) {
        List<String> messages = new ArrayList<>();
        boolean isValid = true;

        if (checkIfAttributeListEmpty(schema)) {
                isValid = false;
                messages.add("Attributes in Schema cannot be empty");
        }

        return new ValidationResult(messages.toArray(new String[messages.size()]), isValid);
    }

    private boolean checkIfAttributeListEmpty(Schema schema) {
        return schema.getAttributes().isEmpty();
    }
}
