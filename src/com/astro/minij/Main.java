package com.astro.minij;

import com.astro.minij.models.content.air.AmbuBag;
import com.astro.minij.models.content.medication.Medication;
import com.astro.minij.models.entity.Matrix;
import com.astro.minij.models.entity.Vector;
import com.astro.minij.models.kits.FirstAidKit;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to MiniJ project!");
        int n = 100;
        Matrix matrix = new Matrix(n, n);
        for (int i = 1; i < matrix.getWidth()+1; i++) {
            for (int j = 1; j < matrix.getHeight()+1; j++) {
                matrix.setValue(i, j, getRandomFloat(-n, n));
            }
        }
        Vector vector = new Vector(n);
        for(int i = 1; i < vector.getSize(); i++) {
            vector.setValue(i, getRandomFloat(-n, n));
        }
        System.out.println(matrix.getValue(n, n));
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
