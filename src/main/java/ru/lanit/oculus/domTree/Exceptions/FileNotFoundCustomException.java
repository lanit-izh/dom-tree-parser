package ru.lanit.oculus.domTree.Exceptions;

/**
 * Не найден файд по имени (+расширение)
 */
public class FileNotFoundCustomException extends RuntimeException {

    public FileNotFoundCustomException(String file, String directoryPath) {
        super(String.format(ExceptionMessages.FILE_NOT_FOUND, file, directoryPath));
    }
}
