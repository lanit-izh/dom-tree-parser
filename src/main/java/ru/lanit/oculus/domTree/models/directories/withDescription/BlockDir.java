package ru.lanit.oculus.domTree.models.directories.withDescription;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.models.directories.ParentDirectory;
import ru.lanit.oculus.domTree.models.directories.withDirectories.ElementsDir;
import ru.lanit.oculus.domTree.models.json.BlockJson;

import java.io.File;

/**
 * Директория, которая содержит описание блока и директорию с элементами блока
 */
public class BlockDir extends DirectoryWithDescription implements ParentDirectory {

    private boolean isCommon;
    private ElementsDir elementsDir;
    private BlockJson blockJson;

    public BlockDir(File file, String fileName) {
        super(file, fileName);
        setChildDir(file);
        blockJson = GsonUtil.deserializeBlock(getJsonContent(file, fileName));
        setDisplayedName(blockJson.getName());
    }

    public BlockDir(File file, String fileName, boolean isCommon) {
        super(file, fileName);
        setChildDir(file);
        blockJson = GsonUtil.deserializeBlock(getJsonContent(file, fileName));
        setDisplayedName(blockJson.getName());
        setCommon(isCommon);
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

    public BlockJson getBlockJson() {
        return blockJson;
    }

    public void setBlockJson(BlockJson blockJson) {
        this.blockJson = blockJson;
    }

    @Override
    public void setChildDir(File parentDir) {
        elementsDir = FileUtil.getElementsDirFromDirectory(parentDir);
    }
}
