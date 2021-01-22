package com.astro.minij.models.kits;

import com.astro.minij.exceptions.InvalidFirstAidKitSizeException;
import com.astro.minij.exceptions.NoSpaceLeftInFirstAidKitException;
import com.astro.minij.models.content.air.AmbuBag;
import com.astro.minij.models.content.medication.Medication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstAidKit {
    public final short capacity;
    public final String size;
    public short usedCapacity;
    public ArrayList<Medication> medications;
    public ArrayList<AmbuBag> ambuBag;

    @Override
    public String toString() {
        return "First aid kit " + this.size + " size, capacity used " + this.usedCapacity + " of " + this.capacity;
    }

    public FirstAidKit(String size) {
        String[] allowedSizes = new String[]{"s", "m", "l", "xl"};
        List<String> allowedSizesList = Arrays.asList(allowedSizes);
        if(!allowedSizesList.contains(size)) throw new InvalidFirstAidKitSizeException(size);
        this.size = size;
        switch (size) {
            case "s" -> this.capacity = 100;
            case "m" -> this.capacity = 250;
            case "l" -> this.capacity = 500;
            case "xl" -> this.capacity = 1000;
            default -> throw new InvalidFirstAidKitSizeException(size);
        }
        this.usedCapacity = 0;
        this.medications = new ArrayList<>();
        this.ambuBag = new ArrayList<>();
    }

    private boolean isSizeBiggerOrEqual(String actualSize, String comparedSize) {
        if (actualSize.equals(comparedSize)) return true;
        switch (actualSize) {
            case "s" -> {
                return false;
            }
            case "m" -> {
                if(comparedSize.equals("s")) return true;
            }
            case "l" -> {
                if(comparedSize.equals("m") || comparedSize.equals("s")) return true;
            }
            case "xl" -> {
                if(comparedSize.equals("l") || comparedSize.equals("m") || comparedSize.equals("s")) return true;
            }
            default -> throw new InvalidFirstAidKitSizeException(comparedSize);
        }
        return false;
    }

    public void addMedication(Medication medication) {
        if(medication.capacityWeight > (this.capacity-this.usedCapacity)) throw new NoSpaceLeftInFirstAidKitException();
        if(!isSizeBiggerOrEqual(this.size, medication.minFirstAidKitSize)) throw new NoSpaceLeftInFirstAidKitException();
        this.medications.add(medication);
        this.usedCapacity += medication.capacityWeight;
    }

    public void addAmbuBag(AmbuBag ambuBag) {
        if(ambuBag.capacityWeight > (this.capacity-this.usedCapacity)) throw new NoSpaceLeftInFirstAidKitException();
        if(!isSizeBiggerOrEqual(this.size, ambuBag.minFirstAidKitSize)) throw new NoSpaceLeftInFirstAidKitException();
        this.ambuBag.add(ambuBag);
        this.usedCapacity += ambuBag.capacityWeight;
    }
}
