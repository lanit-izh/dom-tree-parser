package ru.lanit.oculus.domTree.models.directories;

import java.io.File;

public abstract class AbstractDirectory {
    /**
     * Отображаемое в плагине название
     */
    private String displayedName;
    private String absolutePathToDir;

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public String getAbsolutePathToDir() {
        return absolutePathToDir;
    }

    public void setAbsolutePathToDir(String absolutePathToDir) {
        this.absolutePathToDir = absolutePathToDir;
    }

    protected void setAbsolutePathToDir(File directory) {
        absolutePathToDir = directory.getAbsolutePath();
    }

}
