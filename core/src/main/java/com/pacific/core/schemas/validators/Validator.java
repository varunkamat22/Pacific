package com.pacific.core.schemas.validators;

public interface Validator<T> {
    public ValidationResult validate(T t);
}
