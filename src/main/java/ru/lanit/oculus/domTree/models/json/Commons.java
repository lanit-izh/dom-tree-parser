package ru.lanit.oculus.domTree.models.json;

import ru.lanit.oculus.domTree.FileUtil;
import ru.lanit.oculus.domTree.GsonUtil;
import ru.lanit.oculus.domTree.Singleton;
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

    /**
     * Ищет и возвращает блоки из директории Common по id из json-файла
     *
     * @return          -   список блоков
     */
    public List<BlockDir> getBlocksFromCommons() {
        List<BlockDir> blockDirs = new ArrayList<>();
        if (blocks != null) {
            blocks.forEach(blockJson -> {
                BlockDir blockDir = FileUtil
                        .getRootDirectory()
                        .getCommon()
                        .getBlocksDir()
                        .getBlocksDirList()
                        .stream()
                        .filter(block -> block.getBlockJson().getId().equals(blockJson.getId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException(String.format("Не найден блок с id=%s в папке %s", blockJson.getId(), Singleton.COMMON_DIR_NAME)));
                BlockDir commonBlock = GsonUtil
                        .getGson()
                        .fromJson(GsonUtil.getGson().toJson(blockDir), BlockDir.class);
                commonBlock.setCommon(true);
                blockDirs.add(commonBlock);
            });
        }
        return blockDirs;
    }

    /**
     * Ищет и возвращает элементы из директории Common по id из json-файла
     *
     * @return          -   список элементов
     */
    public List<ElementDir> getElementsFromCommons() {
        List<ElementDir> elementDirs = new ArrayList<>();
        if (elements != null) {
            elements.forEach(elementJson -> {
                ElementDir elementDir = FileUtil
                        .getRootDirectory()
                        .getCommon()
                        .getElementsDir()
                        .getElementsDirList()
                        .stream()
                        .filter(element -> element.getElementJson().getId().equals(elementJson.getId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException(String.format("Не найден элемент с id=%s в папке %s", elementJson.getId(), Singleton.COMMON_DIR_NAME)));
                ElementDir commonElement = GsonUtil.getGson().fromJson(GsonUtil.getGson().toJson(elementDir),ElementDir.class);
                commonElement.setCommon(true);
                elementDirs.add(commonElement);
            });
        }
        return elementDirs;
    }
}
