package ru.lanit.oculus.domTree.Exceptions;

/**
 * Не найден общий элемент по id. Id берется в json-файле страницы/блока, ищется в директории Common/block
 */
public class CommonElementNotFoundException extends RuntimeException {

    public CommonElementNotFoundException(String id, String directoryPath) {
        super(String.format(ExceptionMessages.COMMON_ELEMENT_NOT_FOUND, id, directoryPath));
    }
}
