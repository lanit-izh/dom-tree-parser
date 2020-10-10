package ru.lanit.oculus.domTree.models.directories.withDirectories;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.Singleton;

import java.io.File;

/**
 * Главная директория, которая содержит в себе все page objects
 */
public class RootDir extends DirectoryWithDirectories {

    private CommonDir common;
    private PagesDir pagesDirectory;

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

    @Override
    public void setChildDir(File parentDir) {
        for (File file : FileUtil.getChildren(parentDir)) {
            if (file.getName().equals(Singleton.COMMON_DIR_NAME) && file.isDirectory()) {
                common = new CommonDir(file);
            } else if (file.getName().equals(Singleton.PAGES_DIR_NAME) && file.isDirectory()) {
                pagesDirectory = new PagesDir(file);
            }
        }
    }

}
