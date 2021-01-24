package com.astro.minij.models.entity;

import com.astro.minij.exceptions.IllegalMatrixSizeException;

import java.util.ArrayList;

public class Matrix {
    private int width;
    private int height;
    public ArrayList[][] values;

    public Matrix(int width, int height) {
        if(width <= 0 || height <= 0) throw new IllegalMatrixSizeException();
        this.width = width;
        this.height = height;
        this.values = new ArrayList[this.width][this.height];
    }

    public void setValue(int width, int height, float value) {
        if(width <= 0 || height <= 0) throw new IllegalArgumentException();
        this.values[width-1][height-1] = new ArrayList();
        this.values[width-1][height-1].add(value);
    }

    public float getValue(int width, int height) {
        if(width <= 0 || height <= 0) throw new IllegalArgumentException();
        return this.values[width-1][height-1]==null?null:((float) this.values[width-1][height-1].get(0));
    }
}
