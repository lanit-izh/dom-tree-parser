package ru.lanit.oculus.domTree.models.directories;

import ru.lanit.oculus.domTree.FileUtil;

import java.io.File;

public interface ContainsJson {

    /**
     * Получает содержание файла-json
     *
     * @param file      -   сама директория (в которой лежит json)
     *
     * @return          -   содержание json-на String'ом
     */
    default String getJsonContent (File file) {
        return FileUtil.getJsonContent(file);
    }
}
