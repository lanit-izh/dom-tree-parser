package ru.lanit.oculus.domTree.models.json.markup;

public class IncreaseArea {

    private boolean dynamic_size;
    private Location location;

    public boolean isDynamic_size() {
        return dynamic_size;
    }

    public void setDynamic_size(boolean dynamic_size) {
        this.dynamic_size = dynamic_size;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
