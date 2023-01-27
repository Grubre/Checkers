package com.checkers_core;

public class VariantStartDescription {
    int width;
    int height;
    String name;
    String color;

    
    /** 
     * @return int
     */
    public int getWidth() {
        return width;
    }

    
    /** 
     * @return int
     */
    public int getHeight() {
        return height;
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @return String
     */
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
