package ru.lanit.oculus.domTree;

/**
 * Класс, который используется вместо файла .properties
 */
public class Singleton {

    //название директорий в файловой системе
    public static final String BLOCKS_DIR_NAME = "Blocks";
    public static final String ELEMENTS_DIR_NAME = "Elements";
    public static final String ROOT_DIR_NAME = "repo";
    public static final String COMMON_DIR_NAME = "Common";
    public static final String TYPES_DIR_NAME = "ElementTypes";
    public static final String PAGES_DIR_NAME = "Pages";
    public static final String PROPS_DIR_NAME = "properties";

    //отображаемые в xpath названия директорий
    public static final String ROOT_DIR_DISPLAY_NAME = "repo";
    public static final String COMMON_DIR_DISPLAY_NAME = "Общие компоненты";
    public static final String TYPES_DIR_DISPLAY_NAME = "Типы компонентов";
    public static final String PAGES_DIR_DISPLAY_NAME = "Страницы";
    public static final String BLOCKS_DIR_DISPLAY_NAME = "Блоки";
    public static final String ELEMENTS_DIR_DISPLAY_NAME = "Элементы";
    public static final String PROPERTIES_DIR_DISPLAY_NAME = "Состояния";

    //шаблон для формирования xpath
    public static final String XPATH_TEMPLATE = "%s->%s";
}
