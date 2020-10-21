package ru.lanit.oculus.domTree.Exceptions;

public class CommonBlockNotFoundException extends RuntimeException {

    public CommonBlockNotFoundException(String id, String directoryPath) {
        super(String.format(ExceptionMessages.COMMON_BLOCK_NOT_FIND, id, directoryPath));
    }
}
