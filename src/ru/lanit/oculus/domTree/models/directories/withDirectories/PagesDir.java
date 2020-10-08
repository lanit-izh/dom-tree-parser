package ru.lanit.oculus.domTree.models.directories.withDirectories;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.models.directories.withDescroption.PageDir;
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
    }

    public List<PageDir> getPageDirectories() {
        return pageDirectories;
    }

    @Override
    public void setChildDir(File parentDir)   {
        pageDirectories = FileUtil.parsePagesDir(parentDir);
    }

}
