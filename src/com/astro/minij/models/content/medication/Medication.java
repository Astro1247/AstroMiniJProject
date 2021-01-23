package com.astro.minij.models.content.medication;

import com.astro.minij.exceptions.*;
import com.astro.minij.models.content.Item;

import java.util.Arrays;
import java.util.List;

public class Medication extends Item {
    //private final String[] types = new String[]{"cream", "fluid", "gel", "salt", "tablet"};
    public final String name;
    public final medicationType type;
    public final float dosage;
    private float residue;
    public enum medicationType{
        cream,
        fluid,
        gel,
        salt,
        tablet
    }

    public float getResidue() {
        return this.residue;
    }

    public void refillResidue() {
        switch (this.type) {
            case cream, gel -> {
                final int koef = 5;
                this.residue = this.dosage*koef;
            }
            case fluid -> {
                final int koef = 15;
                this.residue = this.dosage*koef;
            }
            case salt -> {
                final int koef = 50;
                this.residue = this.dosage*koef;
            }
            case tablet -> {
                final int koef = 20;
                this.residue = this.dosage*koef;
            }
            default -> throw new TypeNotFoundException();
        }
    }

    public Medication (final String name, float dosage, medicationType type) {
        try {
            if (name.equals("")) throw new NotEmptyStringExpectedException();
            if (name.length() > 32) throw new TooLongNameException();
            if (dosage < 0.1) throw new TooLowDosageException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.name = name;
        this.type = type;
        this.dosage = dosage;
        capacityWeightGenerator();
        this.minFirstAidKitSize = "s";
    }

    public void use() {
        if(this.residue<this.dosage) throw new NoLongerAvailableException(this.name);
        else {
            this.residue = this.residue-this.dosage;
        }
    }

    private void capacityWeightGenerator() {
        switch (this.type) {
            case cream, gel -> {
                final int koef = 5;
                this.residue = this.dosage*koef;
                if((this.dosage*koef)>Short.MAX_VALUE) throw new TooBigDosageException();
                this.capacityWeight = (short) Math.floor(this.dosage * koef);
            }
            case fluid -> {
                final int koef = 15;
                this.residue = this.dosage*koef;
                if((this.dosage*koef)>Short.MAX_VALUE) throw new TooBigDosageException();
                this.capacityWeight = (short) Math.floor(this.dosage * koef);
            }
            case salt -> {
                final int koef = 50;
                this.residue = this.dosage*koef;
                if((this.dosage*koef)>Short.MAX_VALUE) throw new TooBigDosageException();
                this.capacityWeight = (short) Math.floor(this.dosage * koef);
            }
            case tablet -> {
                final int koef = 20;
                this.residue = this.dosage*koef;
                if((this.dosage*koef)>Short.MAX_VALUE) throw new TooBigDosageException();
                this.capacityWeight = (short) Math.floor(this.dosage * koef);
            }
            default -> throw new TypeNotFoundException();
        }
    }
}
