package ru.vsu.extinguishfiredecision;

public class Result {
    private FireTypes fireTypes;
    private FireExtinguisher fireExtinguisher;

    public void setFireType(FireTypes fireTypes) {
        this.fireTypes = fireTypes;
    }

    public void setFireExtinguisher(FireTypes fireTypes) {
        this.fireTypes = fireTypes;
    }

    public FireTypes getFireType() {
        return fireTypes;
    }

    public FireExtinguisher getfireExtinguisher() {
        return fireExtinguisher;
    }

    public boolean isFireTypeInit() {
        return fireTypes != null;
    }

    public boolean isFireExtinguisherTypeInit() {
        return fireExtinguisher != null;
    }

}
