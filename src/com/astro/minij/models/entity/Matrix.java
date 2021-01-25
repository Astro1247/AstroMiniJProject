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
        if(width < 0 || height < 0) throw new IllegalArgumentException();
        this.values[width][height] = new ArrayList();
        this.values[width][height].add(value);
    }

    public float getValue(int width, int height) {
        if(width < 0 || height < 0) throw new IllegalArgumentException();
        return this.values[width][height]==null?null:((float) this.values[width][height].get(0));
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void display() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.getValue(j, i) + " ");
            }
            System.out.println();
        }
    }
}
