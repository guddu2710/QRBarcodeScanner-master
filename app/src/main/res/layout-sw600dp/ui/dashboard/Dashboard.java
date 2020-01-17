package com.pitangent.project.ui.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.pitangent.project.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
String token;
com.pitangent.project.utils.ConnectionCheck connectionCheck=new com.pitangent.project.utils.ConnectionCheck();
RecyclerView recylerview_dash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
        getEmployeeList();
        
    }

    private void initView() {
        recylerview_dash=findViewById(R.id.recylerview_dash);
        LinearLayout llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);

// change the state of the bottom sheet
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
      //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        // set the peek height
        bottomSheetBehavior.setPeekHeight(100);

        // set hideable or not
      //  bottomSheetBehavior.setHideable(false);

        // set callback for changes
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }


    private void getEmployeeList() {
        token= com.pitangent.project.utils.SharedPreferencesManager.getInstance().getToken();
        if(connectionCheck.ConnectionCheck(com.pitangent.project.ui.dashboard.Dashboard.this)) {
            com.pitangent.project.Api.MainApplication.apiManager.getEmployeeUser(token, new Callback<com.pitangent.project.model.dashboard.EmployeeListModel>() {
                @Override
                public void onResponse(Call<com.pitangent.project.model.dashboard.EmployeeListModel> call, Response<com.pitangent.project.model.dashboard.EmployeeListModel> response) {

                    com.pitangent.project.model.dashboard.EmployeeListModel responseUser = response.body();
                    if (response.isSuccessful() && responseUser != null) {

                        try {
                            com.pitangent.project.ui.dashboard.DashBoardAdapter dashBoardAdapter = new com.pitangent.project.ui.dashboard.DashBoardAdapter(responseUser.getData(), com.pitangent.project.ui.dashboard.Dashboard.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(com.pitangent.project.ui.dashboard.Dashboard.this, LinearLayoutManager.VERTICAL, false);
                            recylerview_dash.setLayoutManager(linearLayoutManager);
                            recylerview_dash.setItemAnimator(new DefaultItemAnimator());
                            recylerview_dash.setAdapter(dashBoardAdapter);


                        } catch (Exception e) {

                        }


                    } else {


                    }
                }

                @Override
                public void onFailure(Call<com.pitangent.project.model.dashboard.EmployeeListModel> call, Throwable t) {


                }
            });
        }

    }
}
