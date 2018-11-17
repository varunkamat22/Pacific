package com.pacific.core.schemas.validators;

public class ValidationResult {
    private String[] messages;
    private boolean isValid;

    public ValidationResult() {
        messages = new String[0];
    }

    public ValidationResult(boolean isValid) {
        messages = new String[0];
        this.isValid = isValid;
    }

    public ValidationResult(String[] messages, boolean isValid) {
        this.messages = messages.clone();
        this.isValid = isValid;
    }

    public String[] getMessages() {
        return messages.clone();
    }

    public void setMessages(String[] messages) {
        this.messages = messages.clone();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
