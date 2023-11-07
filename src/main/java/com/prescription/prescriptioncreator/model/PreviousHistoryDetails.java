package com.prescription.prescriptioncreator.model;

public class PreviousHistoryDetails {
    private String previous_history;

    public String getPrevious_history() {
        return previous_history;
    }

    public void setPrevious_history(String previous_history) {
        this.previous_history = previous_history;
    }
    @Override
    public String toString() {return this.getPrevious_history();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;
}
