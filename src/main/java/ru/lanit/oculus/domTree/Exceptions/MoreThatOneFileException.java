package ru.lanit.oculus.domTree.Exceptions;

/**
 * Найдено >1 файла
 */
public class MoreThatOneFileException extends RuntimeException {

    public MoreThatOneFileException(String directoryPath, String file) {
        super(String.format(ExceptionMessages.MORE_THAT_ONE_FILE, directoryPath, file));
    }
}
