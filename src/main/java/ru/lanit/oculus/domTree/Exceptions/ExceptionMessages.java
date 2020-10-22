package ru.lanit.oculus.domTree.Exceptions;

/**
 * Класс с сообщениями для экспешенов
 */
public class ExceptionMessages {

    public static final String JSON_IN_DIRECTORY_NOT_FOUND = "\nJson-файл с описанием компонента не найден в директории \"%s\"";
    public static final String PNG_IN_DIRECTORY_NOT_FOUND = "\nИзображение (.png) компонента не найдено в директории \"%s\"";
    public static final String FILE_NOT_FOUND = "\nФайл \"%s\" не найден в директории \"%s\"\nПроверьте правильность json-файла с описанием";
    public static final String COMMON_BLOCK_NOT_FOUND = "\nНе найден общий блок с id=\"%s\" для компонента %s";
    public static final String COMMON_ELEMENT_NOT_FOUND = "\nНе найден общий элемент с id=\"%s\" для компонента %s";
    public static final String TYPE_NOT_FOUND = "\nДля объекта по пути \"%s\" не найден тип \"%s\".\nПроверьте правильность json-файла с описанием/директорию с типами \"%s\"";

    public static final String TYPE_INIT_ERROR = "\nКоличество типом в json-файле с описанием типом не равно количеству директорий с типами.\nПуть до директории с типами: \"%s\"\n Проверьте правильность структуры";

    public static final String PROPS_ARE_NOT_DESCRIBED_ERROR = "\nСвойства для типа не описаны в json-файле.\nПуть до типа: \"%s\"";

    public static final String MORE_THAT_ONE_FILE = "\nВ директории \"%s\" найдено больше, чем один файл \"%s\"";

    public static final String JSON_DESERIALIZE_ERROR = "\nОшибка десериализации json-файла в директории \"%s\"\n Проверьте правильность структуры";



}
