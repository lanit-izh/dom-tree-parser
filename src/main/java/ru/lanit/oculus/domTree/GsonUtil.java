package ru.lanit.oculus.domTree;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import ru.lanit.oculus.domTree.Exceptions.JsonDeserializeException;
import ru.lanit.oculus.domTree.models.json.*;

import java.io.File;
import java.lang.reflect.Type;

/**
 * Утиль для сериализации/десериализации объектов/json с помощью Gson
 */
public class GsonUtil {

    /**
     * Создает и возвращает Gson с помощью билдера
     *
     * @return -   Gson
     */
    public static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        return builder.create();
    }

    /**
     * Десериализует json-файл с описанием страницы
     *
     * @param directory         -   директория, содержащая json
     *
     * @return -   класс-модель, описывающая json
     */
    public static PageJson deserializePage(File directory) {
        Type type = new TypeToken<PageJson>() {}.getType();
        return (PageJson) deserializeObject(directory, type);
    }

    /**
     * Десериализует json-файл с описанием элемента
     *
     * @param directory         -   директория, содержащая json
     *
     * @return -   класс-модель, описывающая json
     */
    public static ElementJson deserializeElement(File directory) {
        Type type = new TypeToken<ElementJson>() {}.getType();
        return (ElementJson) deserializeObject(directory, type);
    }

    /**
     * Десериализует json-файл с описанием блока
     *
     * @param directory         -   директория, содержащая json
     *
     * @return -   класс-модель, описывающая json
     */
    public static BlockJson deserializeBlock(File directory) {
        Type type = new TypeToken<BlockJson>() {}.getType();
        return (BlockJson) deserializeObject(directory, type);
    }

    /**
     * Десериализует json-файл с описанием типа
     *
     * @param directory         -   директория, содержащая json
     *
     * @return -   класс-модель, описывающая json
     */
    public static TypeJson deserializeType(File directory) {
        Type type = new TypeToken<TypeJson>() {}.getType();
        return (TypeJson) deserializeObject(directory, type);
    }

    /**
     * Десериализует json-файл с описанием типов элементов
     *
     * @param directory         -   директория, содержащая json
     *
     * @return -   класс-модель, описывающая json
     */
    public static ElementTypesJson deserializeTypes(File directory) {
        Type type = new TypeToken<ElementTypesJson>() {}.getType();
        return (ElementTypesJson) deserializeObject(directory, type);
    }

    /**
     * Метод для десериализации json по типу
     *
     * @param directory         -   директория, содержащая json
     * @param type              -   тип для десериализации
     *
     * @return                  -
     */
    private static Object deserializeObject(File directory, Type type) {
        try {
            String json = FileUtil.getJsonContent(directory);
            return getGson().fromJson(json, type);
        } catch (JsonSyntaxException e) {
            throw new JsonDeserializeException(directory.getAbsolutePath());
        }
    }
}
