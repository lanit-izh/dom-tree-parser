package ru.lanit.oculus.domTree.models.json;

import ru.lanit.oculus.domTree.models.json.markup.Markup;

import java.util.List;

/**
 * Описание json-файла для элементов
 */
public class ElementJson extends DefaultJson {

    private String type;
    private Markup markup;

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

    public Markup getMarkup() {
        return markup;
    }

    public void setMarkup(Markup markup) {
        this.markup = markup;
    }
}
