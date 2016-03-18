package com.jersey.smartmi.models.login_response_parser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by inapp on 24/02/16.
 */
public class UserPersmissions {

    @SerializedName("module")
    @Expose
    String module;

    @SerializedName("Access")
    @Expose
    int access;

    @SerializedName("AddOns")
    @Expose
    int[] addons;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public int[] getAddons() {
        return addons;
    }

    public void setAddons(int[] addons) {
        this.addons = addons;
    }

    @Override
    public String toString() {
        return "UserPersmissions{" +
                "module='" + module + '\'' +
                ", access=" + access +
                ", addons=" + Arrays.toString(addons) +
                '}';
    }
}
