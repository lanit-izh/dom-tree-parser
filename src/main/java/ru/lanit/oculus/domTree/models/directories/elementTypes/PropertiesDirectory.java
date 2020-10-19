package ru.lanit.oculus.domTree.models.directories.elementTypes;

import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.Property;
import ru.lanit.oculus.domTree.models.directories.AbstractDirectory;
import java.util.List;
import java.util.Map;

public class PropertiesDirectory extends AbstractDirectory {

    private final List<Property> properties;

    public PropertiesDirectory(List<Property> parsedProps) {
        setDisplayedName(Singleton.PROPERTIES_DIR_DISPLAY_NAME);
        properties = parsedProps;
    }

    public List<Property> getProperties() {
        return properties;
    }

}
