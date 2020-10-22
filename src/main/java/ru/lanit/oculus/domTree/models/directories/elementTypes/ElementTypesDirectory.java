package ru.lanit.oculus.domTree.models.directories.elementTypes;

import ru.lanit.oculus.domTree.Exceptions.TypeForPageObjectNotFoundException;
import ru.lanit.oculus.domTree.Exceptions.TypeInitException;
import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.directories.AbstractDirectory;
import ru.lanit.oculus.domTree.models.json.ElementTypesJson;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Директория с типами элементов и json-файлом с описанием
 */
public class ElementTypesDirectory extends AbstractDirectory {

    //json с описанием
    private ElementTypesJson elementTypesJson;
    //список типов элементов
    private List<ElementTypeDirectory> elementTypeList;

    public ElementTypesDirectory(File directory) {
        setAbsolutePathToDir(directory.getAbsolutePath());
        elementTypesJson = GsonUtil.deserializeTypes(directory);
        elementTypeList = initTypeList(directory);
        setDisplayedName(Singleton.TYPES_DIR_DISPLAY_NAME);
    }

    public ElementTypesJson getElementTypesJson() {
        return elementTypesJson;
    }

    public void setElementTypesJson(ElementTypesJson elementTypesJson) {
        this.elementTypesJson = elementTypesJson;
    }

    public List<ElementTypeDirectory> getElementTypeList() {
        return elementTypeList;
    }

    public void setElementTypeList(List<ElementTypeDirectory> elementTypeList) {
        this.elementTypeList = elementTypeList;
    }

    /**
     * Задает список типов элементов по json-файлу и списку директорий с типами
     *
     * @param typesDir      -   директория с типами
     *
     * @return              -   список типов элементов
     */
    private List<ElementTypeDirectory> initTypeList(File typesDir) {
        int jsonTypesCount = elementTypesJson
                .getElementTypes()
                .size();
        int dirsCount = FileUtil.getChildren(typesDir).size() - 1;
        if (jsonTypesCount != dirsCount) {
            throw new TypeInitException(this.getAbsolutePathToDir());
        }
        List<ElementTypeDirectory> types = new ArrayList<>();
        elementTypesJson
                .getElementTypes()
                .forEach(type -> {
                    File typeDir = FileUtil.findDirectoryByNameAndGetNotNull(typesDir, type.getType());
                    types.add(new ElementTypeDirectory(typeDir));
                });
        return types;
    }

    /**
     * Ищет и возвращает тип по названию
     *
     * @param name                  -   название типа
     * @param pathToPageObject      -   путь до директории с пейдж-обджектом, для которого ищем тип
     *
     * @return                      -   тип элемента
     */
    public ElementTypeDirectory getElementTypeByTypeName(String name, String pathToPageObject) {
        return elementTypeList
                .stream()
                .filter(type -> type
                        .getTypeJson()
                        .getType()
                        .equals(name))
                .findFirst()
                .orElseThrow(() -> new TypeForPageObjectNotFoundException(pathToPageObject, name, this.getAbsolutePathToDir()));
    }

}
