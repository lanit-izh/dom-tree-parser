package ru.lanit.oculus.domTree;

import org.apache.commons.io.IOUtils;
import ru.lanit.oculus.domTree.Exceptions.FileNotFoundCustomException;
import ru.lanit.oculus.domTree.Exceptions.JsonNotFoundException;
import ru.lanit.oculus.domTree.Exceptions.MoreThatOneFileException;
import ru.lanit.oculus.domTree.Exceptions.PngNotFoundException;
import ru.lanit.oculus.domTree.models.Property;
import ru.lanit.oculus.domTree.models.directories.elementTypes.ElementTypeDirectory;
import ru.lanit.oculus.domTree.models.directories.elementTypes.PropertiesDirectory;
import ru.lanit.oculus.domTree.models.directories.withDescription.BlockDir;
import ru.lanit.oculus.domTree.models.directories.withDescription.ElementDir;
import ru.lanit.oculus.domTree.models.directories.withDescription.PageDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.BlocksDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.ElementsDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.PagesDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.RootDir;
import ru.lanit.oculus.domTree.models.json.BlockJson;
import ru.lanit.oculus.domTree.models.json.ElementJson;
import ru.lanit.oculus.domTree.models.json.PropertyJson;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Утиль для работы с файлами проекта
 */
public class FileUtil {

    private static String project_path_string = "your path";
    private static RootDir rootDirectory;
    public static String FILE_SEPARATOR;

    public static RootDir getRootDirectory() {
        return rootDirectory;
    }

    /**
     * Устанавливает путь до директории, которая содержит рут-директорию
     *
     * @param path -   путь
     *
     */
    public static void setProjectPath(String path) {
        if (System.getProperty("os.name").contains("indows")) {
            FILE_SEPARATOR = "/";
        } else {
            FILE_SEPARATOR = File.separator;
        }
        project_path_string = path;
    }

    /**
     * Парсит все директории из root-директории
     */
    public static void setRootDir(String path) {
        setProjectPath(path);
        parseRootDir();
    }

    /**
     * Парсит root-директорию и сеттит в поле
     *
     */
    public static void parseRootDir() {
        String pathToRootDir = project_path_string + FILE_SEPARATOR + Singleton.ROOT_DIR_NAME;
        rootDirectory = new RootDir(new File(pathToRootDir));
        addCommonObjectsToDom(rootDirectory);
        setPropsForPages(rootDirectory.getPagesDirectory());
        setXpathForObjects(rootDirectory);
    }

