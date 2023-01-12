package com.example.applause;

import java.util.ArrayList;

public class User {
    private String username;
    private ArrayList<ClapsSession> clapsSessions;

    private double speedAvg;
    private double speedMax;
    private double forceAvg;
    private double forceMax;
    private double qualityAvg;
    private int qualityMax;
    private double quantityAvg;
    private int quantityMax;
    private double reactionTimeAvg;
    private int reactionTimeMax;

    public User(String username) {
        this.username = username;
    }

    public User(String username, int speedAvg) {
        this.username = username;
        this.speedAvg = speedAvg;
    }

    public void setClapsSessions(ArrayList<ClapsSession> clapsSessions) {
        this.clapsSessions = clapsSessions;
    }

    public String getUsername() {
        return username;
    }

    public double getSpeedAvg() {
        return speedAvg;
    }

    public double getSpeedMax() {
        return speedMax;
    }

    public double getForceAvg() {
        return forceAvg;
    }

    public double getForceMax() {
        return forceMax;
    }

    public double getQualityAvg() {
        return qualityAvg;
    }

    public int getQualityMax() {
        return qualityMax;
    }

    public double getQuantityAvg() {
        return quantityAvg;
    }

    public int getQuantityMax() {
        return quantityMax;
    }

    public double getReactionTimeAvg() {
        return reactionTimeAvg;
    }

    public int getReactionTimeMax() {
        return reactionTimeMax;
    }

    public void calculateStats() {
        calculateSpeed();
        calculateForce();
        calculateQuality();
        calculateQuantity();
        calculateReflex();
    }

    private void calculateSpeed() {
        int speedSessions = 0;
        double speedSum = 0.0d;
        double speedMax = 0.0d;

        for (int i = 0; i < clapsSessions.size(); i++) {
            ClapsSession session = clapsSessions.get(i);
            if (session.getSessionType() == SessionType.SPEED) {
                speedSessions++;
                speedSum += session.getAvgSpeed();
                if (session.getMaxSpeed() > speedMax)
                    speedMax = session.getMaxSpeed();
            }
        }
        if (speedSessions > 0)
            speedAvg = Helper.changePrecision(speedSum / speedSessions, 2);
        this.speedMax = speedMax;
    }

    private void calculateForce() {
        int forceSessions = 0;
        double forceSum = 0.0d;
        double forceMax = 0.0d;

        for (int i = 0; i < clapsSessions.size(); i++) {
            ClapsSession session = clapsSessions.get(i);
            if (session.getSessionType() == SessionType.FORCE) {
                forceSessions++;
                forceSum += session.getAvgForce();
                if (session.getMaxForce() > forceMax)
                    forceMax = session.getMaxForce();
            }
        }
        if (forceSessions > 0)
            forceAvg = Helper.changePrecision(forceSum / forceSessions, 2);
        this.forceMax = forceMax;
    }

    private void calculateQuality() {
        int qualitySessions = 0;
        int qualitySum = 0;
        int qualityMax = 0;

        for (int i = 0; i < clapsSessions.size(); i++) {
            ClapsSession session = clapsSessions.get(i);
            if (session.getSessionType() == SessionType.QUALITY) {
                int quality = session.getQuality();
                qualitySessions++;
                qualitySum += quality;
                if (quality > qualityMax)
                    qualityMax = quality;
            }
        }
        if (qualitySessions > 0)
            qualityAvg = Helper.changePrecision((double) qualitySum / qualitySessions, 2);
        this.qualityMax = qualityMax;
    }

    private void calculateQuantity() {
        int quantitySessions = 0;
        int quantitySum = 0;
        int quantityMax = 0;

        for (int i = 0; i < clapsSessions.size(); i++) {
            ClapsSession session = clapsSessions.get(i);
            if (session.getSessionType() == SessionType.QUANTITY) {
                int quantity = session.getQuantity();
                quantitySessions++;
                quantitySum += quantity;
                if (quantity > quantityMax)
                    quantityMax = quantity;
            }
        }
        if (quantitySessions > 0)
            quantityAvg = Helper.changePrecision((double) quantitySum / quantitySessions, 2);
        this.quantityMax = quantityMax;
    }

    private void calculateReflex() {
        int reflexSessions = 0;
        int reflexSum = 0;
        int reflexMax = 0;

        for (int i = 0; i < clapsSessions.size(); i++) {
            ClapsSession session = clapsSessions.get(i);
            if (session.getSessionType() == SessionType.REFLEX) {
                int reflex = session.getReflex();
                reflexSessions++;
                reflexSum += reflex;
                if (reflex < reflexMax)
                    reflexMax = reflex;
            }
        }
        if (reflexSessions > 0)
            reactionTimeAvg = Helper.changePrecision((double) reflexSum / reflexSessions, 2);
        reactionTimeMax = reflexMax;
    }
}
