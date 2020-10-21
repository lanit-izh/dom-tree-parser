package ru.lanit.oculus.domTree.Exceptions;

public class CommonElementNotFoundException extends RuntimeException {

    public CommonElementNotFoundException(String id, String directoryPath) {
        super(String.format(ExceptionMessages.COMMON_ELEMENT_NOT_FIND, id, directoryPath));
    }
}
