package ru.lanit.oculus.domTree.Exceptions;

public class JsonNotFoundException extends RuntimeException {

    public JsonNotFoundException(String directoryPath) {
        super(String.format(ExceptionMessages.JSON_IN_DIRECTORY_NOT_FIND, directoryPath));
    }
}
