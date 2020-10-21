package ru.lanit.oculus.domTree.Exceptions;

public class FileNotFoundCustomException extends RuntimeException {

    public FileNotFoundCustomException(String file, String directoryPath) {
        super(String.format(ExceptionMessages.FILE_NOT_FIND, file, directoryPath));
    }
}
