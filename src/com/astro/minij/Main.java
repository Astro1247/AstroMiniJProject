package com.astro.minij;

import com.astro.minij.models.content.air.AmbuBag;
import com.astro.minij.models.content.medication.Medication;
import com.astro.minij.models.kits.FirstAidKit;

public class Main {

    public static void main(String[] args) {
        // TODO: Собрать аптечку ПМП
        System.out.println("Welcome to MiniJ project!");
        FirstAidKit firstAidKit = new FirstAidKit("m");
        Medication analgin = new Medication("Анальгин", 1, Medication.medicationType.tablet);
        firstAidKit.addMedication(analgin);
        System.out.println(firstAidKit.medications.size());
        System.out.println(firstAidKit.toString());
    }
}
