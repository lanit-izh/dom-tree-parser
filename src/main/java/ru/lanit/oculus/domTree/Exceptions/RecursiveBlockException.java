package ru.lanit.oculus.domTree.Exceptions;

/**
 * Блок содержит в себе самого себя (может быть в общих блоках)
 */
public class RecursiveBlockException extends RuntimeException {

    public RecursiveBlockException(String id, String directoryPath) {
        super(String.format(ExceptionMessages.RECURSIVE_BLOCK, id, directoryPath));
    }
}
