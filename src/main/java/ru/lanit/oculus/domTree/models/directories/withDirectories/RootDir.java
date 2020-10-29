package ru.lanit.oculus.domTree.models.directories.withDirectories;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.directories.elementTypes.ElementTypesDirectory;

import java.io.File;

/**
 * Главная директория, которая содержит в себе все page objects
 */
public class RootDir extends DirectoryWithDirectories {

    private CommonDir common;
    private PagesDir pagesDirectory;
    private ElementTypesDirectory elementTypesDirectory;

    public RootDir(File parentDir) {
        super(parentDir);
        setDisplayedName(Singleton.ROOT_DIR_DISPLAY_NAME);
    }

    public CommonDir getCommon() {
        return common;
    }

    public PagesDir getPagesDirectory() {
        return pagesDirectory;
    }

    public ElementTypesDirectory getElementTypesDirectory() {
        return elementTypesDirectory;
    }

    @Override
    public void setChildDir(File parentDir) {
        File commonDir = FileUtil.findDirectoryByName(parentDir, Singleton.COMMON_DIR_NAME);
        if (commonDir != null) {
            common = new CommonDir(commonDir);
        }
        File elementTypesDir = FileUtil.findDirectoryByName(parentDir, Singleton.TYPES_DIR_NAME);
        if (elementTypesDir != null) {
            elementTypesDirectory = new ElementTypesDirectory(elementTypesDir);
        }
        pagesDirectory = FileUtil.initPagesDir(parentDir);
    }

}
