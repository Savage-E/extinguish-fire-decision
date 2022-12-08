package ru.vsu.extinguishfiredecision;

import ru.vsu.extinguishfiredecision.model.Fire;

import java.util.List;

public class Result {
    private Fire fire;
    private List<FireExtinguisherTypes> fireExtinguisherTypes;

    public void setFire(Fire fire) {
        this.fire = fire;
    }

    public void setFireExtinguisher(List<FireExtinguisherTypes> fireExtinguisherTypes) {
        this.fireExtinguisherTypes = fireExtinguisherTypes;
    }

    public Fire getFire() {
        return fire;
    }

    public List<FireExtinguisherTypes> getfireExtinguisher() {
        return fireExtinguisherTypes;
    }

    public boolean isFireTypeInit() {
        return fire != null;
    }

    public boolean isFireExtinguisherTypeInit() {
        return fireExtinguisherTypes != null;
    }

}
