package com.astro.minij.models.kits;

import com.astro.minij.models.content.medication.Medication;

import static org.junit.jupiter.api.Assertions.*;

class FirstAidKitTest {

    @org.junit.jupiter.api.Test
    void testAddedMedicationNotNull() {
        // GIVEN
        final FirstAidKit kit = new FirstAidKit("m");
        Medication testMedication = new Medication("Сироп от кашля", (float)0.5, Medication.medicationType.fluid);
        kit.addMedication(testMedication);

        // WHEN
        final Medication medicationGot = kit.medications.get(0);

        assertNotNull(medicationGot);
        assertEquals(kit.usedCapacity, medicationGot.capacityWeight);
    }

    @org.junit.jupiter.api.Test
    void testAddedMedicationWithIncorrectDosage() {
        // GIVEN
        final FirstAidKit kit = new FirstAidKit("m");
        Medication testMedication = new Medication("Сироп от кашля", (float)0.05, Medication.medicationType.fluid);
        kit.addMedication(testMedication);

        // WHEN
        final Medication medicationGot = kit.medications.get(0);

        assertNotNull(medicationGot);
    }
}