package com.pitangent.project.model.detailspage;

import com.pitangent.project.model.scanning.AssetModel;

import java.util.ArrayList;

public class GetEmployeeAssetModel {
    private ArrayList<AssetModel> data = null;
    private String message;
    private Boolean status;

    public GetEmployeeAssetModel(ArrayList<AssetModel> data, String message, Boolean status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public ArrayList<AssetModel> getData() {
        return data;
    }

    public void setData(ArrayList<AssetModel> data) {
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
