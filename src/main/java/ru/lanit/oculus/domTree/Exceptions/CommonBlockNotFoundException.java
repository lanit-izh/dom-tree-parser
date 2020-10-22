package ru.lanit.oculus.domTree.Exceptions;

/**
 * Не найден общий блок по id. Id берется в json-файле страницы/блока, ищется в директории Common/block
 */
public class CommonBlockNotFoundException extends RuntimeException {

    public CommonBlockNotFoundException(String id, String directoryPath) {
        super(String.format(ExceptionMessages.COMMON_BLOCK_NOT_FOUND, id, directoryPath));
    }
}
