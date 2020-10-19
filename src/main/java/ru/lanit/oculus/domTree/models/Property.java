package ru.lanit.oculus.domTree.models;

import ru.lanit.oculus.domTree.models.directories.AbstractDirectory;

public class Property extends AbstractDirectory {

    private String pathToImage;
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
