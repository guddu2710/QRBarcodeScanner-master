package com.pitangent.assettracking.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.pitangent.assettracking.Api.MainApplication;
import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.detailspage.GetEmployeeAssetModel;
import com.pitangent.assettracking.utils.ConnectionCheck;
import com.pitangent.assettracking.utils.SharedPreferencesManager;
import com.pitangent.assettracking.utils.ShowSnakbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    RelativeLayout llvisible;
    RelativeLayout lldata;
    ConnectionCheck connectionCheck=new ConnectionCheck();
    ShowSnakbar showSnakbar=new ShowSnakbar();
    RecyclerView rv_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        mActionBarToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mActionBarToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String emp_id = intent.getStringExtra("emp_id");
        String emp_name = intent.getStringExtra("emp_name");
        getSupportActionBar().setTitle(""+emp_name);
        initView();
        getData(emp_name,emp_id);

    }

    private void getData(final String empname, String emp_id) {
        if(connectionCheck.ConnectionCheck(Details.this)){
            String token= SharedPreferencesManager.getInstance().getToken();
            MainApplication.apiManager.getemployeeAssetDetails(token,emp_id, new Callback<GetEmployeeAssetModel>() {
                @Override
                public void onResponse(Call<GetEmployeeAssetModel> call, Response<GetEmployeeAssetModel> response) {
                    GetEmployeeAssetModel responseUser=response.body();
                    if(response.isSuccessful()&&responseUser!=null)
                    {
                        try {
                            lldata.setVisibility(View.VISIBLE);
                            llvisible.setVisibility(View.GONE);
                           // AssetModel assetModel=responseUser.getData().get(0);
                            DetailsAdapter dashBoardAdapter = new DetailsAdapter(responseUser.getData(), Details.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Details.this, LinearLayoutManager.VERTICAL, false);
                            rv_details.setLayoutManager(linearLayoutManager);
                            rv_details.setItemAnimator(new DefaultItemAnimator());
                            rv_details.setAdapter(dashBoardAdapter);



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        lldata.setVisibility(View.GONE);
                        llvisible.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onFailure(Call<GetEmployeeAssetModel> call, Throwable t) {
                    showSnakbar.show(Details.this,"Somthing went wrong");
                }
            });

        }
        else {
            showSnakbar.show(Details.this,"Check internet connection");
        }
    }

    private void initView() {
        lldata=findViewById(R.id.lldata);
        llvisible=findViewById(R.id.llvisible);
        rv_details=findViewById(R.id.rv_details);



    }

}
