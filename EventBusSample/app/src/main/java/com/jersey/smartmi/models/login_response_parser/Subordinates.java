package com.jersey.smartmi.models.login_response_parser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by inapp on 24/02/16.
 */
public class Subordinates {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Subordinates{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
