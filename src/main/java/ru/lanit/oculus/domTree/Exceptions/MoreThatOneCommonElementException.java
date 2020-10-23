package ru.lanit.oculus.domTree.Exceptions;

/**
 * По id найдено >1 элемента в папке common
 */
public class MoreThatOneCommonElementException extends RuntimeException {

    public MoreThatOneCommonElementException(String id, String directoryPath) {
        super(String.format(ExceptionMessages.MORE_THAT_ONE_COMMON_ELEMENT, id, directoryPath));
    }
}
