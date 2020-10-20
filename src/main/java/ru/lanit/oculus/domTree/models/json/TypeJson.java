package ru.lanit.oculus.domTree.models.json;

import java.util.List;

/**
 * Описывает тип элемента
 */
public class TypeJson {

    private String type;
    private List<PropertyJson> properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PropertyJson> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyJson> properties) {
        this.properties = properties;
    }

}
