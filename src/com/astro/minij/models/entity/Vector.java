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
        if(index <= 0) throw new IllegalArgumentException();
        this.values[index-1] = new ArrayList();
        this.values[index-1].add(value);
    }

    public float getValue(int index) {
        if(index <= 0) throw new IllegalArgumentException();
        return this.values[index-1]==null?null:((float) this.values[index-1].get(0));
    }

    public int getSize() {
        return this.size;
    }
}
