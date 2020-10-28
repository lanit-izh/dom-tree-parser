package ru.lanit.oculus.domTree.Exceptions;

/**
 * Повторяется id-шник в двух json-файлах
 */
public class IdDuplicateException extends RuntimeException {

    public IdDuplicateException(String id, String firstDirectoryPath, String secondDirectoryPath) {
        super(String.format(ExceptionMessages.ID_DUPLICATE_ERROR, id, firstDirectoryPath, secondDirectoryPath));
    }
}
