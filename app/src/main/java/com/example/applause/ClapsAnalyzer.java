package com.example.applause;

import java.util.LinkedList;

public class ClapsAnalyzer {
    private LinkedList<AccelerationVector> accelerationVectors;
    private LinkedList<Clap> claps;
    private final SessionType sessionType;
    private long reactionTime;
    private int threshold;

    public ClapsAnalyzer(LinkedList<AccelerationVector> accelerationVectors, SessionType sessionType) {
        this.accelerationVectors = accelerationVectors;
        this.sessionType = sessionType;
        claps = new LinkedList<Clap>();
        threshold = 60;
    }

    public ClapsAnalyzer(long reactionTime, SessionType sessionType) {
        this.reactionTime = reactionTime;
        this.sessionType = sessionType;
    }

    public ClapsSession analyze() {
        ClapsSession clapsSession = new ClapsSession(sessionType);

        if (sessionType != SessionType.REFLEX) {
            findClaps();

            if (claps.size() < 2)
                return clapsSession;
            else
                clapsSession.setNoClaps(false);
        }

        switch (sessionType) {
            case SPEED:
                clapsSession.setAvgSpeed(calculateAvgSpeed());
                clapsSession.setMaxSpeed(calculateMaxSpeed());
                break;
            case FORCE:
                clapsSession.setAvgForce(calculateAvgForce());
                clapsSession.setMaxForce(calculateMaxForce());
                break;
            case QUALITY:
                clapsSession.setQuality(calculateQuality());
                break;
            case QUANTITY:
                clapsSession.setQuantity(claps.size());
                break;
            case REFLEX:
                clapsSession.setReflex(calculateReflex());
        }
        return clapsSession;
    }

    private void findClaps() {
        double acceleration = 0.0d;
        long timeSinceLastClap = 0;
        long timeInterwal = 0;

        for (int i = 0; i < accelerationVectors.size(); i++) {
            acceleration = accelerationVectors.get(i).getZAxis();
            timeInterwal += accelerationVectors.get(i).getTime();
            if (acceleration > threshold)
            {
                timeSinceLastClap = timeInterwal;
                timeInterwal = 0;

                if (isDoublePointClap(i)) { // check second sample
                    acceleration += accelerationVectors.get(i + 1).getZAxis();
                    timeInterwal += accelerationVectors.get(i + 1).getTime();
                    if (isDoublePointClap(i + 1)) { //check third sample
                        acceleration += accelerationVectors.get(i + 2).getZAxis();
                        timeInterwal += accelerationVectors.get(i + 2).getTime();
                        ++i;
                    }
                    ++i;
                }
                acceleration = Helper.changePrecision(acceleration, 2);
                Clap clap = new Clap(acceleration, timeSinceLastClap);
                claps.add(clap);
            }
        }
    }

    private boolean isDoublePointClap(int i) {
        if (i + 1 >= accelerationVectors.size())
            return false;
        return accelerationVectors.get(i + 1).getZAxis() > (double) threshold / 3.0d;
    }

    private double calculateAvgSpeed() {
        double avgSpeed = 0.0d;
        double totalTimeInSeconds = 0.0d;
        long totalTime = 0;

        for (int i = 1; i < claps.size(); i++) {
            totalTime += claps.get(i).getTime();
        }
        totalTimeInSeconds = getTimeInSeconds(totalTime);
        avgSpeed = claps.size() / totalTimeInSeconds; // claps/second
        avgSpeed *= 60; // claps/minute
        return Helper.changePrecision(avgSpeed, 2);
    }

    private double calculateMaxSpeed() {
        long timePassed = 0;
        long maxSpeed = 0;
        double result = 0.0d;

        maxSpeed = claps.get(claps.size() - 1).getTime();

        for (int i = 1; i < claps.size() - 1; i++) {
            timePassed = claps.get(i).getTime();
            if (timePassed < maxSpeed)
                maxSpeed = timePassed;
        }
        result = 1000000000.0d / maxSpeed; // claps/second
        result *= 60; // claps/minute
        return Helper.changePrecision(result, 2);
    }

    private double calculateAvgForce() {
        double zAcceleration = 0.0d;
        double force = 0.0d;
        double avgForce = 0.0d;

        for (int i = 0; i < claps.size(); i++) {
            zAcceleration = claps.get(i).getzAcceleration();
            force = calculateForce(zAcceleration);
            avgForce += force;
        }
        avgForce /= claps.size();
        return Helper.changePrecision(avgForce, 2);
    }

    private double calculateMaxForce() {
        double zAcceleration = 0.0d;
        double force = 0.0d;
        double maxForce = 0.0d;

        for (int i = 0; i < claps.size(); i++) {
            zAcceleration = claps.get(i).getzAcceleration();
            force = calculateForce(zAcceleration);
            if (force > maxForce)
                maxForce = force;
        }
        return maxForce;
    }

    private int calculateQuality() {
        LinkedList<Double> timeDeviationPercentage = new LinkedList<>();
        LinkedList<Double> forceDeviationPercentage = new LinkedList<>();
        double timeSum = 0.0d;
        double forceSum = 0.0d;
        double avgTime = 0.0d;
        double avgForce = 0.0d;
        double timeQuality = 0.0d;
        double forceQuality = 0.0d;
        int quality = 0;

        for (int i = 0; i < claps.size(); i++) {
            if (i > 0)
                timeSum += getTimeInSeconds(claps.get(i).getTime());
            forceSum += claps.get(i).getzAcceleration();
        }

        avgTime = timeSum / (claps.size() - 1);
        avgForce = forceSum / claps.size();

        for (int i = 1; i < claps.size(); i++) {
            double time = getTimeInSeconds(claps.get(i).getTime());
            double deviation = Math.abs(time - avgTime);
            double deviationPercentage = (deviation * 100.0d) / avgTime;
            timeDeviationPercentage.add(deviationPercentage);
        }

        for (int i = 0; i < timeDeviationPercentage.size(); i++) {
            timeQuality += timeDeviationPercentage.get(i);
        }

        timeQuality = 100.0d - (timeQuality / timeDeviationPercentage.size());

        for (int i = 0; i < claps.size(); i++) {
            double force = claps.get(i).getzAcceleration();
            double deviation = Math.abs(force - avgForce);
            double deviationPercentage = (deviation * 100.0d) / avgForce;
            forceDeviationPercentage.add(deviationPercentage);
        }

        for (int i = 0; i < forceDeviationPercentage.size(); i++) {
            forceQuality += forceDeviationPercentage.get(i);
        }

        forceQuality = 100.0d - (forceQuality / forceDeviationPercentage.size());

        quality = (int) Math.round((timeQuality + forceQuality) / 2);
        return quality;
    }

    private int calculateReflex() {
        return (int) Math.round(getTimeInMiliseconds(reactionTime));
    }

    private double calculateForce(double acceleration) {
        double force = 0.0d;
        double mass = 0.2d; // 200 gramów = 0.2kg - masa układu
        force = mass * acceleration;
        return Helper.changePrecision(force, 2);
    }

    private double getTimeInMiliseconds(long time) {
        double divider = 1000000.0d; // przelicznik z nanosekund na milisekundy
        double t = time / divider;
        return Helper.changePrecision(t, 2);
    }

    private double getTimeInSeconds(long time) {
        double divider = 1000000000.0d; // przelicznik z nanosekund na sekundy
        double t = time / divider;
        return Helper.changePrecision(t, 2);
    }
}
