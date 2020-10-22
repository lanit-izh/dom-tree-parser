package ru.lanit.oculus.domTree.Exceptions;

/**
 * Ошибка при инициализации типов - количество типов в json != количеству типов в директории
 */
public class TypeInitException extends RuntimeException {

    public TypeInitException(String typesDirectoryPath) {
        super(String.format(ExceptionMessages.TYPE_INIT_ERROR, typesDirectoryPath));
    }

}
