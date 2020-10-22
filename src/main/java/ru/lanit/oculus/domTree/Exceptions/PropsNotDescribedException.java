package ru.lanit.oculus.domTree.Exceptions;

/**
 * Не найдено описание свойств в json-файле (если есть папка properties)
 */
public class PropsNotDescribedException extends RuntimeException {

    public PropsNotDescribedException(String typeDirectoryPath) {
        super(String.format(ExceptionMessages.PROPS_ARE_NOT_DESCRIBED_ERROR, typeDirectoryPath));
    }

}
