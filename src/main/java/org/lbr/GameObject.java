package org.lbr;

public abstract class GameObject extends Exception {
    private String name;

    public GameObject(String name_) {
        this.name = name_;
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
    void setName(String newName) {
        name = newName;
    }

}