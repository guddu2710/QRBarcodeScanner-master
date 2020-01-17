package com.pitangent.assettracking.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.pitangent.assettracking.Api.MainApplication;
import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.damage.GetDamageModel;
import com.pitangent.assettracking.model.scanning.AssetModel;
import com.pitangent.assettracking.model.scanning.GetAssignedModel;
import com.pitangent.assettracking.model.scanning.GetFreeModel;
import com.pitangent.assettracking.ui.dashboard.Dashboard;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {
    ProgressDialog progressDialog;
    TextView txt_asset_name_show,txt_asset_name,txt_category_name_show,txt_category_name,txt_employee_name_show,
            txt_employee_name,txt_asset_manufacturer_show,txt_asset_manufacturer,txt_asset_model_no_show,txt_asset_model_no,
            txt_office_asset_id_show,txt_office_asset_id,txt_asset_serial_no_show,txt_asset_serial_no_id,txt_asset_purchased_date_show,
            txt_asset_purchased_date_id,txt_status_show,txt_status_id;
    ConnectionCheck connectionCheck=new ConnectionCheck();
    public Activity c;
    public Dialog d;
    public Button btn_cancel,btn_type;
    AssetModel data;
    ShowToast showToast=new ShowToast();
    TextView txt_dia;
    public CustomDialogClass(Activity a, AssetModel data) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.data=data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        initView();


    }

    private void initView() {
        txt_asset_name_show=findViewById(R.id.txt_asset_name_show);
        txt_asset_name=findViewById(R.id.txt_asset_name);
        txt_category_name_show=findViewById(R.id.txt_category_name_show);
        txt_category_name=findViewById(R.id.txt_category_name);
        txt_employee_name_show=findViewById(R.id.txt_employee_name_show);
        txt_employee_name=findViewById(R.id.txt_employee_name);
        txt_asset_manufacturer_show=findViewById(R.id.txt_asset_manufacturer_show);
        txt_asset_manufacturer=findViewById(R.id.txt_asset_manufacturer);
        txt_asset_model_no_show=findViewById(R.id.txt_asset_model_no_show);
        txt_asset_model_no=findViewById(R.id.txt_asset_model_no);
        txt_office_asset_id_show=findViewById(R.id.txt_office_asset_id_show);
        txt_office_asset_id=findViewById(R.id.txt_office_asset_id);
        txt_asset_serial_no_show=findViewById(R.id.txt_asset_serial_no_show);
        txt_asset_serial_no_id=findViewById(R.id.txt_asset_serial_no_id);
        txt_asset_purchased_date_show=findViewById(R.id.txt_asset_purchased_date_show);
        txt_asset_purchased_date_id=findViewById(R.id.txt_asset_purchased_date_id);
        txt_status_show=findViewById(R.id.txt_status_show);
        txt_status_id=findViewById(R.id.txt_status_id);
        btn_cancel=findViewById(R.id.btn_cancel);
        btn_type=findViewById(R.id.btn_type);
        setValues();

    }

    private void setValues() {
        txt_asset_name_show.setText("Asset name");
        txt_asset_name.setText(data.getAsset_name());
        txt_category_name_show.setText("Category name");
        txt_category_name.setText(data.getCategory_name());
        txt_employee_name_show.setText("Employee name");
        txt_employee_name.setText(data.getEmployee_name());
        txt_asset_manufacturer_show.setText("Manufacturer");
        txt_asset_manufacturer.setText(data.getAsset_manufacturer());
        txt_asset_model_no_show.setText("Model no.");
        txt_asset_model_no.setText(data.getAsset_model_no());
        txt_office_asset_id_show.setText("Office asset no.");
        txt_office_asset_id.setText(data.getOffice_asset_id());
        txt_asset_serial_no_show.setText("Serial no.");
        txt_asset_serial_no_id.setText(data.getAsset_serial_no());
        txt_asset_purchased_date_show.setText("Purchased date");
        txt_asset_purchased_date_id.setText(data.getAsset_purchased_date());
        txt_status_show.setText("status");
        if(data.getStatus().equalsIgnoreCase("1")){
            txt_status_id.setText("Assigned");
        }
        else if(data.getStatus().equalsIgnoreCase("2")){
            txt_status_id.setText("Damaged");
        }
        else {
            txt_status_id.setText("Free");
        }
        if(SharedPreferencesManager.getInstance().getAssignType().equalsIgnoreCase("Assign")){
            btn_type.setText("Assign");
        }
        else if(SharedPreferencesManager.getInstance().getAssignType().equalsIgnoreCase("free")){
            btn_type.setText("Free");
        }
        else {
            btn_type.setText("Damage");
        }
        btn_cancel.setOnClickListener(this);
        btn_type.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_type:
                if(SharedPreferencesManager.getInstance().getAssignType().equalsIgnoreCase("Assign")){
                    progressDialog = new ProgressDialog(c);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Assigning...");
                    progressDialog.show();
                    assignAsste();
                    break;
                }
                else if(SharedPreferencesManager.getInstance().getAssignType().equalsIgnoreCase("free")){

                    progressDialog = new ProgressDialog(c);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    freeAsste();
                    break;
                }
                else {

                    progressDialog = new ProgressDialog(c);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Assigning...");
                    progressDialog.show();
                    damageAsste();
                    break;
                }

            case R.id.btn_cancel:
                dismiss();
                Intent intent=new Intent(c, Dashboard.class);
                c.startActivity(intent);
                c.finish();
                break;
            default:
                break;
        }
        dismiss();
    }




    public  void assignAsste(){
        String token=SharedPreferencesManager.getInstance().getToken();
        if (connectionCheck.ConnectionCheck(c)){
            MainApplication.apiManager.getassignAsset(token,AppData.empid,data.getId().toString(),"1", new Callback<GetAssignedModel>() {
                @Override
                public void onResponse(Call<GetAssignedModel> call, Response<GetAssignedModel> response) {

                    final GetAssignedModel responseUser = response.body();
                    Log.d("re",""+response.raw());
                    if (response.isSuccessful() && responseUser != null) {

//                   Toast.makeText(getActivity(),"Successfull",Toast.LENGTH_LONG).show();
                        try {
                            progressDialog.dismiss();
                            showToast.showToast(c,"Successfully updated");
                            Intent intent=new Intent(c, Dashboard.class);
                            c.startActivity(intent);
                            c.finish();


                        }
                        catch (Exception e){
                            progressDialog.dismiss();
                        }
                    } else {
//                    Toast.makeText(getActivity(),"Something wrong",Toast.LENGTH_LONG).show();

                    }
                }
                @Override
                public void onFailure(Call<GetAssignedModel> call, Throwable t) {

                    progressDialog.dismiss();
                    Log.d("error",""+t.getMessage());
                    showToast.showToast(c,"Internal Error"+t.getMessage());

                }
            });

        }
        else {
            progressDialog.dismiss();
            showToast.showToast(c,"Network error");
        }
    }
    public  void freeAsste(){
        String token=SharedPreferencesManager.getInstance().getToken();
        if (connectionCheck.ConnectionCheck(c)){
//            showToast.showToast(getContext(),""+data.getId().toString());

            MainApplication.apiManager.getfreeAsset(token,"0",data.getId().toString(),"0", new Callback<GetFreeModel>() {
                @Override
                public void onResponse(Call<GetFreeModel> call, Response<GetFreeModel> response) {

                    final GetFreeModel responseUser = response.body();
                    if (response.isSuccessful() && responseUser != null) {

//                   Toast.makeText(getActivity(),"Successfull",Toast.LENGTH_LONG).show();
                        try {
                            progressDialog.dismiss();
                            showToast.showToast(getContext(),""+responseUser.getMessage());
                            Intent intent=new Intent(getContext(), Dashboard.class);
                            c.startActivity(intent);
                            c.finish();


                        }
                        catch (Exception e){
                            progressDialog.dismiss();
                        }
                    } else {
                        progressDialog.dismiss();
//                    Toast.makeText(getActivity(),"Something wrong",Toast.LENGTH_LONG).show();

                    }
                }
                @Override
                public void onFailure(Call<GetFreeModel> call, Throwable t) {
                    progressDialog.dismiss();

                    showToast.showToast(c,"Internal Error");

                }
            });

        }
        else {
            progressDialog.dismiss();
            showToast.showToast(c,"Network error");

        }
    }
    public  void damageAsste(){
        String token=SharedPreferencesManager.getInstance().getToken();
        if (connectionCheck.ConnectionCheck(c)){
            MainApplication.apiManager.getdamageAsset(token,"2",data.getId().toString(),"1", new Callback<GetDamageModel>() {
                @Override
                public void onResponse(Call<GetDamageModel> call, Response<GetDamageModel> response) {

                    final GetDamageModel responseUser = response.body();
                    if (response.isSuccessful() && responseUser != null) {

//                   Toast.makeText(getActivity(),"Successfull",Toast.LENGTH_LONG).show();
                        try {
                            progressDialog.dismiss();
                            showToast.showToast(c,"Successfully updated");
                            Intent intent=new Intent(c, Dashboard.class);
                            c.startActivity(intent);
                            c.finish();



                        }
                        catch (Exception e){
                            progressDialog.dismiss();
                        }
                    } else {
                        showToast.showToast(c,"Something went wrong");


                    }
                }
                @Override
                public void onFailure(Call<GetDamageModel> call, Throwable t) {
                    progressDialog.dismiss();
                    showToast.showToast(c,"Internal Error");

                }
            });

        }
        else {

            progressDialog.dismiss();
            showToast.showToast(c,"Network error");
        }
    }


}