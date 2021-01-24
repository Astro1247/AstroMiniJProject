package com.astro.minij;

import com.astro.minij.models.content.air.AmbuBag;
import com.astro.minij.models.content.medication.Medication;
import com.astro.minij.models.entity.Matrix;
import com.astro.minij.models.kits.FirstAidKit;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to MiniJ project!");
        Matrix matrix = new Matrix(100, 100);
        for (int i = 1; i < matrix.getWidth()+1; i++) {
            for (int j = 1; j < matrix.getHeight()+1; j++) {
                matrix.setValue(i, j, getRandomFloat(-1000, 1000));
            }
        }
        //matrix.setValue(100, 100, 15);
        System.out.println(matrix.getValue(100, 100));
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
