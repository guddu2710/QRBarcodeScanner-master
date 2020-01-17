package com.pitangent.assettracking.model.dashboard;

public class EmployeeModel {
    private Integer id;
    private String employee_name;
    private String employee_code;
    private String employee_designation;
    private String employee_gender;
    private Boolean status;
    private String image;
    private String created_at;
    private String updated_at;

    public EmployeeModel(Integer id, String employee_name, String employee_code, String employee_designation, String employee_gender, Boolean status, String image, String created_at, String updated_at) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_code = employee_code;
        this.employee_designation = employee_designation;
        this.employee_gender = employee_gender;
        this.status = status;
        this.image = image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }

    public String getEmployee_designation() {
        return employee_designation;
    }

    public void setEmployee_designation(String employee_designation) {
        this.employee_designation = employee_designation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmployee_gender() {
        return employee_gender;
    }

    public void setEmployee_gender(String employee_gender) {
        this.employee_gender = employee_gender;
    }
}
