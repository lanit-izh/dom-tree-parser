package ru.lanit.oculus.domTree.Exceptions;

/**
 * Не найдено изображение для компонента (ищется png)
 */
public class PngNotFoundException extends RuntimeException {

    public PngNotFoundException(String directoryPath) {
        super(String.format(ExceptionMessages.PNG_IN_DIRECTORY_NOT_FOUND, directoryPath));
    }
}
