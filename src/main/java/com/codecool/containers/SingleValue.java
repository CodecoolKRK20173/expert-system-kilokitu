package com.codecool.containers;

import java.util.ArrayList;

public class SingleValue extends Value {

    public SingleValue(String param, boolean selectionType) {
        this.inputPattern = new ArrayList<>();
        this.inputPattern.add(param);
        this.selectionType = selectionType;
    }
}