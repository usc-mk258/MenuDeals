package com.application.app.myresturants.models;

import java.io.Serializable;
import java.util.ArrayList;

public class AdressModel implements Serializable {

    String type;
    ArrayList coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList coordinates) {
        this.coordinates = coordinates;
    }
}
