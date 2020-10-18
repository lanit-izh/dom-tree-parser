package ru.lanit.oculus.domTree.models.directories.elementTypes;

import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.directories.AbstractDirectory;

import java.util.Map;

public class PropertiesDirectory extends AbstractDirectory {

    //Мапа со свойстами вида: Название - полный путь к картинке
    private Map<String, String> properties;

    public PropertiesDirectory(Map<String, String> parsedProps) {
        setDisplayedName(Singleton.PROPERTIES_DIR_DISPLAY_NAME);
        properties = parsedProps;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

}
