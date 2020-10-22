package ru.lanit.oculus.domTree.models.directories.elementTypes;

import ru.lanit.oculus.domTree.Exceptions.PropsNotDescribedException;
import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.Property;
import ru.lanit.oculus.domTree.models.directories.AbstractDirectory;
import ru.lanit.oculus.domTree.models.json.TypeJson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Описывает тип элемента из директории с типами
 * Содержит json с описанием типа
 */
public class ElementTypeDirectory extends AbstractDirectory {

    //json с описанием
    private TypeJson typeJson;
    //свойства (состояния) типа
    private PropertiesDirectory props;

    public ElementTypeDirectory(File elementTypeDirectory) {
        setAbsolutePathToDir(elementTypeDirectory.getAbsolutePath());
        typeJson = GsonUtil.deserializeType(elementTypeDirectory);
        setDisplayedName(typeJson.getType());
        props = new PropertiesDirectory(initProps(elementTypeDirectory));
    }

    public TypeJson getTypeJson() {
        return typeJson;
    }

    public void setTypeJson(TypeJson typeJson) {
        this.typeJson = typeJson;
    }

    public PropertiesDirectory getProps() {
        return props;
    }

    public void setProps(PropertiesDirectory props) {
        this.props = props;
    }

    /**
     * Задает свойства (состояния) для типа
     *
     * @param directory -   директория типа элемента
     * @return -   список свойств элемента
     */
    private List<Property> initProps(File directory) {
        if (typeJson.getProperties() != null) {
            File propsDirectory = FileUtil.findDirectoryByNameAndGetNotNull(directory, Singleton.PROPS_DIR_NAME);
            List<Property> props = new ArrayList<>();
            typeJson
                    .getProperties()
                    .forEach(prop -> {
                        File image = FileUtil.findFileByFullName(propsDirectory, prop.getImageName(), "png");
                        props.add(new Property(prop.getName(), image.getAbsolutePath()));
                    });
            return props;
        } else {
            if (FileUtil.findDirectoryByName(directory, Singleton.PROPS_DIR_NAME) != null) {
                throw new PropsNotDescribedException(this.getAbsolutePathToDir());
            } else {
                return null;
            }
        }
    }
}
