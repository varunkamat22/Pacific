package com.pacific.core.schemas.objects;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by vakamat on 11/1/2018.
 */
public class Schema {
    private final String id;
    private final String name;
    private final boolean publishSchema;
    private final List<Attribute> attributes;

    public Schema(String id, String name, boolean publishSchema, List<Attribute> attributes) {
        this.id = id;
        this.name = name;
        this.attributes = attributes;
        this.publishSchema = publishSchema;
    }

    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Schema) {
            if(StringUtils.isEmpty(((Schema)obj).getId()) || StringUtils.isEmpty(this.getId())) {
                return false;
            }else {
                return ((Schema)obj).getId().equals(this.getId());
            }
        }
        return false;
    }
}
