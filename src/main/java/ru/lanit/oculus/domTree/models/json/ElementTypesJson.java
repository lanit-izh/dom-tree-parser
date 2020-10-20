package ru.lanit.oculus.domTree.models.json;

import java.util.List;

/**
 * Описывает json для директории, которая содержит все типы элементов
 */
public class ElementTypesJson {

    private List<TypeJson> elementTypes;

    public List<TypeJson> getElementTypes() {
        return elementTypes;
    }

    public void setElementTypes(List<TypeJson> elementTypes) {
        this.elementTypes = elementTypes;
    }

}
