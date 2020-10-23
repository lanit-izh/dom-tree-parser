package ru.lanit.oculus.domTree.models.json;

import ru.lanit.oculus.domTree.Exceptions.*;
import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.models.directories.withDescription.BlockDir;
import ru.lanit.oculus.domTree.models.directories.withDescription.ElementDir;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Ищет и возвращает блоки из директории Common по id из json-файла
     *
     * @return -   список блоков
     */
    public List<BlockDir> getBlocksFromCommons(String absolutePath) {
        List<BlockDir> blockDirs = new ArrayList<>();
        if (blocks != null) {
            blocks.forEach(blockJson -> {
                List<BlockDir> commonBlocks = FileUtil
                        .getRootDirectory()
                        .getCommon()
                        .getBlocksDir()
                        .getBlocksDirList()
                        .stream()
                        .filter(block -> block.getBlockJson().getId().equals(blockJson.getId()))
                        .collect(Collectors.toList());
                int size = commonBlocks.size();
                if (size == 0) {
                    throw new CommonBlockNotFoundException(blockJson.getId(), absolutePath);
                } else if (size == 1) {
                    BlockDir commonBlock = GsonUtil
                            .getGson()
                            .fromJson(GsonUtil.getGson().toJson(commonBlocks.get(0)), BlockDir.class);
                    commonBlock.setCommon(true);
                    blockDirs.add(commonBlock);
                } else {
                    throw new MoreThatOneCommonBlockException(blockJson.getId(), absolutePath);
                }
            });
        }
        return blockDirs;
    }

    /**
     * Ищет и возвращает элементы из директории Common по id из json-файла
     *
     * @return -   список элементов
     */
    public List<ElementDir> getElementsFromCommons(String absolutePath) {
        List<ElementDir> elementDirs = new ArrayList<>();
        if (elements != null) {
            elements.forEach(elementJson -> {
                List <ElementDir> commonElements = FileUtil
                        .getRootDirectory()
                        .getCommon()
                        .getElementsDir()
                        .getElementsDirList()
                        .stream()
                        .filter(element -> element.getElementJson().getId().equals(elementJson.getId()))
                        .collect(Collectors.toList());
                int size = commonElements.size();
                if (size == 0) {
                    throw  new CommonElementNotFoundException(elementJson.getId(), absolutePath);
                } else if (size == 1) {
                    ElementDir commonElement = GsonUtil
                            .getGson()
                            .fromJson(GsonUtil.getGson().toJson(commonElements.get(0)), ElementDir.class);
                    commonElement.setCommon(true);
                    elementDirs.add(commonElement);
                } else {
                    throw new MoreThatOneCommonElementException(elementJson.getId(), absolutePath);
                }
            });
        }
        return elementDirs;
    }
}
