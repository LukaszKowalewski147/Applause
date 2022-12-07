package com.example.applause;

import java.util.LinkedList;
import java.util.Queue;

public class Displacement {

    private Queue<AccelerationVector> entries;
    private final int limit;

    public Displacement() {
        entries = new LinkedList<>();
        limit = 1000;

        initializeData();
    }

    public Queue<AccelerationVector> getEntries(){
        return entries;
    }

    public AccelerationVector addEntry(double zAxis, long time) {
        AccelerationVector entry = new AccelerationVector(zAxis, time);
        entries.add(entry);
        entries.remove();
        return entry;
    }

    private void initializeData() {
        AccelerationVector initialEntry = new AccelerationVector(0.0d, 0L);
        for (int i = 0; i < limit; i++) {
            entries.add(initialEntry);
        }
    }
}
