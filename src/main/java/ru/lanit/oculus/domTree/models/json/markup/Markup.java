package ru.lanit.oculus.domTree.models.json.markup;

import java.util.List;

public class Markup {

    private LeftTop leftTop;
    private RightBottom rightBottom;
    private ActionArea action_area;
    private List<IncreaseArea> increase_areas;

    public LeftTop getLeftTop() {
        return leftTop;
    }

    public void setLeftTop(LeftTop leftTop) {
        this.leftTop = leftTop;
    }

    public RightBottom getRightBottom() {
        return rightBottom;
    }

    public void setRightBottom(RightBottom rightBottom) {
        this.rightBottom = rightBottom;
    }

    public ActionArea getActionArea() {
        return action_area;
    }

    public void setActionArea(ActionArea action_area) {
        this.action_area = action_area;
    }

    public List<IncreaseArea> getIncreaseAreas() {
        return increase_areas;
    }

    public void setIncreaseAreas(List<IncreaseArea> increase_areas) {
        this.increase_areas = increase_areas;
    }
}