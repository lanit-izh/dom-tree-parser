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

/**
 * Директория с типами элементов и json-файлом с описанием
 */
public class ElementTypesDirectory extends AbstractDirectory implements ContainsJson {

    //json с описанием
    private ElementTypesJson elementTypesJson;
    //список типов элементов
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

    /**
     * Задает список типов элементов по json-файлу и списку директорий с типами
     *
     * @param typesDir              -   директория с типами
     *
     * @return                      -   список типов элементов
     */
    private List<ElementType> initTypeList(File typesDir) {
        int jsonTypesCount = elementTypesJson
                .getElementTypes()
                .size();
        int dirsCount = FileUtil.getChildren(typesDir).size() - 1;
        if (jsonTypesCount != dirsCount) {
            throw new RuntimeException("Ошибка");
        }
        List<ElementType> types = new ArrayList<>();
        elementTypesJson.getElementTypes().forEach(type -> {
            FileUtil.getChildren(typesDir).forEach(parentFile -> {
                if (parentFile.getName().equals(type.getType() ) && parentFile.isDirectory()) {
                    ElementType typeDir = new ElementType(parentFile);
                    types.add(typeDir);
                }
            });
        });
        if (types.size() != dirsCount) {
            throw new RuntimeException("Ошибка");
        }
        return types;
    }

    /**
     * Ищет и возвращает тип по названию
     *
     * @param name      -   название типа
     *
     * @return          -   тип элемента
     */
    public ElementType getElementTypeByTypeName(String name) {
        return elementTypeList
                .stream()
                .filter(type -> type.getTypeJson().getType().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("В директории %s нет типа с именем \"%s\"", Singleton.TYPES_DIR_NAME, name)));
    }

}
