package com.pitangent.assettracking.ui.free;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.pitangent.assettracking.Api.MainApplication;
import com.pitangent.assettracking.AppData;
import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.free.GetFreeAssetModel;
import com.pitangent.assettracking.model.scanning.AssetModel;
import com.pitangent.assettracking.ui.MultipleSelectionActivity;
import com.pitangent.assettracking.ui.dashboard.Dashboard;
import com.pitangent.assettracking.utils.ConnectionCheck;
import com.pitangent.assettracking.utils.SharedPreferencesManager;
import com.pitangent.assettracking.utils.ShowSnakbar;

import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeAsstes extends AppCompatActivity {
ConnectionCheck connectionCheck=new ConnectionCheck();
RecyclerView recylerview_free;
ShowSnakbar showSnakbar=new ShowSnakbar();
EditText et_search;
    ArrayList uniqueList;

    private MenuItem itemToHide;
    private MenuItem itemToShow;
    ArrayList<AssetModel> arrayList=new ArrayList<>();
    ArrayList<AssetModel> arrayListtemp=new ArrayList<>();
    ArrayList<String> catList=new ArrayList<>();


    FreeAsstesAdapter dashBoardAdapter;
    Toolbar mActionBarToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_asstes);
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
        getSupportActionBar().setTitle("Free product");
        initView();
        getFreeAssteList();
    }

    private void initView() {
        recylerview_free=findViewById(R.id.recylerview_free);
        dashBoardAdapter=new FreeAsstesAdapter(arrayList, FreeAsstes.this);
        et_search=findViewById(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
    }
    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<AssetModel> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (AssetModel s : arrayListtemp) {
            //if the existing elements contains the search input
            if (s.getOffice_asset_id().toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        //dashBoardAdapter.filterList(filterdNames);
        arrayListtemp=filterdNames;
        //calling a method of the adapter class and passing the filtered list
        //dashBoardAdapter.filterList(filterdNames);
        dashBoardAdapter = new FreeAsstesAdapter(filterdNames, FreeAsstes.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FreeAsstes.this, LinearLayoutManager.VERTICAL, false);
        recylerview_free.setLayoutManager(linearLayoutManager);
        // recylerview_free.setItemAnimator(new DefaultItemAnimator());
        recylerview_free.setAdapter(dashBoardAdapter);
        RecyclerSectionItemDecorationfree sectionItemDecoration =
                new RecyclerSectionItemDecorationfree(getResources().getDimensionPixelSize(R.dimen.header),
                        true,
                        getSectionCallback(filterdNames));
        recylerview_free.addItemDecoration(sectionItemDecoration);

    }
    private void filterByCatregory(ArrayList<String> selectcatList) {
        //new array list that will hold the filtered data
        ArrayList<AssetModel> filterdNames = new ArrayList<>();
        //looping through existing elements
        for(int i=0;i<selectcatList.size();i++){
            for (AssetModel s : arrayListtemp) {
                //if the existing elements contains the search input
                if (s.getCategory_name().toLowerCase().contains(selectcatList.get(i).toLowerCase())) {
                    //adding the element to filtered list
                    filterdNames.add(s);
                }
            }
        }
        arrayListtemp=filterdNames;
        //calling a method of the adapter class and passing the filtered list
        //dashBoardAdapter.filterList(filterdNames);
        dashBoardAdapter = new FreeAsstesAdapter(filterdNames, FreeAsstes.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FreeAsstes.this, LinearLayoutManager.VERTICAL, false);
        recylerview_free.setLayoutManager(linearLayoutManager);
        // recylerview_free.setItemAnimator(new DefaultItemAnimator());
        recylerview_free.setAdapter(dashBoardAdapter);
        RecyclerSectionItemDecorationfree sectionItemDecoration =
                new RecyclerSectionItemDecorationfree(getResources().getDimensionPixelSize(R.dimen.header),
                        true,
                        getSectionCallback(filterdNames));
        recylerview_free.addItemDecoration(sectionItemDecoration);

    }

    private void getFreeAssteList() {
        String token= SharedPreferencesManager.getInstance().getToken();
        if(connectionCheck.ConnectionCheck(FreeAsstes.this)) {
            MainApplication.apiManager.getfreeAssetDetails(token, new Callback<GetFreeAssetModel>() {
                @Override
                public void onResponse(Call<GetFreeAssetModel> call, Response<GetFreeAssetModel> response) {

                    GetFreeAssetModel responseUser = response.body();
                    if (response.isSuccessful() && responseUser != null) {

                        try {
                            arrayList=responseUser.getData();
                            arrayListtemp=responseUser.getData();
                            dashBoardAdapter = new FreeAsstesAdapter(arrayList, FreeAsstes.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FreeAsstes.this, LinearLayoutManager.VERTICAL, false);
                            recylerview_free.setLayoutManager(linearLayoutManager);
                           // recylerview_free.setItemAnimator(new DefaultItemAnimator());
                            recylerview_free.setAdapter(dashBoardAdapter);
                            for (AssetModel s : arrayListtemp) {
                                //if the existing elements contains the search input
                                    //adding the element to filtered list
                                    catList.add(s.getCategory_name());

                            }
                            uniqueList = (ArrayList) catList.stream().distinct().collect(Collectors.toList());
                            AppData.uniquecatList=uniqueList;
                            if(AppData.selectcatList.size()>0){
                                filterByCatregory(AppData.selectcatList);
                            }
                            else {
                                RecyclerSectionItemDecorationfree sectionItemDecoration =
                                        new RecyclerSectionItemDecorationfree(getResources().getDimensionPixelSize(R.dimen.header),
                                                true,
                                                getSectionCallback(arrayListtemp));
                                recylerview_free.addItemDecoration(sectionItemDecoration);
                            }

                        } catch (Exception e) {
                            showSnakbar.show(FreeAsstes.this,"Something went wrong");
                        }


                    } else {
                        showSnakbar.show(FreeAsstes.this,"No data found");


                    }
                }

                @Override
                public void onFailure(Call<GetFreeAssetModel> call, Throwable t) {


                }
            });
        }
        else {
            showSnakbar.show(FreeAsstes.this,"Check internet connection");
        }

    }
    private RecyclerSectionItemDecorationfree.SectionCallback getSectionCallback(final ArrayList<AssetModel> people) {
        return new RecyclerSectionItemDecorationfree.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0
                        || people.get(position).getCategory_name()
                        .charAt(0) != people.get(position - 1)
                        .getCategory_name().toString()
                        .charAt(0);
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return people.get(position).getCategory_name()
                        .subSequence(0,
                                people.get(position).getCategory_name().length());
            }
        };
    }

    @Override
    public void onBackPressed() {
        Intent intent2=new Intent(FreeAsstes.this, Dashboard.class);
        startActivity(intent2);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        itemToHide = menu.findItem(R.id.action_search);
        itemToShow = menu.findItem(R.id.action_close);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            itemToHide.setVisible(false);
            // show the menu item
            itemToShow.setVisible(true);
            et_search.setVisibility(View.VISIBLE);
        }
        if (id == R.id.action_filter) {
            arrayListtemp=arrayList;
            Intent intent2=new Intent(FreeAsstes.this, MultipleSelectionActivity.class);
            startActivity(intent2);
//            finish();

        }
        if (id == R.id.action_close) {
            itemToHide.setVisible(true);
            // show the menu item
            itemToShow.setVisible(false);
            et_search.setVisibility(View.GONE);

        }

        return super.onOptionsItemSelected(item);
    }
}
