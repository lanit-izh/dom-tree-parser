package ru.lanit.oculus.domTree.models.directories.withDirectories;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.directories.withDescription.ElementDir;

import java.io.File;
import java.util.List;

/**
 * Директория, которая содержит директории с описанием элементов
 * Не содержит в себе json-файла
 */
public class ElementsDir extends DirectoryWithDirectories {

    private List<ElementDir> elementsDirList;

    public ElementsDir(File parentDir) {
        super(parentDir);
        setDisplayedName(Singleton.ELEMENTS_DIR_DISPLAY_NAME);
    }

    public ElementsDir(List<ElementDir> elementDirs) {
        elementsDirList = elementDirs;
        setDisplayedName(Singleton.ELEMENTS_DIR_DISPLAY_NAME);
    }

    public List<ElementDir> getElementsDirList() {
        return elementsDirList;
    }

    public void addCommonElements(List<ElementDir> commonElements) {
        elementsDirList.addAll(commonElements);
    }

    @Override
    public void setChildDir(File parentDir) {
        elementsDirList = FileUtil.parseElementsDir(parentDir);
    }

}
