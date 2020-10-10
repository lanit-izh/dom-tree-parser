package ru.lanit.oculus.domTree.models.directories.withDescription;

import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.models.json.ElementJson;

import java.io.File;

/**
 * Директория, которая содержит описание элемента
 */
public class ElementDir extends DirectoryWithDescription {

    private boolean isCommon;
    private ElementJson elementJson;

    public ElementDir(File file, String fileName) {
        super(file, fileName);
        elementJson = GsonUtil.deserializeElement(getJsonContent(file, fileName));
        setDisplayedName(elementJson.getName());
    }

    public ElementDir(File file, String fileName, boolean isCommon) {
        super(file, fileName);
        elementJson = GsonUtil.deserializeElement(getJsonContent(file, fileName));
        setDisplayedName(elementJson.getName());
        setCommon(isCommon);
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

}
