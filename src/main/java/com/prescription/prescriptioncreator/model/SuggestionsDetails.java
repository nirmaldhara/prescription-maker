package com.prescription.prescriptioncreator.model;

public class SuggestionsDetails {
    private int id;
    private String suggestions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }
    @Override
    public String toString() {return this.getSuggestions();
    }
}
