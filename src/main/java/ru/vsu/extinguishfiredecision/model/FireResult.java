package ru.vsu.extinguishfiredecision.model;

import ru.vsu.extinguishfiredecision.FireTypes;

public class FireResult {
    private FireTypes fireTypes;

    public void setFireTypes(FireTypes fireTypes) {
        this.fireTypes = fireTypes;
    }

    public FireResult(FireTypes fireTypes) {
        this.fireTypes = fireTypes;
    }

    public FireTypes getFireTypes() {
        return fireTypes;
    }

}
