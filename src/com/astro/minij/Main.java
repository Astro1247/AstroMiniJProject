package com.astro.minij;

import com.astro.minij.models.content.air.AmbuBag;
import com.astro.minij.models.content.medication.Medication;
import com.astro.minij.models.entity.Matrix;
import com.astro.minij.models.kits.FirstAidKit;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to MiniJ project!");
        Matrix matrix = new Matrix(100, 100);
        matrix.setValue(100, 100, 15);
        System.out.println(matrix.getValue(100, 100));
    }

    public static void firstAidKit() {
        FirstAidKit firstAidKit = new FirstAidKit("m");
        Medication analgin = new Medication("Анальгин", 1, Medication.medicationType.tablet);
        firstAidKit.addMedication(analgin);
        System.out.println(firstAidKit.medications.size());
        System.out.println(firstAidKit.toString());
    }
}
