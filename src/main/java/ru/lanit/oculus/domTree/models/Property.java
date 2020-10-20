package ru.lanit.oculus.domTree.models;

import ru.lanit.oculus.domTree.models.directories.AbstractDirectory;

/**
 * Класс, описывающий свойство (состояние)
 * По факту не является директорией,
 * но наследует класс для удобства работы со свойством в плагине/на стороне драйвера
 */
public class Property extends AbstractDirectory {

    //путь до изображения для свойства
    private String pathToImage;
    //путь до свойства
    private String xpath;

    public Property(String name, String pathToImage) {
        setDisplayedName(name);
        this.pathToImage = pathToImage;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

}
