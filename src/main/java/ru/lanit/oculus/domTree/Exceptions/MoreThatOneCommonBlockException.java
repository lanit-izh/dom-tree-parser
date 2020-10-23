package ru.lanit.oculus.domTree.Exceptions;

/**
 * По id найдено >1 блока в папке common
 */
public class MoreThatOneCommonBlockException extends RuntimeException {

    public MoreThatOneCommonBlockException(String id, String directoryPath) {
        super(String.format(ExceptionMessages.MORE_THAT_ONE_COMMON_BLOCK, id, directoryPath));
    }
}
