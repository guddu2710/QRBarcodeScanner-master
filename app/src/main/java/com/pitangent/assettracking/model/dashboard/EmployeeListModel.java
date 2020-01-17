package com.pitangent.assettracking.model.dashboard;

import java.util.ArrayList;

public class EmployeeListModel {
    private ArrayList<EmployeeModel> data = null;
    private String message;
    private Boolean status;

    public EmployeeListModel(ArrayList<EmployeeModel> data, String message, Boolean status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public ArrayList<EmployeeModel> getData() {
        return data;
    }

    public void setData(ArrayList<EmployeeModel> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
