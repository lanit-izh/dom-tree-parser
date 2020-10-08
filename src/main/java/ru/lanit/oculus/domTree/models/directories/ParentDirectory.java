package ru.lanit.oculus.domTree.models.directories;

import java.io.File;

/**
 * Интерфейс для директорий, который содержат в себе директории
 */
public interface ParentDirectory {

    /**
     * Метод получает и сеттит в поля дочерн-ию/ии директор-ию/ии
     *
     * @param parentDir - сама (родительская) директория
     */
    void setChildDir(File parentDir);

}