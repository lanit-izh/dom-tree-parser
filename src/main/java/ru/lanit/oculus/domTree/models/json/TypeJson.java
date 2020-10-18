package ru.lanit.oculus.domTree.models.json;

import java.util.List;

public class TypeJson {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private List<PropertyJson> properties;

    public List<PropertyJson> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyJson> properties) {
        this.properties = properties;
    }
}
