package ru.lanit.oculus.domTree.models.json;

import ru.lanit.oculus.domTree.models.json.markup.Markup;

import java.util.List;

/**
 * Описание json-файла для элементов
 */
public class ElementJson extends DefaultJson {

    private String type;
    private Markup markup;
    private String use_mask="false";

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

    public String getUse_mask() {
        return use_mask;
    }

    public void setUse_mask(String use_mask) {
        this.use_mask = use_mask;
    }
}
