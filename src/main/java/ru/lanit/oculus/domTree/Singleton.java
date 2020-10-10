package ru.lanit.oculus.domTree;

/**
 * Класс, который используется вместо файла .properties
 */
public class Singleton {

    //название файлов
    public static final String BLOCK_FILES_NAME = "block";
    public static final String ELEMENT_FILES_NAME = "element";
    public static final String PAGE_FILES_NAME = "page";

    //название директорий в файловой системе
    public static final String BLOCKS_DIR_NAME = "Blocks";
    public static final String ELEMENTS_DIR_NAME = "Elements";
    public static final String ROOT_DIR_NAME = "repo";
    public static final String COMMON_DIR_NAME = "Common";
    public static final String PAGES_DIR_NAME = "Pages";

    //отображаемые в xpath названия директорий
    public static final String BLOCKS_DIR_DISPLAY_NAME = "Блоки";
    public static final String COMMON_DIR_DISPLAY_NAME = "Общие компоненты";
    public static final String ELEMENTS_DIR_DISPLAY_NAME = "Элементы";
    public static final String ROOT_DIR_DISPLAY_NAME = "repo";
    public static final String PAGES_DIR_DISPLAY_NAME = "Страницы";

    //шаблон для формирования xpath
    public static final String XPATH_TEMPLATE = "%s->%s";
}
