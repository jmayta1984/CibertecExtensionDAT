package pe.edu.cibertec.pokeapi;

import com.google.gson.annotations.SerializedName;

public class Pokemon {

    @SerializedName("name")
    private String name;

    @SerializedName("weight")
    private int weight;

    @SerializedName("height")
    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Pokemon() {
    }
}
