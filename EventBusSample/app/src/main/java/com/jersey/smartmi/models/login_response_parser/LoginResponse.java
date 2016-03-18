package com.jersey.smartmi.models.login_response_parser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jersey.smartmi.models.view_quotes_response_parser.Content;

/**
 * Created by inapp on 24/02/16.
 */
public class LoginResponse {


    @SerializedName("status")
    @Expose
    public int status;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("content")
    @Expose
    Content content = new Content();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }


    public LoginResponse(){
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
