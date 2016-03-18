package com.jersey.smartmi.models.login_response_parser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by inapp on 24/02/16.
 */
public class User {
    @SerializedName("accessToken")
    @Expose
    String accessToken;

    @SerializedName("employeeId")
    @Expose
    int employeeId;

    @SerializedName("employeeName")
    @Expose
    String employeeName;

    @SerializedName("roleID")
    @Expose
    int roleID;

    @SerializedName("roleName")
    @Expose
    String roleName;

    @SerializedName("designationId")
    @Expose
    int designationId;

    @SerializedName("designationName")
    @Expose
    String designationName;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getDesignationId() {
        return designationId;
    }

    public void setDesignationId(int designationId) {
        this.designationId = designationId;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    @Override
    public String toString() {
        return "User{" +
                "accessToken='" + accessToken + '\'' +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", designationId=" + designationId +
                ", designationName='" + designationName + '\'' +
                '}';
    }
}
