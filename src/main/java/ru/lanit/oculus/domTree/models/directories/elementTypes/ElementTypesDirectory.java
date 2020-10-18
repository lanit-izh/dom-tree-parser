package ru.lanit.oculus.domTree.models.directories.elementTypes;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.directories.AbstractDirectory;
import ru.lanit.oculus.domTree.models.directories.ContainsJson;
import ru.lanit.oculus.domTree.models.json.ElementTypesJson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ElementTypesDirectory extends AbstractDirectory implements ContainsJson {

    private ElementTypesJson elementTypesJson;
    private List<ElementType> elementTypeList;

    public ElementTypesDirectory(File directory) throws Exception {
        elementTypesJson = GsonUtil.deserializeTypes(getJsonContent(directory));
        elementTypeList = initTypeList(directory);
        setDisplayedName(Singleton.TYPES_DIR_DISPLAY_NAME);
    }

    public ElementTypesJson getElementTypesJson() {
        return elementTypesJson;
    }

    public void setElementTypesJson(ElementTypesJson elementTypesJson) {
        this.elementTypesJson = elementTypesJson;
    }

    public List<ElementType> getElementTypeList() {
        return elementTypeList;
    }

    public void setElementTypeList(List<ElementType> elementTypeList) {
        this.elementTypeList = elementTypeList;
    }

    private List<ElementType> initTypeList(File typesDir) throws Exception {
        int jsonTypesCount = elementTypesJson
                .getElementTypes()
                .size();
        int dirsCount = FileUtil.getChildren(typesDir).size() - 1;
        if (jsonTypesCount != dirsCount) {
            throw new Exception("Ошибка");
        }
        List<ElementType> types = new ArrayList<>();
        elementTypesJson.getElementTypes().forEach(type -> {
            FileUtil.getChildren(typesDir).forEach(parentFile -> {
                if (parentFile.getName().equals(type.getType() ) && parentFile.isDirectory()) {
                    ElementType typeDir = null;
                    try {
                        typeDir = new ElementType(parentFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    types.add(typeDir);
                }
            });
        });
        if (types.size() != dirsCount) {
            throw new Exception("Ошибка");
        }
        return types;
    }

    public ElementType getElementTypeByTypeName(String name) {
        return elementTypeList
                .stream()
                .filter(type -> type.getTypeJson().getType().equals(name))
                .findFirst()
                .orElse(null);
    }
}
