package com.pitangent.assettracking.ui.damage;

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
import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.damage.GetDamageModel;
import com.pitangent.assettracking.model.scanning.AssetModel;
import com.pitangent.assettracking.ui.MultipleSelectionActivityDamage;
import com.pitangent.assettracking.utils.ConnectionCheck;
import com.pitangent.assettracking.utils.SharedPreferencesManager;
import com.pitangent.assettracking.utils.ShowSnakbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DamageActivity extends AppCompatActivity {
ConnectionCheck connectionCheck=new ConnectionCheck();
ShowSnakbar showSnakbar=new ShowSnakbar();
Toolbar mActionBarToolbar;
    EditText et_search;
    ArrayList uniqueList;
    RecyclerView recylerview_damage;
    private MenuItem itemToHide;
    private MenuItem itemToShow;
    ArrayList<AssetModel> arrayList=new ArrayList<>();
    ArrayList<AssetModel> arrayListtemp=new ArrayList<>();
    ArrayList<String> catList=new ArrayList<>();
    DamageAsstesAdapter damageAsstesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage);
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
        getSupportActionBar().setTitle("Damage Products");
        initView();
        getDamageAssteList();
    }
    private void initView() {

        recylerview_damage=findViewById(R.id.recylerview_damage);
        damageAsstesAdapter=new DamageAsstesAdapter(arrayList, DamageActivity.this);
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
       // damageAsstesAdapter.filterList(filterdNames);
        arrayListtemp=filterdNames;
        //calling a method of the adapter class and passing the filtered list
        //dashBoardAdapter.filterList(filterdNames);
        damageAsstesAdapter = new DamageAsstesAdapter(arrayList, DamageActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DamageActivity.this, LinearLayoutManager.VERTICAL, false);
        recylerview_damage.setLayoutManager(linearLayoutManager);
        // recylerview_damage.setItemAnimator(new DefaultItemAnimator());
        recylerview_damage.setAdapter(damageAsstesAdapter);
        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.header),
                        true,
                        getSectionCallback(filterdNames));
        recylerview_damage.addItemDecoration(sectionItemDecoration);
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
        damageAsstesAdapter = new DamageAsstesAdapter(arrayList, DamageActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DamageActivity.this, LinearLayoutManager.VERTICAL, false);
        recylerview_damage.setLayoutManager(linearLayoutManager);
        // recylerview_damage.setItemAnimator(new DefaultItemAnimator());
        recylerview_damage.setAdapter(damageAsstesAdapter);
        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.header),
                        true,
                        getSectionCallback(filterdNames));
        recylerview_damage.addItemDecoration(sectionItemDecoration);


    }
    private void getDamageAssteList() {
        String token= SharedPreferencesManager.getInstance().getToken();
        if(connectionCheck.ConnectionCheck(DamageActivity.this)) {
            MainApplication.apiManager.getdamageAssetDetails(token, new Callback<GetDamageModel>() {
                @Override
                public void onResponse(Call<GetDamageModel> call, Response<GetDamageModel> response) {

                    GetDamageModel responseUser = response.body();
                    if (response.isSuccessful() && responseUser != null) {

                        try {
                            arrayList=responseUser.getData();
                            arrayListtemp=responseUser.getData();
                             damageAsstesAdapter = new DamageAsstesAdapter(arrayList, DamageActivity.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DamageActivity.this, LinearLayoutManager.VERTICAL, false);
                            recylerview_damage.setLayoutManager(linearLayoutManager);
                           // recylerview_damage.setItemAnimator(new DefaultItemAnimator());
                            recylerview_damage.setAdapter(damageAsstesAdapter);
                            RecyclerSectionItemDecoration sectionItemDecoration =
                                    new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.header),
                                            true,
                                            getSectionCallback(arrayList));
                            recylerview_damage.addItemDecoration(sectionItemDecoration);


                        } catch (Exception e) {
                            showSnakbar.show(DamageActivity.this,"Something went Wrong");

                        }


                    } else {

                        showSnakbar.show(DamageActivity.this,"No data found");
                    }
                }

                @Override
                public void onFailure(Call<GetDamageModel> call, Throwable t) {

                    showSnakbar.show(DamageActivity.this,"Something went worng");
                }
            });
        }
        else {
            showSnakbar.show(DamageActivity.this,"Check internet connection");
        }

    }
    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final ArrayList<AssetModel> people) {
        return new RecyclerSectionItemDecoration.SectionCallback() {
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
        finish();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        itemToHide = menu.findItem(R.id.action_search);
        itemToShow = menu.findItem(R.id.action_close);
        return true;
    }
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
            Intent intent2=new Intent(DamageActivity.this, MultipleSelectionActivityDamage.class);
            startActivity(intent2);
            finish();

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
