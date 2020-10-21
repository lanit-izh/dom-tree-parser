package ru.lanit.oculus.domTree.models.directories.elementTypes;

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
public class ElementType extends AbstractDirectory {

    //json с описанием
    private TypeJson typeJson;
    //свойства (состояния) типа
    private PropertiesDirectory props;

    public ElementType(File elementTypeDirectory) {
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
     *                  
     * @return -   список свойств элемента
     */
    private List<Property> initProps(File directory) {
        if (typeJson.getProperties() != null) {
            int jsonPropsCount = typeJson.getProperties().size();
            File propsDirectory = FileUtil.findDirectoryByName(directory, Singleton.PROPS_DIR_NAME);
            if (jsonPropsCount > 0 && propsDirectory == null) {
                throw new RuntimeException("Ошибка");
            }
            int imagePropsCount = FileUtil.getChildren(propsDirectory).size();
            if (jsonPropsCount != imagePropsCount) {
                throw new RuntimeException("Ошибка");
            }
            List<Property> props = new ArrayList<>();
            typeJson.getProperties().forEach(prop -> {
                FileUtil.getChildren(propsDirectory).forEach(image -> {
                    if (image.getName().equals(prop.getImageName() + ".png")) {
                        props.add(new Property(prop.getName(), image.getAbsolutePath()));
                    }
                });
            });
            if (props.size() != imagePropsCount) {
                throw new RuntimeException("Ошибка");
            }
            return props;
        } else {
            if (FileUtil.findDirectoryByName(directory, Singleton.PROPS_DIR_NAME) != null) {
                throw new RuntimeException("Ошибка");
            } else {
                return null;
            }
        }
    }
}
