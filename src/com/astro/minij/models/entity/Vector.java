package com.astro.minij.models.entity;

import java.util.ArrayList;

public class Vector {
    private int size;
    public ArrayList[] values;

    public Vector(int size) {
        if(size <= 0) throw new IllegalArgumentException();
        this.size = size;
        this.values = new ArrayList[this.size];
    }

    public void setValue(int index, float value) {
        if(index < 0) throw new IllegalArgumentException();
        this.values[index] = new ArrayList();
        this.values[index].add(value);
    }

    public float getValue(int index) {
        if(index < 0) throw new IllegalArgumentException();
        return this.values[index]==null?null:((float) this.values[index].get(0));
    }

    public int getSize() {
        return this.size;
    }

    public void display() {
        for (int j = 0; j < this.size; j++) {
            System.out.println(this.getValue(j) + " ");
        }
    }
}
