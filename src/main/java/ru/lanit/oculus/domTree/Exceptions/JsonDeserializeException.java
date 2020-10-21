package ru.lanit.oculus.domTree.Exceptions;

public class JsonDeserializeException extends RuntimeException {

    public JsonDeserializeException(String type) {
        super(String.format(ExceptionMessages.JSON_DESERIALIZE_ERROR, type));
    }
}
