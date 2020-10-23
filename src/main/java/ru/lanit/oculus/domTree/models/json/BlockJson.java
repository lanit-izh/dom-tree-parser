package ru.lanit.oculus.domTree.models.json;

import ru.lanit.oculus.domTree.Exceptions.RecursiveBlockException;
import ru.lanit.oculus.domTree.models.directories.withDescription.BlockDir;
import ru.lanit.oculus.domTree.models.json.markup.Markup;

import java.util.List;

/**
 * Описание json-файла для блоков
 */
public class BlockJson extends Commons {

    private String type;
    private Markup markup;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private List<PropertyJson> properties;

    public List<PropertyJson> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyJson> properties) {
        this.properties = properties;
    }

    public Markup getMarkup() {
        return markup;
    }

    public void setMarkup(Markup markup) {
        this.markup = markup;
    }

    /**
     * Проверяет, чтобы блок не содержал во вложенных блоках самого себя
     *
     * @param absolutePath      -   путь до директории с блоком
     *
     * @return                  -   лист с общими блоками
     */
    @Override
    public List<BlockDir> getBlocksFromCommons(String absolutePath) {
        List<BlockDir> blocks = super.getBlocksFromCommons(absolutePath);
        boolean isBlockContainsItself = blocks
                .stream()
                .anyMatch(block -> block.getBlockJson().getId().equals(this.getId()));
        if (isBlockContainsItself) {
            throw new RecursiveBlockException(this.getId(), absolutePath);
        }
        return blocks;
    }

}