package com.astro.minij.models.content.medication;

import com.astro.minij.exceptions.NotEmptyStringExpectedException;
import com.astro.minij.exceptions.TooLongNameException;
import com.astro.minij.exceptions.TypeNotFoundException;

import java.util.Arrays;
import java.util.List;

public class Medication {
    private final String[] types = new String[]{"cream", "fluid", "gel", "salt", "tablet"};
    List<String> typesList = Arrays.asList(types);
    String name;
    String type;
    float dosage;

    public Medication (String name, float dosage, String type) {
        if (name.equals("")) throw new NotEmptyStringExpectedException();
        if (name.length() > 32) throw new TooLongNameException();
        if (!typesList.contains(type)) throw new TypeNotFoundException();

        this.name = name;
        this.type = type;
        this.dosage = dosage;
    }
}
