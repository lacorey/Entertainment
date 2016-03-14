package com.melon.mobile.entertainment.model;

/**
 * Created by sinye on 16/3/11.
 */
public class NavigationModel extends BaseModel {

    private int iconResId;

    public NavigationModel(String id, String name, int iconResId) {
        super(id, name);
        this.iconResId = iconResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

}