    /**
     * Возвращает содержимое json-файла
     *
     * @param directory -   директория, содержащая json
     *
     * @return -   содержимое файла
     */
    public static String getJsonContent(File directory) {
        String content = "";
        try {
            File json = findFileByExtension(directory, "json");
            content = IOUtils.toString(json.toURI(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException npe) {
            throw new JsonNotFoundException(directory.getAbsolutePath());
        }
        return content;
    }

    /**
     * Ищет и возвращает первый найденный файл по расширению
     *
     * @param directory -   директория для поиска
     * @param extension -   расширение
     *
     * @return -   найденный файл или null
     */
    private static File findFileByExtension(File directory, String extension) {
        return findFileByFullName(directory, null, extension);
    }

    /**
     * Ищет файл по полному названию
     *
     * @param directory -   директория для поиска
     * @param fileName  -   названия файла (без расширения)
     * @param extension -   расширение (без точки)
     *
     * @return -   найденный файл
     */
    public static File findFileByFullName(File directory, String fileName, String extension) {
        List<File> files = new ArrayList<>();
        for (File file : getChildren(directory)) {
            if ((fileName != null && file.getName().equals(fileName + "." + extension)) || (fileName == null && file.getName().contains("." + extension))) {
                files.add(file);
            }
        }
        String fullFileName = fileName != null ? fileName + "." + extension : "поиск файла по расширению: (" + "." + extension + ")";
        if (files.size() > 1) {
            throw new MoreThatOneFileException(directory.getAbsolutePath(), fullFileName);
        }
        if (fileName == null) {
            return files.size() != 0 ? files.get(0) : null;
        } else {
            if (files.size() == 0) {
                throw new FileNotFoundCustomException(fullFileName, directory.getAbsolutePath());
            }
            return files.get(0);
        }
    }

    /**
     * Парсит директории с элементами
     *
     * @param parentDir -   директория, содержащая директории с элементами
     *
     * @return -   лист директорий элементов
     */
    public static List<ElementDir> parseElementsDir(File parentDir) {
        List<ElementDir> elements = new ArrayList<>();
        for (File file : getChildren(parentDir)) {
            elements.add(new ElementDir(file));
        }
        return elements;
    }

    /**
     * Парсит директории с блоками
     *
     * @param parentDir -   директория, содержащая директории с блоками
     *
     * @return -   лист директорий блоков
     */
    public static List<BlockDir> parseBlocksDir(File parentDir) {
        List<BlockDir> blocks = new ArrayList<>();
        for (File file : getChildren(parentDir)) {
            blocks.add(new BlockDir(file));
        }
        return blocks;
    }

    /**
     * Парсит директории со страницами
     *
     * @param parentDir -   директория, содержащая директориии со страницами
     *
     * @return -   лист директорий страниц
     */
    public static List<PageDir> parsePagesDir(File parentDir) {
        List<PageDir> pages = new ArrayList<>();
        for (File file : getChildren(parentDir)) {
            pages.add(new PageDir(file));
        }
        return pages;
    }

    /**
     * Добавляет общие блоки и элементы из json как виртуальные директории
     *
     * @param rootDir -   root-директория
     *
     */
    private static void addCommonObjectsToDom(RootDir rootDir) {
        rootDir
                .getPagesDirectory()
                .getPageDirectoriesList()
                .forEach(FileUtil::addCommonsToPage);
    }

    /**
     * Добавялет общие блоки и элементы к странице
     *
     * @param pageDir -   страница
     *
     */
    private static void addCommonsToPage(PageDir pageDir) {
        if (pageDir.getBlocksDir() != null) {
            pageDir
                    .getBlocksDir()
                    .addCommonBlocks(pageDir.getPageJson().getBlocksFromCommons(pageDir.getAbsolutePathToDir()));
            pageDir.getBlocksDir().getBlocksDirList().forEach(FileUtil::addCommonsToBlock);
        } else {
            pageDir.setBlocksDir(new BlocksDir(pageDir.getPageJson().getBlocksFromCommons(pageDir.getAbsolutePathToDir())));
        }
        if (pageDir.getElementsDir() != null) {
            pageDir
                    .getElementsDir()
                    .addCommonElements(pageDir.getPageJson().getElementsFromCommons(pageDir.getAbsolutePathToDir()));
        } else {
            pageDir.setElementsDir(new ElementsDir(pageDir.getPageJson().getElementsFromCommons(pageDir.getAbsolutePathToDir())));
        }
    }

    /**
     * Добавялет общие блоки и элементы в блок
     *
     * @param blockDir -   блок
     *
     */
    private static void addCommonsToBlock(BlockDir blockDir) {
        if (blockDir.getBlocksDir() != null) {
            blockDir
                    .getBlocksDir()
                    .addCommonBlocks(blockDir.getBlockJson().getBlocksFromCommons(blockDir.getAbsolutePathToDir()));
        } else {
            blockDir.setBlocksDir(new BlocksDir(blockDir.getBlockJson().getBlocksFromCommons(blockDir.getAbsolutePathToDir())));
        }
        if (blockDir.getElementsDir() != null) {
            blockDir
                    .getElementsDir()
                    .addCommonElements(blockDir.getBlockJson().getElementsFromCommons(blockDir.getAbsolutePathToDir()));
        } else {
            blockDir.setElementsDir(new ElementsDir(blockDir.getBlockJson().getElementsFromCommons(blockDir.getAbsolutePathToDir())));
        }
    }

    /**
     * Возвращает все вложенные в директорию файлы
     *
     * @param parentDir     -   родительская директория
     *
     * @return              -   вложенные файлы
     */
    public static List<File> getChildren(File parentDir) {
        List<File> childrenFiles = new ArrayList<>();
        if (parentDir != null && parentDir.listFiles() != null) {
            childrenFiles.addAll(Arrays.asList(parentDir.listFiles()));
        }
        return childrenFiles;
    }

    /**
     * Добавляет к страницам,блокам и элементам xpath'ы
     *
     * @param rootDirectory     -   root-директория
     *
     */
    private static void setXpathForObjects(RootDir rootDirectory) {
        rootDirectory
                .getPagesDirectory()
                .getPageDirectoriesList()
                .forEach(pageDir -> {
                    pageDir.setXpath(pageDir.getDisplayedName());
                    setXpathForBlocks(pageDir.getDisplayedName(), pageDir.getBlocksDir());
                    setXpathForElements(pageDir.getDisplayedName(), pageDir.getElementsDir());
                });
    }

    /**
     * Добавляет к блокам xpath'ы
     *
     * @param prefix        -   префикс (xpath парент-объектов: страниц/блоков)
     * @param blocksDir     -   лист с блоками
     *
     */
    private static void setXpathForBlocks(String prefix, BlocksDir blocksDir) {
        if (blocksDir != null) {
            String blocksPrefix = String.format(Singleton.XPATH_TEMPLATE, prefix, Singleton.BLOCKS_DIR_DISPLAY_NAME);
            blocksDir.getBlocksDirList().forEach(blockDir -> {
                String blockXpath = String.format(Singleton.XPATH_TEMPLATE, blocksPrefix, blockDir.getDisplayedName());
                blockDir.setXpath(blockXpath);
                setXpathForBlocks(blockXpath, blockDir.getBlocksDir());
                setXpathForElements(blockXpath, blockDir.getElementsDir());
                setXpathForProps(blockXpath, blockDir.getProps());
            });
        }

    }

    /**
     * Добавляет к элементам xpath'ы
     *
     * @param prefix            -   префикс (xpath парент-объектов: страниц/блоков)
     * @param elementsDir       -   директорий с элементами
     *
     */
    private static void setXpathForElements(String prefix, ElementsDir elementsDir) {
        if (elementsDir != null) {
            String elementsPrefix = String.format(Singleton.XPATH_TEMPLATE, prefix, Singleton.ELEMENTS_DIR_DISPLAY_NAME);
            elementsDir.getElementsDirList().forEach(elementDir -> {
                String elementXpath = String.format(Singleton.XPATH_TEMPLATE, elementsPrefix, elementDir.getElementJson().getName());
                elementDir.setXpath(elementXpath);
                setXpathForProps(elementXpath, elementDir.getProps());
            });
        }
    }

    /**
     * Добавляет к свойствам(состояниям) xpath'ы
     *
     * @param prefix                -   префикс (xpath парент-объектов: страниц/блоков)
     * @param propertiesDirectory   -   директория со свойствами
     *
     */
    private static void setXpathForProps(String prefix, PropertiesDirectory propertiesDirectory) {
        if (propertiesDirectory != null) {
            String propertiesPrefix = String.format(Singleton.XPATH_TEMPLATE, prefix, Singleton.PROPERTIES_DIR_DISPLAY_NAME);
            propertiesDirectory.getProperties().forEach(property -> {
                String propertyXpath = String.format(Singleton.XPATH_TEMPLATE, propertiesPrefix, property.getDisplayedName());
                property.setXpath(propertyXpath);
            });
        }
    }

    /**
     * Задает свойства для блока
     *
     * @param pages     -   директория со страницами
     *
     */
    private static void setPropsForPages(PagesDir pages) {
        pages
                .getPageDirectoriesList()
                .forEach(page -> {
                    page
                            .getBlocksDir()
                            .getBlocksDirList()
                            .forEach(FileUtil::setPropsForBlock);
                    page
                            .getElementsDir()
                            .getElementsDirList()
                            .forEach(FileUtil::setPropsForElement);
                });
    }

    /**
     * Задает свойства для блока
     *
     * @param block     -   директория с блоком
     *
     */
    private static void setPropsForBlock(BlockDir block) {
        BlockJson json = block.getBlockJson();
        if (json.getType() != null && json.getProperties() != null) {
            block.setProps(new PropertiesDirectory(initAndOverrideProps(json.getType(), json.getProperties(), block.getAbsolutePathToDir())));
        } else if (json.getType() != null) {
            ElementTypeDirectory commonType = rootDirectory
                    .getElementTypesDirectory()
                    .getElementTypeByTypeName(json.getType(), block.getAbsolutePathToDir());
            block.setProps(commonType.getProps());
        }
        if (block.getBlocksDir() != null) {
            block
                    .getBlocksDir()
                    .getBlocksDirList()
                    .forEach(FileUtil::setPropsForBlock);
        }
        if (block.getElementsDir() != null) {
            block
                    .getElementsDir()
                    .getElementsDirList()
                    .forEach(FileUtil::setPropsForElement);
        }
    }

    /**
     * Задает свойства для элемента
     *
     * @param element   -   директория с элементом
     *
     */
    private static void setPropsForElement(ElementDir element) {
        ElementJson json = element.getElementJson();
        if (json.getType() != null && json.getProperties() != null) {
            element.setProps(new PropertiesDirectory(initAndOverrideProps(json.getType(), json.getProperties(), element.getAbsolutePathToDir())));
        } else if (json.getType() != null) {
            ElementTypeDirectory commonType = rootDirectory
                    .getElementTypesDirectory()
                    .getElementTypeByTypeName(json.getType(), element.getAbsolutePathToDir());
            element.setProps(commonType.getProps());
        }
    }

    /**
     * Создает и возвращает свойства элемента/блока.
     * Переопределяет путь до изображения для тех свойств, которые описание в json-файле элемента/блока
     *
     * @param typeName      -   тип элемента/блока
     * @param jsonProps     -   свойства элемента/блока из его json-файла
     * @param directoryPath -   путь до директории элемента/блока
     *
     * @return -   переопределенные свойства
     */
    private static List<Property> initAndOverrideProps(String typeName, List<PropertyJson> jsonProps, String directoryPath) {
        List<Property> overrideProps = new ArrayList<>();
        List<Property> defaultProps = rootDirectory
                .getElementTypesDirectory()
                .getElementTypeByTypeName(typeName, directoryPath)
                .getProps()
                .getProperties();
        File propsDirectory = FileUtil.findDirectoryByNameAndGetNotNull(new File(directoryPath), Singleton.PROPS_DIR_NAME);
        defaultProps.forEach(property -> {
            boolean isNeedToOverride = false;
            String imageName = property.getPathToImage();
            for (PropertyJson jsonProp : jsonProps) {
                if (jsonProp.getName().equals(property.getDisplayedName())) {
                    isNeedToOverride = true;
                    imageName = jsonProp.getImageName();
                }
            }
            if (isNeedToOverride) {
                //берется изображение из директории блока/элемента
                File image = findFileByFullName(propsDirectory, imageName, "png");
                overrideProps.add(new Property(property.getDisplayedName(), image.getAbsolutePath()));
            } else {
                overrideProps.add(new Property(property.getDisplayedName(), imageName));
            }
        });
        return overrideProps;
    }

    /**
     * Ищет в директории png-изображение
     *
     * @param directory -   директория
     *
     * @return -   путь до изображения
     */
    public static String findImageAndGetPath(File directory) {
        File image = findFileByExtension(directory, "png");
        if (image == null) {
            throw new PngNotFoundException(directory.getAbsolutePath());
        } else {
            return image.getPath();
        }
    }

    /**
     * Ищет директорию по названию
     * Может вернуть null
     *
     * @param parentDir -   родительская директория
     * @param dirName   -   директория для поиска
     *
     * @return -   директория
     */
    public static File findDirectoryByName(File parentDir, String dirName) {
        File directory = null;
        for (File file : getChildren(parentDir)) {
            if (file.getName().equals(dirName) && file.isDirectory())
                directory = file;
        }
        return directory;
    }

    /**
     * Ищет директорию по названию и выкидывает ошибку в случае не нахождения
     *
     * @param parentDir -   родительская директория
     * @param dirName   -   директория для поиска
     *
     * @return -   директория
     */
    public static File findDirectoryByNameAndGetNotNull(File parentDir, String dirName) {
        File directory = findDirectoryByName(parentDir, dirName);
        if (directory == null) {
            throw new FileNotFoundCustomException(dirName, parentDir.getAbsolutePath());
        }
        return directory;
    }

    /**
     * Ищет и возвращает директорию с блоком
     *
     * @param parentDir     -   родительская директория
     *
     * @return              -   директория блока
     */
    public static BlocksDir initBlocksDir(File parentDir) {
        return new BlocksDir(findDirectoryByName(parentDir, Singleton.BLOCKS_DIR_NAME));
    }

    /**
     * Ищет и возвращает директорию с элементом
     *
     * @param parentDir     -   родительская директория
     *
     * @return              -   директория элемента
     */
    public static ElementsDir initElementsDir(File parentDir) {
        return new ElementsDir(findDirectoryByName(parentDir, Singleton.ELEMENTS_DIR_NAME));
    }

    /**
     * Ищет и возвращает директорию со страницей
     *
     * @param parentDir     -   родительская директория
     *
     * @return              -   директория страницы
     */
    public static PagesDir initPagesDir(File parentDir) {
        return new PagesDir(findDirectoryByName(parentDir, Singleton.PAGES_DIR_NAME));
    }

}