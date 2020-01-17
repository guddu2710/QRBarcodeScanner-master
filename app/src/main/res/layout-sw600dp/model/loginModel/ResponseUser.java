package com.pitangent.project.model.loginModel;

import com.google.gson.annotations.SerializedName;

public class ResponseUser {
    @SerializedName("data")
    private UserLogin data;
    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private Boolean success;
    @SerializedName("status")
    private Boolean status;
    @SerializedName("token")
    private String token;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseUser() {
    }

    /**
     *
     * @param message
     * @param status
     * @param data
     * @param success
     */
    public ResponseUser(UserLogin data, String message, Boolean success, Boolean status, String token) {
        super();
        this.data = data;
        this.message = message;
        this.token=token;
        this.success = success;
        this.status = status;
    }

    public UserLogin getData() {
        return data;
    }

    public void setData(UserLogin data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


