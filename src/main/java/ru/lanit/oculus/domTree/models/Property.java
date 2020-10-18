package ru.lanit.oculus.domTree.models;

public class Property {

    private String name;
    private String pathToImage;
    private String xpath;

    public Property(String name, String pathToImage) {
        this.name = name;
        this.pathToImage = pathToImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
