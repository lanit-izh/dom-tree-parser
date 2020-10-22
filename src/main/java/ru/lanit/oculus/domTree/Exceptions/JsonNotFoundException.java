package ru.lanit.oculus.domTree.Exceptions;

/**
 * Не найден json-файл с описанием компонента
 */
public class JsonNotFoundException extends RuntimeException {

    public JsonNotFoundException(String directoryPath) {
        super(String.format(ExceptionMessages.JSON_IN_DIRECTORY_NOT_FOUND, directoryPath));
    }
}
