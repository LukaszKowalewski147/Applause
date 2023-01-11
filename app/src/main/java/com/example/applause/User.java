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
    private double qualityMax;
    private double quantityAvg;
    private double quantityMax;
    private double reactionTimeAvg;
    private double reactionTimeMax;

    public User(String username) {
        this.username = username;
    }

    public User(String username, int speedAvg) {
        this.username = username;
        this.speedAvg = speedAvg;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<ClapsSession> getClapsSessions() {
        return clapsSessions;
    }

    public void setClapsSessions(ArrayList<ClapsSession> clapsSessions) {
        this.clapsSessions = clapsSessions;
    }

    public double getSpeedAvg() {
        return speedAvg;
    }

    public void setSpeedAvg(double speedAvg) {
        this.speedAvg = speedAvg;
    }

    public double getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(double speedMax) {
        this.speedMax = speedMax;
    }

    public double getForceAvg() {
        return forceAvg;
    }

    public void setForceAvg(double forceAvg) {
        this.forceAvg = forceAvg;
    }

    public double getForceMax() {
        return forceMax;
    }

    public void setForceMax(double forceMax) {
        this.forceMax = forceMax;
    }

    public double getQualityAvg() {
        return qualityAvg;
    }

    public void setQualityAvg(double qualityAvg) {
        this.qualityAvg = qualityAvg;
    }

    public double getQualityMax() {
        return qualityMax;
    }

    public void setQualityMax(double qualityMax) {
        this.qualityMax = qualityMax;
    }

    public double getQuantityAvg() {
        return quantityAvg;
    }

    public void setQuantityAvg(double quantityAvg) {
        this.quantityAvg = quantityAvg;
    }

    public double getQuantityMax() {
        return quantityMax;
    }

    public void setQuantityMax(double quantityMax) {
        this.quantityMax = quantityMax;
    }

    public double getReactionTimeAvg() {
        return reactionTimeAvg;
    }

    public void setReactionTimeAvg(double reactionTimeAvg) {
        this.reactionTimeAvg = reactionTimeAvg;
    }

    public double getReactionTimeMax() {
        return reactionTimeMax;
    }

    public void setReactionTimeMax(double reactionTimeMax) {
        this.reactionTimeMax = reactionTimeMax;
    }

    public void calculateStats() {
        calculateSpeedAvg();
        calculateSpeedMax();
        calculateForceAvg();
        calculateForceMax();
        calculateQualityAvg();
        calculateQualityMax();
        calculateQuantityAvg();
        calculateQuantityMax();
        calculateReflexAvg();
        calculateReflexMax();
    }

    private void calculateSpeedAvg() {

    }

    private void calculateSpeedMax() {

    }

    private void calculateForceAvg() {

    }

    private void calculateForceMax() {

    }

    private void calculateQualityAvg() {

    }

    private void calculateQualityMax() {

    }

    private void calculateQuantityAvg() {

    }

    private void calculateQuantityMax() {

    }

    private void calculateReflexAvg() {

    }

    private void calculateReflexMax() {

    }
}
