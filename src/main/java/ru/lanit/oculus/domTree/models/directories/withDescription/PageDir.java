package ru.lanit.oculus.domTree.models.directories.withDescription;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.Singleton;
import ru.lanit.oculus.domTree.models.directories.ParentDirectory;
import ru.lanit.oculus.domTree.models.directories.withDirectories.BlocksDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.CommonDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.ElementsDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.PagesDir;
import ru.lanit.oculus.domTree.models.json.PageJson;

import java.io.File;

/**
 * Директория, которая содержит описание страницы
 * Может содержать в себе директории с блоками и элементами
 */
public class PageDir extends DirectoryWithDescription implements ParentDirectory {

    private BlocksDir blocksDir;
    private ElementsDir elementsDir;
    private PageJson pageJson;

    public PageDir(File file) {
        super(file);
        setAbsolutePathToDir(file.getAbsolutePath());
        setChildDir(file);
        pageJson = GsonUtil.deserializePage(file);
        setDisplayedName(pageJson.getName());
    }

    public BlocksDir getBlocksDir() {
        return blocksDir;
    }

    public ElementsDir getElementsDir() {
        return elementsDir;
    }

    public PageJson getPageJson() {
        return pageJson;
    }

    public void setPageJson(PageJson pageJson) {
        this.pageJson = pageJson;
    }

    public void setBlocksDir(BlocksDir blocksDir) {
        this.blocksDir = blocksDir;
    }

    public void setElementsDir(ElementsDir elementsDir) {
        this.elementsDir = elementsDir;
    }

    @Override
    public void setChildDir(File parentDir) {
        blocksDir = FileUtil.initBlocksDir(parentDir);
        elementsDir = FileUtil.initElementsDir(parentDir);
    }

}
