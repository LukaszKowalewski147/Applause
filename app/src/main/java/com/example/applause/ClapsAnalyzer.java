package com.example.applause;

import java.util.LinkedList;

public class ClapsAnalyzer {
    private final LinkedList<AccelerationVector> accelerationVectors;
    private final SessionType sessionType;
    private final LinkedList<Clap> claps;
    private final int threshold;

    public ClapsAnalyzer(LinkedList<AccelerationVector> accelerationVectors, SessionType sessionType) {
        this.accelerationVectors = accelerationVectors;
        this.sessionType = sessionType;
        claps = new LinkedList<Clap>();
        threshold = 60;
    }

    public ClapsSession analyze() {
        ClapsSession clapsSession = new ClapsSession(sessionType);
        findClaps();

        if (claps.size() < 2)
            return clapsSession;
        else
            clapsSession.setNoClaps(false);

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

                if (isDoublePointClap(i)) {
                    acceleration += accelerationVectors.get(i + 1).getZAxis();
                    timeInterwal += accelerationVectors.get(i + 1).getTime();
                    if (isDoublePointClap(i + 1)) {
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
        return accelerationVectors.get(i + 1).getZAxis() > (double) threshold / 2.0d;
    }

    private double calculateAvgSpeed() {
        double avgSpeed = 0.0d;
        double totalTimeInSeconds = 0.0d;
        long totalTime = 0;

        for (int i = 0; i < accelerationVectors.size(); i++) {
            totalTime += accelerationVectors.get(i).getTime();
        }
        totalTimeInSeconds = getTimeInSeconds(totalTime);
        avgSpeed = claps.size() / totalTimeInSeconds; // claps / second
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
        result = 1000000000.0d / maxSpeed; // sekunda/x = n claps/second
        return Helper.changePrecision(result, 2);
    }

    private double calculateAvgForce() {
        double force = 0.0d;
        double avgForce = 0.0d;

        for (int i = 0; i < claps.size(); i++) {
            force = claps.get(i).getForce();
            avgForce += force;
        }
        avgForce /= claps.size();
        return Helper.changePrecision(avgForce, 2);
    }

    private double calculateMaxForce() {
        double force = 0.0d;
        double maxForce = 0.0d;

        for (int i = 0; i < claps.size(); i++) {
            force = claps.get(i).getForce();
            if (force > maxForce)
                maxForce = force;
        }
        return maxForce;
    }

    private int calculateQuality() {
        int quality = 0;
        return quality;
    }

    private int calculateReflex() {
        int reflex = 0;
        return reflex;
    }

    private double calculatePower(int sample) {
        double power = 0.0d;
        double mass = 0.2d; // 200 gramów = 0.2kg - masa układu
        double acceleration = accelerationVectors.get(sample).getZAxis();
        power = mass * acceleration;
        return Helper.changePrecision(power, 1);
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
