package ru.lanit.oculus.domTree.models.directories;

public abstract class AbstractDirectory {
    /**
     * Отображаемое в плагине название
     */
    private String displayedName;

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }

}
