package ru.lanit.oculus.domTree.models.directories.withDirectories;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.models.directories.withDescroption.ElementDir;
import java.io.File;
import java.util.List;

/**
 * Директория, которая содержит директории с описанием элементов
 * Не содержит в себе json-файла
 */
public class ElementsDir extends DirectoryWithDirectories {

    private List<ElementDir> elementsDirs;

    public ElementsDir(File parentDir) {
        super(parentDir);
    }

    public ElementsDir(List<ElementDir> elementDirs) {
        elementsDirs = elementDirs;
    }

    public List<ElementDir> getElementsDirs() {
        return elementsDirs;
    }

    public void addCommonElements(List<ElementDir> commonElements) {
        elementsDirs.addAll(commonElements);
    }

    @Override
    public void setChildDir(File parentDir) {
        elementsDirs = FileUtil.parseElementsDir(parentDir);
    }

}
