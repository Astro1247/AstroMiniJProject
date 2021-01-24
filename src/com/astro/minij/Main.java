package com.astro.minij;

import com.astro.minij.models.content.medication.Medication;
import com.astro.minij.models.entity.Matrix;
import com.astro.minij.models.entity.Vector;
import com.astro.minij.models.kits.FirstAidKit;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to MiniJ project!");
        int n = 100;
        Matrix matrix = new Matrix(n, n);
        for (int i = 0; i < matrix.getWidth(); i++) {
            for (int j = 0; j < matrix.getHeight(); j++) {
                matrix.setValue(i, j, getRandomFloat(-n, n));
            }
        }
        Vector vector = new Vector(n);
        for(int i = 0; i < vector.getSize(); i++) {
            vector.setValue(i, getRandomFloat(-n, n));
        }
        long minSingleThreadExecTime = Long.MAX_VALUE;
        long maxSingleThreadExecTime = Long.MIN_VALUE;
        for (int i = 0; i < 100; i++) {
            long execTime = singleThreadMatrixVectorMultiplier(matrix, vector);
            if (minSingleThreadExecTime > execTime) minSingleThreadExecTime = execTime;
            if (maxSingleThreadExecTime < execTime) maxSingleThreadExecTime = execTime;
        }
        System.out.println("Single thread:\nMin exec time: " + minSingleThreadExecTime + "\nMax exec time: " + maxSingleThreadExecTime);
    }

    public static long singleThreadMatrixVectorMultiplier(Matrix matrix, Vector vector) {
        long startTime = System.nanoTime();
        Vector resultVector = new Vector(matrix.getHeight());
        for (int i = 0; i < matrix.getHeight(); i++) {
            float newValue = 0;
            for (int j = 0; j < matrix.getWidth(); j++) {
                newValue += matrix.getValue(j, i)*vector.getValue(j);
            }
            resultVector.setValue(i, newValue);
        }
        long stopTime = System.nanoTime();
        //System.out.println("Single thread execution time: " + (stopTime - startTime));
        return stopTime - startTime;
    }

    public static float getRandomFloat(float min, float max) {
        double random = min + Math.random() * (max - min);
        return (float) random;
    }

    public static void firstAidKit() {
        FirstAidKit firstAidKit = new FirstAidKit("m");
        Medication analgin = new Medication("Анальгин", 1, Medication.medicationType.tablet);
        firstAidKit.addMedication(analgin);
        System.out.println(firstAidKit.medications.size());
        System.out.println(firstAidKit.toString());
    }
}
