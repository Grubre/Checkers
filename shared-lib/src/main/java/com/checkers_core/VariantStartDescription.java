package com.checkers_core;

public class VariantStartDescription {
    int width;
    int height;
    String name;
    String color;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public VariantStartDescription(int width, int height, String name, String color) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.color = color;
    }
}
