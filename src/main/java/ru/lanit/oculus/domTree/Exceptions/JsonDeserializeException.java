package ru.lanit.oculus.domTree.Exceptions;

/**
 * Ошибка десериализации json-файла с класс-модель
 */
public class JsonDeserializeException extends RuntimeException {

    public JsonDeserializeException(String pathTojson) {
        super(String.format(ExceptionMessages.JSON_DESERIALIZE_ERROR, pathTojson));
    }
}
