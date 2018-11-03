package com.pacific.core.schemas.objects;

/**
 * Created by vakamat on 11/1/2018.
 */
public class Attribute {
    private String name;
    private boolean required;
    private boolean creatable;
    private boolean updatable;
    private boolean generated;
    private boolean multivalued;

    /*
     * Always use builder for instance of this Class.
     */
    private Attribute() {
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    private void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isCreatable() {
        return creatable;
    }

    private void setCreatable(boolean creatable) {
        this.creatable = creatable;
    }

    public boolean isUpdatable() {
        return updatable;
    }

    private void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    public boolean isGenerated() {
        return generated;
    }

    private void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public boolean isMultivalued() {
        return multivalued;
    }

    private void setMultivalued(boolean multivalued) {
        this.multivalued = multivalued;
    }

    public static class AttributeBuilder {
        Attribute attribute;

        public AttributeBuilder(){
            attribute = new Attribute();
        }

        public AttributeBuilder withName(String name) {
            attribute.setName(name);
            return this;
        }

        public AttributeBuilder withRequired(boolean required) {
            attribute.setRequired(required);
            return this;
        }
        public AttributeBuilder withCreatable(boolean creatable) {
            attribute.setCreatable(creatable);
            return this;
        }
        public AttributeBuilder withUpdatable(boolean updatable) {
            attribute.setUpdatable(updatable);
            return this;
        }
        public AttributeBuilder withGenerated(boolean generated) {
            attribute.setGenerated(generated);
            return this;
        }
        public AttributeBuilder withMultivalued(boolean multivalued) {
            attribute.setMultivalued(multivalued);
            return this;
        }

        public Attribute build(){
            return this.attribute;
        }
    }
}
