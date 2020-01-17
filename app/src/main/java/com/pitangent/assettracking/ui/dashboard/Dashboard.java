package com.pitangent.assettracking.ui.dashboard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pitangent.assettracking.Api.MainApplication;
import com.pitangent.assettracking.AppData;
import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.dashboard.EmployeeListModel;
import com.pitangent.assettracking.scan.MainActivity;
import com.pitangent.assettracking.ui.damage.DamageActivity;
import com.pitangent.assettracking.ui.free.FreeAsstes;
import com.pitangent.assettracking.ui.login.LoginActivity;
import com.pitangent.assettracking.utils.ConnectionCheck;
import com.pitangent.assettracking.utils.SharedPreferencesManager;
import com.pitangent.assettracking.utils.ShowSnakbar;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Dashboard extends AppCompatActivity implements View.OnClickListener {
String token;
Toolbar mActionBarToolbar;
ConnectionCheck connectionCheck=new ConnectionCheck();
RecyclerView recylerview_dash;
ShowSnakbar showSnakbar=new ShowSnakbar();
private SwipeRefreshLayout pullToRefresh;
    ImageView scanFree,scanDamage,listFree,listDamage;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Employee List");
        mActionBarToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        pullToRefresh = findViewById(R.id.pullToRefresh);
        progressDialog=new ProgressDialog(this);
        progressDialog.show();
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getEmployeeList(); // your code

            }
        });
        initView();
        getEmployeeList();
        
    }

    private void initView() {
        recylerview_dash=findViewById(R.id.recylerview_dash);
        LinearLayout llBottomSheet = (LinearLayout) findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
         scanFree = (ImageView) llBottomSheet.findViewById(R.id.iv_scan_free);
         scanDamage = (ImageView) llBottomSheet.findViewById(R.id.iv_scan_damage);
         listFree = (ImageView) llBottomSheet.findViewById(R.id.list_free);
         listDamage = (ImageView) llBottomSheet.findViewById(R.id.list_damage);
        scanFree.setOnClickListener(this);
        scanDamage.setOnClickListener(this);
        listFree.setOnClickListener(this);
        listDamage.setOnClickListener(this);
        scanFree.setClickable(true);
        scanDamage.setClickable(true);
        listFree.setClickable(true);
        listDamage.setClickable(true);

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
                if (newState == bottomSheetBehavior.STATE_HIDDEN) {

                    bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
              //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });


    }


    private void getEmployeeList() {
        token= SharedPreferencesManager.getInstance().getToken();
        Log.d("token",token);

        if(connectionCheck.ConnectionCheck(Dashboard.this)) {
            MainApplication.apiManager.getEmployeeUser(token, new Callback<EmployeeListModel>() {
                @Override
                public void onResponse(Call<EmployeeListModel> call, Response<EmployeeListModel> response) {

                    EmployeeListModel responseUser = response.body();
                    if (response.isSuccessful() && responseUser != null) {

                        try {
                            DashBoardAdapter dashBoardAdapter = new DashBoardAdapter(responseUser.getData(), Dashboard.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Dashboard.this, LinearLayoutManager.VERTICAL, false);
                            recylerview_dash.setLayoutManager(linearLayoutManager);
                            recylerview_dash.setItemAnimator(new DefaultItemAnimator());
                            recylerview_dash.setAdapter(dashBoardAdapter);
                            progressDialog.dismiss();
                            pullToRefresh.setRefreshing(false);


                        } catch (Exception e) {
                            pullToRefresh.setRefreshing(false);
                            progressDialog.dismiss();
                            showSnakbar.show(Dashboard.this,"Something went wrong");

                        }


                    } else {

                        pullToRefresh.setRefreshing(false);
                        showSnakbar.show(Dashboard.this,"Data not found");
                    }
                }

                @Override
                public void onFailure(Call<EmployeeListModel> call, Throwable t) {
                    pullToRefresh.setRefreshing(false);
                    showSnakbar.show(Dashboard.this,"Something went wrong");
                }
            });
        }
        else {
            pullToRefresh.setRefreshing(false);
            showSnakbar.show(Dashboard.this,"Check internet connection");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.list_free:
                AppData.uniquecatList.clear();
                AppData.selectcatList.clear();
                listFree.setClickable(false);
                Intent intent2=new Intent(Dashboard.this, FreeAsstes.class);
                startActivity(intent2);
               // finish();
                break;
            case R.id.iv_scan_free:
                scanFree.setClickable(false);
                SharedPreferencesManager.getInstance().setAssignType("free");
                Intent intent=new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent);
               // finish();
                break;
            case R.id.list_damage:
                listDamage.setClickable(false);
                Intent intent3=new Intent(Dashboard.this, DamageActivity.class);
                startActivity(intent3);
               // finish();
                break;
            case R.id.iv_scan_damage:
                scanDamage.setClickable(false);
                SharedPreferencesManager.getInstance().setAssignType("damage");
                Intent intent1=new Intent(Dashboard.this, MainActivity.class);
                startActivity(intent1);
              //  finish();
                break;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
           SharedPreferencesManager.getInstance().setLogged("notlogged");
           Intent intent=new Intent(Dashboard.this, LoginActivity.class);
           startActivity(intent);
           finish();
        }

        return super.onOptionsItemSelected(item);
    }
    boolean isSecond;

//    @Override
//    public void onBackPressed(){
//        if (isSecond) {
//            ShowToast showToast=new ShowToast();
//            showToast.showToast(Dashboard.this,"press double to exit");
//        }
//
//        isSecond = true;
//        new Handler() . postDelayed(new Runnable() {
//            @Override
//            public void run(){
//                isSecond = false;
//            }
//        }, 2000);
//    }

    @Override
    protected void onResume() {
        super.onResume();
        scanFree.setClickable(true);
        scanDamage.setClickable(true);
        listFree.setClickable(true);
        listDamage.setClickable(true);
        getEmployeeList();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        scanFree.setClickable(true);
        scanDamage.setClickable(true);
        listFree.setClickable(true);
        listDamage.setClickable(true);
        getEmployeeList();
    }
}
