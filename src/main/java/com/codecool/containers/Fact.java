package com.codecool.containers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Fact {

    private String id;
    private String description;
    private Map<String, Boolean> evals;

    public Fact(String id, String description) {
        this.id = id;
        this.description = description;
        this.evals = new HashMap<>();
    }

    public Set<String> getIdSet() {
        return this.evals.keySet();
    }

    public void setFactValueById(String id, boolean value) {
        this.evals.put(id, value);
    }

    public boolean getValueById(String id) {
        return this.evals.get(id);
    }

    public String getDescription() {
        return this.description;
    }

}