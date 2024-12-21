package com.example.RecipeApp;

public class Recipe {
    private final String name;
    private final int quality;
    private final int difficulty;

    public Recipe(String name, int quality, int difficulty) {
        this.name = name;
        this.quality = quality;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public int getQuality() {
        return quality;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
