package ru.lanit.oculus.domTree.models.directories.withDescroption;

import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.models.json.ElementJson;
import java.io.File;

/**
 * Директория, которая содержит описание элемента
 */
public class ElementDir extends DirectoryWithDescription {

    private ElementJson elementJson;

    public ElementDir(File file, String fileName) {
        super(file, fileName);
        elementJson = GsonUtil.deserializeElement(getJsonContent(file, fileName));
    }


    public ElementJson getElementJson() {
        return elementJson;
    }

    public void setElementJson(ElementJson elementJson) {
        this.elementJson = elementJson;
    }

}
