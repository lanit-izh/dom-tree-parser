package ru.lanit.oculus.domTree.models.directories.withDirectories;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.directories.withDescription.PageDir;

import java.io.File;
import java.util.List;

/**
 * Директория, которая содержит директории с описанием страниц
 * Не содержит в себе json-файла
 */
public class PagesDir extends DirectoryWithDirectories {

    private List<PageDir> pageDirectories;

    public PagesDir(File parentDir) {
        super(parentDir);
        setDisplayedName(Singleton.PAGES_DIR_DISPLAY_NAME);
    }

    public List<PageDir> getPageDirectories() {
        return pageDirectories;
    }

    @Override
    public void setChildDir(File parentDir)   {
        pageDirectories = FileUtil.parsePagesDir(parentDir);
    }

}
