package com.prescription.prescriptioncreator.model;

public class SuggestionsDetails {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String suggestions;


    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public String toString() {
        return this.getSuggestions();
    }
}
