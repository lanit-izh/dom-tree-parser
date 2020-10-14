package ru.lanit.oculus.domTree.models.json;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.models.directories.withDescription.BlockDir;
import ru.lanit.oculus.domTree.models.directories.withDescription.ElementDir;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для описания json-файлов, которые содержат список общих элементов (блоков или элементов)
 */
public abstract class Commons extends DefaultJson {


    private List<BlockJson> blocks;
    private List<ElementJson> elements;

    public List<BlockJson> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockJson> blocks) {
        this.blocks = blocks;
    }

    public List<ElementJson> getElements() {
        return elements;
    }

    public void setElements(List<ElementJson> elements) {
        this.elements = elements;
    }

    public List<BlockDir> getBlocksFromCommons() {
        List<BlockDir> blockDirs = new ArrayList<>();
        if (blocks != null) {
            blocks.forEach(block -> {
                FileUtil
                        .getRootDirectory()
                        .getCommon()
                        .getBlocksDir()
                        .getBlocksDirList()
                        .forEach(blockDir -> {
                            if (blockDir.getBlockJson().getId().equals(block.getId())) {
                                BlockDir commonBlock = GsonUtil.getGson().fromJson(GsonUtil.getGson().toJson(blockDir),BlockDir.class);
                                commonBlock.setCommon(true);
                                blockDirs.add(commonBlock);
                            }
                        });
            });
        }
        return blockDirs;
    }

    public List<ElementDir> getElementsFromCommons() {
        List<ElementDir> elementDirs = new ArrayList<>();
        if (elements != null) {
            elements.forEach(elementJson -> {
                FileUtil
                        .getRootDirectory()
                        .getCommon()
                        .getElementsDir()
                        .getElementsDirList()
                        .forEach(elementDir -> {
                            if (elementDir.getElementJson().getId().equals(elementJson.getId())) {
                                ElementDir commonElement = GsonUtil.getGson().fromJson(GsonUtil.getGson().toJson(elementDir),ElementDir.class);
                                commonElement.setCommon(true);
                                elementDirs.add(commonElement);
                            }
                        });
            });
        }
        return elementDirs;
    }
}
