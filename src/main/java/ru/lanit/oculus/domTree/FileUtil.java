package ru.lanit.oculus.domTree;

import org.apache.commons.io.IOUtils;
import ru.lanit.oculus.domTree.models.directories.withDescription.BlockDir;
import ru.lanit.oculus.domTree.models.directories.withDescription.ElementDir;
import ru.lanit.oculus.domTree.models.directories.withDescription.PageDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.BlocksDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.ElementsDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.RootDir;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * @param path      -   путь
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
     *
     * @return -   файловая иерархия в виде класса RootDir
     */
    public static void setRootDir(String path) {
        setProjectPath(path);
        parseRootDir();
    }

    /**
     * Парсит рут-директорию и сеттит в поле
     */
    public static void parseRootDir() {
        String pathToRootDir = project_path_string + FILE_SEPARATOR + Singleton.ROOT_DIR_NAME;
        rootDirectory = new RootDir(new File(pathToRootDir));
        addCommonObjectsToDom(rootDirectory);
        setXpathForObjects(rootDirectory);
    }

    /**
     * Возвращает содержимое json-файла
     *
     * @param directory -   директория, содержащая json
     * @return -   содержимое файла
     */
    public static String getJsonContent(File directory) {
        String content = "";
        try {
            File json = findFileByExtension(directory, "json");
            content = IOUtils.toString(json.toURI(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * Ищет и возвращает первый найденный файл по расширению
     *
     * @param directory     -   директория для поиска
     * @param extension     -   расширение
     *
     * @return              -   найденный файл или null
     */
    private static File findFileByExtension(File directory, String extension) {
        File firstFileWithExtension = null;
        for (File file : getChildren(directory)) {
            if (file.getName().contains("." + extension)) {
                firstFileWithExtension = file;
                break;
            }
        }
        return firstFileWithExtension;
    }

    /**
     * Парсит директории с элементами
     *
     * @param parentDir -   директория, содержащая директории с элементами
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
     * Ищет директорию, которая содержит в себе список элементов
     *
     * @param parentDir -   директория для поиска
     * @return -   директория с элементами
     */
    public static ElementsDir getElementsDirFromDirectory(File parentDir) {
        ElementsDir elementsDir = null;
        for (File file : getChildren(parentDir)) {
            if (file.getName().equals(Singleton.ELEMENTS_DIR_NAME)) {
                elementsDir = new ElementsDir(file);
            }
        }
        return elementsDir;
    }

    /**
     * Добавляет общие блоки и элементы из json как виртуальные директории
     *
     * @param rootDir -   root-директория
     * @return -   root-директория с добавленными виртуальными общими компонентами
     */
    private static void addCommonObjectsToDom(RootDir rootDir) {
        rootDir.getPagesDirectory().getPageDirectoriesList().forEach(pageDir -> {
            if (pageDir.getBlocksDir() != null) {
                pageDir
                        .getBlocksDir()
                        .addCommonBlocks(pageDir.getPageJson().getBlocksFromCommons());
            } else {
                pageDir.setBlocksDir(new BlocksDir(pageDir.getPageJson().getBlocksFromCommons()));
            }
            if (pageDir.getElementsDir() != null) {
                pageDir
                        .getElementsDir()
                        .addCommonElements(pageDir.getPageJson().getElementsFromCommons());
            } else {
                pageDir.setElementsDir(new ElementsDir(pageDir.getPageJson().getElementsFromCommons()));
            }
        });
    }

    /**
     * Возвращает все вложенные в директорию файлы
     *
     * @param parentDir -   родительская директория
     * @return -   вложенные файлы
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
     * @param rootDirectory -   рут-директория
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
     */
    private static void setXpathForBlocks(String prefix, BlocksDir blocksDir) {
        if (blocksDir != null) {
            String blocksPrefix = String.format(Singleton.XPATH_TEMPLATE, prefix,  Singleton.BLOCKS_DIR_DISPLAY_NAME);
            blocksDir.getBlocksDirList().forEach(blockDir -> {
                String blockXpath = String.format(Singleton.XPATH_TEMPLATE, blocksPrefix, blockDir.getDisplayedName());
                blockDir.setXpath(blockXpath);
                setXpathForBlocks(blockXpath, blockDir.getBlocksDir());
                setXpathForElements(blockXpath, blockDir.getElementsDir());
            });
        }

    }

    /**
     * Добавляет к элементам xpath'ы
     *
     * @param prefix        -   префикс (xpath парент-объектов: страниц/блоков)
     * @param elementsDir   -   директорий с элементами
     */
    private static void setXpathForElements(String prefix, ElementsDir elementsDir) {
        if (elementsDir != null) {
            String elementsPrefix = String.format(Singleton.XPATH_TEMPLATE, prefix, Singleton.ELEMENTS_DIR_DISPLAY_NAME);
            elementsDir.getElementsDirList().forEach(elementDir -> {
                String elementXpath = String.format(Singleton.XPATH_TEMPLATE, elementsPrefix, elementDir.getElementJson().getName());
                elementDir.setXpath(elementXpath);
            });
        }
    }

    /**
     * Ищет в директории png-изображение
     *
     * @param directory     -   директория
     *
     * @return              -   путь до изображения
     */
    public static String getFindImageAndGetPath(File directory) {
        return findFileByExtension(directory,"png").getPath();
    }

}
