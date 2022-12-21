package com.example.applause;

public class Clap {
    private final double force;
    private final long time;

    public Clap(double force, long time) {
        this.force = force;
        this.time = time;
    }

    public double getForce() {
        return force;
    }

    public long getTime() {
        return time;
    }
}
