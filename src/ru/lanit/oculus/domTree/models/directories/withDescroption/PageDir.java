package ru.lanit.oculus.domTree.models.directories.withDescroption;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.directories.ParentDirectory;
import ru.lanit.oculus.domTree.models.directories.withDirectories.BlocksDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.ElementsDir;
import ru.lanit.oculus.domTree.models.json.PageJson;
import java.io.File;

/**
 * Директория, которая содержит описание страницы
 * Может содержать в себе директории с блоками и элементами
 */
public class PageDir extends DirectoryWithDescription implements ParentDirectory {

    private BlocksDir blocks;
    private ElementsDir elements;
    private PageJson pageJson;

    public PageDir(File file, String fileName) {
        super(file, fileName);
        setChildDir(file);
        pageJson = GsonUtil.deserializePage(getJsonContent(file, fileName));
    }

    public BlocksDir getBlocks() {
        return blocks;
    }

    public ElementsDir getElements() {
        return elements;
    }

    public PageJson getPageJson() {
        return pageJson;
    }

    public void setPageJson(PageJson pageJson) {
        this.pageJson = pageJson;
    }

    public void setBlocks(BlocksDir blocks) {
        this.blocks = blocks;
    }

    public void setElements(ElementsDir elements) {
        this.elements = elements;
    }

    @Override
    public void setChildDir(File parentDir) {
        for (File file : FileUtil.getChildren(parentDir)) {
            if (file.getName().equals(Singleton.BLOCKS_DIR_NAME) && file.isDirectory()) {
                blocks = new BlocksDir(file);
            } else if (file.getName().equals(Singleton.ELEMENTS_DIR_NAME) && file.isDirectory()) {
                elements = new ElementsDir(file);
            }
        }
    }

}
