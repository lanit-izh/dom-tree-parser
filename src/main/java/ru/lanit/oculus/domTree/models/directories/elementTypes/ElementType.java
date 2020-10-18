package ru.lanit.oculus.domTree.models.directories.elementTypes;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.directories.AbstractDirectory;
import ru.lanit.oculus.domTree.models.directories.ContainsJson;
import ru.lanit.oculus.domTree.models.json.TypeJson;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ElementType extends AbstractDirectory implements ContainsJson {

    private TypeJson typeJson;
    private PropertiesDirectory props;

    public ElementType(File elementTypeDirectory) throws Exception {
        typeJson = GsonUtil.deserializeType(getJsonContent(elementTypeDirectory));
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

    private Map<String, String> initProps(File directory) throws Exception {
        if (typeJson.getProperties() != null) {
            int jsonPropsCount = typeJson.getProperties().size();
            File propsDirectory = FileUtil.findDirectoryByName(directory, Singleton.PROPS_DIR_NAME);
            if (jsonPropsCount > 0 && propsDirectory == null) {
                throw new Exception("Ошибка");
            }
            int imagePropsCount = FileUtil.getChildren(propsDirectory).size();
            if (jsonPropsCount != imagePropsCount) {
                throw new Exception("Ошибка");
            }
            Map<String, String> props = new HashMap<>();
            String imageExtension = ".png";
            typeJson.getProperties().forEach(prop -> {
                FileUtil.getChildren(propsDirectory).forEach(image -> {
                    if (image.getName().equals(prop.getImageName() + imageExtension)) {
                        props.put(prop.getName(), image.getAbsolutePath());
                    }
                });
            });
            if (props.size() != imagePropsCount) {
                throw new Exception("Ошибка");
            }
            return props;
        } else {
            if (FileUtil.findDirectoryByName(directory, Singleton.PROPS_DIR_NAME) != null) {
                throw new Exception("Ошибка");
            } else {
                return null;
            }
        }
    }
}
