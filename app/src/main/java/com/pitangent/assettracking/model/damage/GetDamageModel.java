package com.pitangent.assettracking.model.damage;

import com.pitangent.assettracking.model.scanning.AssetModel;

import java.util.ArrayList;

public class GetDamageModel {
    private ArrayList<AssetModel> data = null;
    private String message;
    private Boolean status;

    public GetDamageModel(String message, Boolean status) {
        this.message = message;
        this.status = status;
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

    public ArrayList<AssetModel> getData() {
        return data;
    }

    public void setData(ArrayList<AssetModel> data) {
        this.data = data;
    }
}
