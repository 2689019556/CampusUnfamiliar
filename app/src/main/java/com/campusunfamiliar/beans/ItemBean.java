package com.campusunfamiliar.beans;

/**
 * Created by ASUSS on 2017/7/9.
 */

public class ItemBean
{
    private int img;
    private String title;
    private boolean isUpdate=false;

    public ItemBean() {
    }

    public ItemBean(int img, String title, boolean isUpdate) {
        this.img = img;
        this.title = title;
        this.isUpdate = isUpdate;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "img=" + img +
                ", title='" + title + '\'' +
                ", isUpdate=" + isUpdate +
                '}';
    }
}
