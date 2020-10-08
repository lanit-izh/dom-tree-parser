package ru.lanit.oculus.domTree.models.directories.withDescription;


import ru.lanit.oculus.domTree.FileUtil;

import java.io.File;

/**
 * Абстрактный класс для директорий, которые содержат в себе json-файл с описанием и изображение
 * (могут содержать в себе директории)
 */
public abstract class DirectoryWithDescription {

    private String pathToImage;

    public DirectoryWithDescription(File file, String fileName) {
        setPathToImage(file, fileName);
    }

    public String getPathToImage() {
        return pathToImage;
    }

    /**
     * Получает содержание файла-json
     *
     * @param file      -   сама директория (в которой лежит json)
     * @param fileName  -   название json-файла (без расширения)
     *
     * @return          -   содержание json-на String'ом
     */
    public String getJsonContent (File file, String fileName) {
        return FileUtil.getJsonContent(file, fileName);
    }

    public void setPathToImage(File file, String fileName) {
        pathToImage = file.getPath() + FileUtil.FILE_SEPARATOR + fileName + ".png";
    }
}
