package com.codecool.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactRepository {

    private List<Fact> facts;

    public FactRepository() {
        this.facts = new ArrayList<>();
    }

    public void addFact(Fact fact) {
        this.facts.add(fact);
    }

    private class FactIterator implements Iterator<Fact> {

        int index;
    
        @Override
        public boolean hasNext() {
            return index < facts.size();
        }
    
        @Override
        public Fact next() {
            if (this.hasNext())
                return facts.get(index++);
    
            return null;
        }
    
    }

    public Iterator<Fact> getIterator() {
        return new FactIterator();
    }

}