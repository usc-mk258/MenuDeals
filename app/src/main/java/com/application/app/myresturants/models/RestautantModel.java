package com.application.app.myresturants.models;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RestautantModel implements Serializable {



    String id;
    String name;
    String email;
    ArrayList image_url;
    AdressModel address;
    String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList getImage_url() {
        return image_url;
    }

    public void setImage_url(ArrayList image_url) {
        this.image_url = image_url;
    }

    public AdressModel getAddress() {
        return address;
    }

    public void setAddress(AdressModel address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
