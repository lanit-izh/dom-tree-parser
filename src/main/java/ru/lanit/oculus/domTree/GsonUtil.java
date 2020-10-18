package ru.lanit.oculus.domTree;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.lanit.oculus.domTree.models.json.*;

/**
 * Утиль для сериализации/десериализации объектов/json с помощью Gson
 */
public class GsonUtil {

    /**
     * Создает и возвращает Gson с помощью билдера
     *
     * @return      -   Gson
     */
    public static Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        return builder.create();
    }

    /**
     * Десериализует json-файл с описанием страницы
     *
     * @param json      -   содержимое json-файла
     *
     * @return          -   класс-модель, описывающая json
     */
    public static PageJson deserializePage(String json) {
        return getGson().fromJson(json, PageJson.class);
    }

    /**
     * Десериализует json-файл с опписанием элемента
     *
     * @param json      -   содержимое json-файла
     *
     * @return          -   класс-модель, описывающая json
     */
    public static ElementJson deserializeElement(String json) {
        return getGson().fromJson(json, ElementJson.class);
    }

    /**
     * Десериализует json-файл с опписанием блока
     *
     * @param json      -   содержимое json-файла
     *
     * @return          -   класс-модель, описывающая json
     */
    public static BlockJson deserializeBlock(String json) {
        return getGson().fromJson(json, BlockJson.class);
    }

    public static TypeJson deserializeType(String json) {
        return getGson().fromJson(json, TypeJson.class);
    }

    public static ElementTypesJson deserializeTypes(String json) {
        return getGson().fromJson(json, ElementTypesJson.class);
    }
}
