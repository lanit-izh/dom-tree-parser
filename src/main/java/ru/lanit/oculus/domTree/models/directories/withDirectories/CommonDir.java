package ru.lanit.oculus.domTree.models.directories.withDirectories;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.Singleton;

import java.io.File;

/**
 * Описывает директорию, которая содержит в себе директорию с блоками и с элементами
 * Не содержит в себе json-файла
 */
public class CommonDir extends DirectoryWithDirectories {

    private BlocksDir blocksDir;
    private ElementsDir elementsDir;

    public CommonDir(File parentDir) {
        super(parentDir);
        setDisplayedName(Singleton.COMMON_DIR_DISPLAY_NAME);
    }

    public BlocksDir getBlocksDir() {
        return blocksDir;
    }

    public ElementsDir getElementsDir() {
        return elementsDir;
    }

    @Override
    public void setChildDir(File parentDir) {
        for (File file : FileUtil.getChildren(parentDir)) {
            if (file.getName().equals(Singleton.BLOCKS_DIR_NAME) && file.isDirectory()) {
                blocksDir = new BlocksDir(file);
            } else if (file.getName().equals(Singleton.ELEMENTS_DIR_NAME) && file.isDirectory()) {
                elementsDir = new ElementsDir(file);
            }
        }
    }

}
