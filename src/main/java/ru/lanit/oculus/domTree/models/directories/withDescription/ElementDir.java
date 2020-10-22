package ru.lanit.oculus.domTree.models.directories.withDescription;

import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.models.directories.elementTypes.PropertiesDirectory;
import ru.lanit.oculus.domTree.models.json.ElementJson;

import java.io.File;

/**
 * Директория, которая содержит описание элемента
 */
public class ElementDir extends DirectoryWithDescription {

    //является ли блок общим
    private boolean isCommon;
    //json с описанием элемента
    private ElementJson elementJson;
    //свойства (состояния) элемента
    private PropertiesDirectory props;

    public ElementDir(File file) {
        super(file);
        elementJson = GsonUtil.deserializeElement(file);
        setDisplayedName(elementJson.getName());
        setAbsolutePathToDir(file);
    }

    public boolean isCommon() {
        return isCommon;
    }

    public void setCommon(boolean common) {
        isCommon = common;
    }

    public ElementJson getElementJson() {
        return elementJson;
    }

    public void setElementJson(ElementJson elementJson) {
        this.elementJson = elementJson;
    }

    public PropertiesDirectory getProps() {
        return props;
    }

    public void setProps(PropertiesDirectory props) {
        this.props = props;
    }
}
