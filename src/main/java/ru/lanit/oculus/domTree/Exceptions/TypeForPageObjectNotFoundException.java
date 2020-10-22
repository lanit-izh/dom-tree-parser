package ru.lanit.oculus.domTree.Exceptions;

/**
 * Не найден тип в директории типом для блоков/элементов
 */
public class TypeForPageObjectNotFoundException extends RuntimeException {

    public TypeForPageObjectNotFoundException(String directoryWithPageObjectPath, String typeName, String typesDirectoryPath) {
        super(String.format(ExceptionMessages.TYPE_NOT_FOUND, directoryWithPageObjectPath, typeName, typesDirectoryPath));
    }

}
