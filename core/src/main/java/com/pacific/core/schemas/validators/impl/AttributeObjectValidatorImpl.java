package com.pacific.core.schemas.validators.impl;

import com.pacific.core.schemas.objects.Attribute;
import com.pacific.core.schemas.validators.ValidationResult;
import com.pacific.core.schemas.validators.Validator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class AttributeObjectValidatorImpl implements Validator<Attribute>{

    @Override
    public ValidationResult validate(Attribute attribute) {
        List<String> messages = new ArrayList<>();
        boolean isValid = true;

        if (!isGeneratedValid(attribute)) {
            messages.add(MessageFormat.format("Attribute {0} cannot be marked generated and creatable/updatable at the same time",
                                                    attribute.getName()));
            isValid = false;
        }

        return new ValidationResult(messages.toArray(new String[messages.size()]), isValid);
    }

    private boolean isGeneratedValid(Attribute attribute) {
        return !(attribute.isGenerated() && (attribute.isCreatable() || attribute.isUpdatable() || attribute.isRequired()));
    }

}
