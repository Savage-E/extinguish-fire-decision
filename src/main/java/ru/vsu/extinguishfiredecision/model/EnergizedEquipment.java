package ru.vsu.extinguishfiredecision.model;

public class EnergizedEquipment {
    private String suggestion = "Попытаться обесточить от кислорода объект";

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public EnergizedEquipment() {

    }
}
