package ru.lanit.oculus.domTree;

import org.apache.commons.io.IOUtils;
import ru.lanit.oculus.domTree.models.directories.withDescription.BlockDir;
import ru.lanit.oculus.domTree.models.directories.withDescription.DirectoryWithDescription;
import ru.lanit.oculus.domTree.models.directories.withDescription.ElementDir;
import ru.lanit.oculus.domTree.models.directories.withDescription.PageDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.BlocksDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.ElementsDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.RootDir;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Утиль для работы с файлами проекта
 */
public class FileUtil {

    private static String project_path_string = "your path";
    public static RootDir rootDirectory;
    public static final String FILE_SEPARATOR = File.separator;

    public static void setProjectPath(String path) {
        project_path_string = path;
    }

    /**
     * Парсит все директории из root-директории
     *
     * @return      -   файловая иерархия в виде класса RootDir
     */
    public static void setRootDir() {
        String pathToRootDir = project_path_string + FILE_SEPARATOR + Singleton.ROOT_DIR_NAME;
        rootDirectory = new RootDir(new File(pathToRootDir));
        rootDirectory = addCommonObjectsToDom(rootDirectory);
    }

    /**
     * Возвращает содержимое json-файла
     *
     * @param directory     -   директория, содержащая json
     * @param jsonName      -   название json-файла (без расширения)
     *
     * @return              -   содержимое файла
     */
    public static String getJsonContent(File directory, String jsonName) {
        String content = "";
        for (File file: getChildren(directory)) {
            if (file.getName().equals(jsonName + ".json")) {
                try {
                    content= IOUtils.toString(new FileReader(file.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return content;
    }

    /**
     * Парсит директории с элементами
     *
     * @param parentDir     -   директория, содержащая директории с элементами
     *
     * @return              -   лист директорий элементов
     */
    public static List<ElementDir> parseElementsDir (File parentDir) {
        List<ElementDir> elements = new ArrayList<>();
        for (File file : getChildren(parentDir)) {
            elements.add(new ElementDir(file, Singleton.ELEMENT_FILES_NAME));
        }
        return elements;
    }

    /**
     * Парсит директории с блоками
     *
     * @param parentDir     -   директория, содержащая директории с блоками
     *
     * @return              -   лист директорий блоков
     */
    public static List<BlockDir> parseBlocksDir (File parentDir) {
        List<BlockDir> blocks = new ArrayList<>();
        for (File file : getChildren(parentDir)) {
            blocks.add(new BlockDir(file, Singleton.BLOCK_FILES_NAME));
        }
        return blocks;
    }

    /**
     * Парсит директории со страницами
     *
     * @param parentDir     -   директория, содержащая директориии со страницами
     *
     * @return              -   лист директорий страниц
     */
    public static List<PageDir> parsePagesDir (File parentDir) {
        List<PageDir> pages = new ArrayList<>();
        for (File file : getChildren(parentDir)) {
            pages.add(new PageDir(file, Singleton.PAGE_FILES_NAME));
        }
        return pages;
    }

    /**
     * Ищет директорию, которая содержит в себе список элементов
     *
     * @param parentDir     -   директория для поиска
     *
     * @return              -   директория с элементами
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
     * @param rootDir       -   root-директория
     *
     * @return              -   root-директория с добавленными виртуальными общими компонентами
     */
    public static RootDir addCommonObjectsToDom(RootDir rootDir) {
        rootDir.getPagesDirectory().getPageDirectories().forEach(pageDir -> {
            if (pageDir.getBlocks() != null ) {
                pageDir
                        .getBlocks()
                        .addCommonBlocks(pageDir.getPageJson().getBlocksFromCommons());
            } else {
                pageDir.setBlocks(new BlocksDir(pageDir.getPageJson().getBlocksFromCommons()));
            }
            if (pageDir.getElements() != null ) {
                pageDir
                        .getElements()
                        .addCommonElements(pageDir.getPageJson().getElementsFromCommons());
            } else {
                pageDir.setElements(new ElementsDir(pageDir.getPageJson().getElementsFromCommons()));
            }
        });
        return rootDir;
    }

    /**
     * Получает изображение из директории
     *
     * @param directory     -   директория, в которой содержится изображение
     *
     * @return              -   изображение
     */
    public static ImageIcon getImage(DirectoryWithDescription directory) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(directory.getPathToImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon(myPicture);
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

    public static void main(String[] args) {
        setProjectPath("/home/mrsaiw/IdeaProjects/page_object_json_example/");
        setRootDir();
    }

}
