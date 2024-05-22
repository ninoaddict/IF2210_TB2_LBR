package org.lbr.gameobject;

import org.lbr.gameobject.cultivable.Cultivable;
import org.lbr.gui.card.Card;

public abstract class GameObject {
    private String name;
    private String imgUrlPath;
    private Card parent;

    public GameObject(String name_, String imgUrlPath) {
        this.name = name_;
        this.imgUrlPath = imgUrlPath;
        this.parent = null;
    }

    public String getImgUrlPath() {
        return imgUrlPath;
    }

    public void setImgUrlPath(String imgUrlPath) {
        this.imgUrlPath = imgUrlPath;
    }

    public Card getParent() {
        return parent;
    }

    public void setParent(Card parent) {
        this.parent = parent;
    }

    // getter
    public String getName() {
        return name;
    }

    public String getTypeObject() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getTypeObject() + "\nName: " + name;
    }

    // setter
    public void setName(String newName) {
        name = newName;
    }
}