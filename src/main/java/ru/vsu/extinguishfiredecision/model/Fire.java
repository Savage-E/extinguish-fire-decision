package ru.vsu.extinguishfiredecision.model;

import ru.vsu.extinguishfiredecision.FireTypes;

public class Fire {
    private FireTypes type;
    private String material;

    public FireTypes getType() {
        return type;
    }

    public Fire(FireTypes type, String material) {
        this.type = type;
        this.material = material;
    }

    public Fire(FireTypes type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public Fire(String material) {
        this.material = material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setType(FireTypes type) {
        this.type = type;
    }
}
