package ru.lanit.oculus.domTree.models.directories.withDirectories;

import ru.lanit.oculus.domTree.models.directories.ParentDirectory;

import java.io.File;

/**
 * Абстрактный класс для директорий, которые содержат в себе другие директории
 * (не содержат в себе json-файла)
 */
public abstract class DirectoryWithDirectories implements ParentDirectory {
    
    public DirectoryWithDirectories(File parentDir) {
        setChildDir(parentDir);
    }

    protected DirectoryWithDirectories() {
    }
}
