package ru.lanit.oculus.domTree.models.directories.withDescription;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.models.directories.ParentDirectory;
import ru.lanit.oculus.domTree.models.directories.elementTypes.PropertiesDirectory;
import ru.lanit.oculus.domTree.models.directories.withDirectories.BlocksDir;
import ru.lanit.oculus.domTree.models.directories.withDirectories.ElementsDir;
import ru.lanit.oculus.domTree.models.json.BlockJson;
import java.io.File;

/**
 * Директория, которая содержит описание блока и директорию с элементами блока
 */
public class BlockDir extends DirectoryWithDescription implements ParentDirectory {

    //является ли блок общим
    private boolean isCommon;
    //директория с элементами
    private ElementsDir elementsDir;
    //директория с вложенными блоками
    private BlocksDir blocksDir;
    //json с описанием блока
    private BlockJson blockJson;
    //свойства (состояния) блока
    private PropertiesDirectory props;

    public BlockDir(File file) {
        super(file);
        setChildDir(file);
        blockJson = GsonUtil.deserializeBlock(file);
        setDisplayedName(blockJson.getName());
        setAbsolutePathToDir(file);
    }

    public boolean isCommon() {
        return isCommon;
    }

    public void setCommon(boolean common) {
        isCommon = common;
    }

    public ElementsDir getElementsDir() {
        return elementsDir;
    }

    public void setElementsDir(ElementsDir elementsDir) {
        this.elementsDir = elementsDir;
    }

    public BlocksDir getBlocksDir() {
        return blocksDir;
    }

    public void setBlocksDir(BlocksDir blocksDir) {
        this.blocksDir = blocksDir;
    }

    public BlockJson getBlockJson() {
        return blockJson;
    }

    public void setBlockJson(BlockJson blockJson) {
        this.blockJson = blockJson;
    }

    public PropertiesDirectory getProps() {
        return props;
    }

    public void setProps(PropertiesDirectory props) {
        this.props = props;
    }

    @Override
    public void setChildDir(File parentDir) {
        blocksDir = FileUtil.initBlocksDir(parentDir);
        elementsDir = FileUtil.initElementsDir(parentDir);
    }
}
