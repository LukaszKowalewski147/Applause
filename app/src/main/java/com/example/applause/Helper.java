package com.example.applause;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Queue;

public class Helper {

    public static double[] convertToDoubleArray(Queue<AccelerationVector> accelerationVectors) {
        Queue<AccelerationVector> entries = new LinkedList<>(accelerationVectors);

        double value = 0.0d;
        int size = entries.size();

        double[] array = new double[size];

        for (int i = 0; i < size; i++) {
            value = entries.poll().getZAxis();
            array[i] = Helper.changePrecision(value, 1);
        }
        return array;
    }

    public static long[] convertTimeToLongArray(Queue<AccelerationVector> accelerationVectors) {

        Queue<AccelerationVector> entries = new LinkedList<>(accelerationVectors);
        int size = entries.size();
        long[] array = new long[size];

        for (int i = 0; i < size; i++) {
            array[i] = entries.poll().getTime();
        }
        return array;
    }

    public static double changePrecision(double value, int precision) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
