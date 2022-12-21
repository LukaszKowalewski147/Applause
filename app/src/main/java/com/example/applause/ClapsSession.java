package com.example.applause;

public class ClapsSession {
    private final SessionType sessionType;
    private double avgSpeed;
    private double maxSpeed;
    private double avgForce;
    private double maxForce;
    private int quality;
    private int quantity;
    private int reflex;
    private boolean noClaps;

    public ClapsSession(SessionType sessionType) {
        this.sessionType = sessionType;
        noClaps = true;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAvgForce() {
        return avgForce;
    }

    public void setAvgForce(double avgForce) {
        this.avgForce = avgForce;
    }

    public double getMaxForce() {
        return maxForce;
    }

    public void setMaxForce(double maxForce) {
        this.maxForce = maxForce;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReflex() {
        return reflex;
    }

    public void setReflex(int reflex) {
        this.reflex = reflex;
    }

    public boolean isNoClaps() {
        return noClaps;
    }

    public void setNoClaps(boolean noClaps) {
        this.noClaps = noClaps;
    }
}
