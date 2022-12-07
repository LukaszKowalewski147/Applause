package com.example.applause;

public class AccelerationVector {
    private final double zAxis;
    private final long time;

    public AccelerationVector(double zAxis, long time) {
        this.zAxis = zAxis;
        this.time = time;
    }

    public double getZAxis() {
        return zAxis;
    }

    public long getTime() {
        return time;
    }
}

