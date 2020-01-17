package com.pitangent.assettracking.model.scanning;

public class AssetModel {
    private Integer id;
    private String asset_name;
    private String employee_id;
    private String category_id;
    private String asset_manufacturer;
    private String asset_model_no;
    private String office_asset_id;
    private String asset_sku_no;
    private String accessories_id;
    private String asset_serial_no;
    private String asset_qr_code;
    private String asset_purchased_date;
    private String status;
    private String created_at;
    private String updated_at;

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    private String employee_name;
    private String category_name;


    public AssetModel(Integer id, String asset_name, String employee_id, String category_id, String asset_manufacturer, String asset_model_no, String office_asset_id, String asset_sku_no, String accessories_id, String asset_serial_no, String asset_qr_code, String asset_purchased_date, String status, String created_at, String updated_at, String employee_name, String category_name) {
        this.id = id;
        this.asset_name = asset_name;
        this.employee_id = employee_id;
        this.category_id = category_id;
        this.asset_manufacturer = asset_manufacturer;
        this.asset_model_no = asset_model_no;
        this.office_asset_id = office_asset_id;
        this.asset_sku_no = asset_sku_no;
        this.accessories_id = accessories_id;
        this.asset_serial_no = asset_serial_no;
        this.asset_qr_code = asset_qr_code;
        this.asset_purchased_date = asset_purchased_date;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.category_name=category_name;
        this.employee_name=employee_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getAsset_manufacturer() {
        return asset_manufacturer;
    }

    public void setAsset_manufacturer(String asset_manufacturer) {
        this.asset_manufacturer = asset_manufacturer;
    }

    public String getAsset_model_no() {
        return asset_model_no;
    }

    public void setAsset_model_no(String asset_model_no) {
        this.asset_model_no = asset_model_no;
    }

    public String getOffice_asset_id() {
        return office_asset_id;
    }

    public void setOffice_asset_id(String office_asset_id) {
        this.office_asset_id = office_asset_id;
    }

    public String getAsset_sku_no() {
        return asset_sku_no;
    }

    public void setAsset_sku_no(String asset_sku_no) {
        this.asset_sku_no = asset_sku_no;
    }

    public String getAccessories_id() {
        return accessories_id;
    }

    public void setAccessories_id(String accessories_id) {
        this.accessories_id = accessories_id;
    }

    public String getAsset_serial_no() {
        return asset_serial_no;
    }

    public void setAsset_serial_no(String asset_serial_no) {
        this.asset_serial_no = asset_serial_no;
    }

    public String getAsset_qr_code() {
        return asset_qr_code;
    }

    public void setAsset_qr_code(String asset_qr_code) {
        this.asset_qr_code = asset_qr_code;
    }

    public String getAsset_purchased_date() {
        return asset_purchased_date;
    }

    public void setAsset_purchased_date(String asset_purchased_date) {
        this.asset_purchased_date = asset_purchased_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

