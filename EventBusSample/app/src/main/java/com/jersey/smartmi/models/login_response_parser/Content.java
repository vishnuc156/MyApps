package com.jersey.smartmi.models.login_response_parser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by inapp on 24/02/16.
 */
public class Content {

    @SerializedName("userDetails")
    @Expose
    ArrayList<User> userDetails;

    @SerializedName("subordinatesList")
    @Expose
    ArrayList<Subordinates> subordinatesList;

    @SerializedName("userPersmission")
    @Expose
    ArrayList<UserPersmissions> userPermission;

    public ArrayList<User> getUser() {
        return userDetails;
    }

    public void setUser(ArrayList<User> user) {
        userDetails = user;
    }

    public ArrayList<Subordinates> getSubordinatesList() {
        return subordinatesList;
    }

    public void setSubordinatesList(ArrayList<Subordinates> subordinatesList) {
        this.subordinatesList = subordinatesList;
    }

    public ArrayList<UserPersmissions> getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(ArrayList<UserPersmissions> userPermission) {
        this.userPermission = userPermission;
    }

    @Override
    public String toString() {
        return "Content{" +
                "User=" + userDetails +
                ", subordinatesList=" + subordinatesList +
                ", userPermission=" + userPermission +
                '}';
    }
}
