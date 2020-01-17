package com.pitangent.project.model.scanning;

public class GetAssetModel {

    AssetModel data;

    private String message;
    private Boolean status;

    public GetAssetModel(AssetModel data, String message, Boolean status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public AssetModel getData() {
        return data;
    }

    public void setData(AssetModel data) {
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
