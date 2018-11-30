package com.pacific.core.schemas.objects;

public class Attribute {
    private String name;
    private String description;
    private Type dataType;
    private boolean required;
    private boolean creatable;
    private boolean updatable;
    private boolean generated;
    private boolean multivalued;
    private Schema subSchema;
    private String refs;

    public Type getDataType() {
        return dataType;
    }

    public void setDataType(Type dataType) {
        this.dataType = dataType;
    }

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

    public Schema getSubSchema() {
        return subSchema;
    }

    private void setSubSchema(Schema subSchema) {
        this.subSchema = subSchema;
    }

    public String getRefs() {
        return refs;
    }

    private void setRefs(String refs) {
        this.refs = refs;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public enum Type{
        STRING, BOOLEAN, INTEGER, LONG, EMBEDDED;
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

        public AttributeBuilder withType(Type dataType) {
            attribute.setDataType(dataType);
            return this;
        }

        public AttributeBuilder withSubSchema(Schema subSchema) {
            attribute.setSubSchema(subSchema);
            return this;
        }

        public AttributeBuilder withRefs(String refs) {
            attribute.setRefs(refs);
            return this;
        }

        public AttributeBuilder withDescription(String description) {
            attribute.setDescription(description);
            return this;
        }

        public Attribute build(){
            return this.attribute;
        }
    }
}
