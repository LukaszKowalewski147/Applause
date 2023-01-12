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

        for (int i = 0; i < accelerationVectors.size(); i++) {
            totalTime += accelerationVectors.get(i).getTime();
        }
        totalTimeInSeconds = getTimeInSeconds(totalTime);
        avgSpeed = claps.size() / totalTimeInSeconds; // claps / second
        avgSpeed *= 60; // claps / minute
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
        int i = 0;
        Double sumaCzasuKlasniec = 0.0;
        Double sumaMocyKlasniec =  0.0;

        // srednia algebraiczna czasu na klasniecie i mocy.
        for(Clap clap : claps){
            i++;
            sumaCzasuKlasniec = sumaCzasuKlasniec + getTimeInSeconds(clap.getTime());
            sumaMocyKlasniec = sumaMocyKlasniec +  clap.getzAcceleration();
        }
        System.out.println("SUMA: " + sumaCzasuKlasniec + " sekund");
        System.out.println("SUMA: " + sumaMocyKlasniec + " moc");
        Double sredniaSumaCzasuKlasniec = sumaCzasuKlasniec / i;
        Double sredniaSumaMocyKlasniec = sumaMocyKlasniec / i;




// jakosc czasu !!!!
        LinkedList<Double> roznicaProcentowaCzasu = new LinkedList<>();
        for(Clap clap : claps){
            Double klasniecieCzas = getTimeInSeconds(clap.getTime());

            if(klasniecieCzas == sredniaSumaCzasuKlasniec ){
                roznicaProcentowaCzasu.add(0.0);
            } else if (klasniecieCzas > sredniaSumaCzasuKlasniec) {
                roznicaProcentowaCzasu.add((klasniecieCzas - sredniaSumaCzasuKlasniec) / klasniecieCzas * 100);
            }else {
                roznicaProcentowaCzasu.add((sredniaSumaCzasuKlasniec - klasniecieCzas) / sredniaSumaCzasuKlasniec * 100);
            }
        }

        double jakoscCzasu = 0;
        for(int x =0; x < roznicaProcentowaCzasu.size(); x++){
            jakoscCzasu = jakoscCzasu + roznicaProcentowaCzasu.get(x);
            System.out.println("ROZNICA CZASU % " + roznicaProcentowaCzasu.get(x));
        }
        jakoscCzasu = 100 - (jakoscCzasu   / roznicaProcentowaCzasu.size());
        System.out.println("JAKOSC CZASU % " + (jakoscCzasu));


        // jakosc sily  !!!!
        LinkedList<Double> roznicaProcentowaSily = new LinkedList<>();
        for(Clap clap : claps){
            Double silaMoc = clap.getzAcceleration();

            if(silaMoc == sredniaSumaMocyKlasniec ){
                roznicaProcentowaSily.add(0.0);
            } else if (silaMoc > sredniaSumaMocyKlasniec) {
                roznicaProcentowaSily.add((silaMoc - sredniaSumaMocyKlasniec) / silaMoc * 100);
            }else {
                roznicaProcentowaSily.add((sredniaSumaMocyKlasniec - silaMoc) / sredniaSumaMocyKlasniec * 100);
            }
        }

        double jakoscMocy = 0;
        for(int x =0; x < roznicaProcentowaSily.size(); x++){
            jakoscMocy = jakoscMocy + roznicaProcentowaSily.get(x);
            System.out.println("ROZNICA MOCY % " + roznicaProcentowaSily.get(x));
        }
        jakoscMocy = 100 - (jakoscMocy   / roznicaProcentowaSily.size()) ;
        System.out.println("JAKOSC MOCY % " + (jakoscMocy));


        // ogolna jakosc ((sila  + czas) / 2))
        double ogolnaJakosc = (jakoscCzasu + jakoscMocy) / 2;
        System.out.println("OGOLA JAKOSC: " + ogolnaJakosc);



        return (int)ogolnaJakosc;
    }

    private int calculateReflex() {
        int reflex = 0;
        return reflex;
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
