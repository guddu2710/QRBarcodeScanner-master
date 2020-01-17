package com.pitangent.assettracking.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.pitangent.assettracking.AppData;
import com.pitangent.assettracking.R;
import com.pitangent.assettracking.ui.damage.DamageActivity;
import com.pitangent.assettracking.ui.free.FreeAsstes;

import java.util.ArrayList;

public class MultipleSelectionActivityDamage extends AppCompatActivity {
    private android.support.v7.widget.RecyclerView recyclerView;
    private ArrayList<Category> employees = new ArrayList<>();
    private MultiAdapter adapter;
    private android.support.v7.widget.AppCompatButton btnGetSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_selection_damage);
        this.btnGetSelected = (AppCompatButton) findViewById(R.id.btnGetSelected);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new MultiAdapter(this, employees);
        recyclerView.setAdapter(adapter);
        createList();
        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppData.selectcatList.clear();
                if (adapter.getSelected().size() > 0) {

                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < adapter.getSelected().size(); i++) {
                        stringBuilder.append(adapter.getSelected().get(i).getName());
                        stringBuilder.append("\n");
                        AppData.selectcatList.add(adapter.getSelected().get(i).getName());
                    }
                   // showToast(stringBuilder.toString().trim());
                    Intent intent2=new Intent(MultipleSelectionActivityDamage.this, DamageActivity.class);
                    startActivity(intent2);
                    finish();
                } else {
                    Intent intent2=new Intent(MultipleSelectionActivityDamage.this, DamageActivity.class);
                    startActivity(intent2);
                    finish();
                   // showToast("No Selection");
                }
            }
        });
    }

    private void createList() {
        employees = new ArrayList<>();
        for (int i = 0; i < AppData.uniquecatList.size(); i++) {
            Category employee = new Category();
            employee.setName(AppData.uniquecatList.get(i));
            for(int j=0;j<AppData.selectcatList.size();j++){
                if (AppData.selectcatList.get(j).toLowerCase().equalsIgnoreCase(AppData.uniquecatList.get(i))) {
                    employee.setChecked(true);
                }
            }
            // for example to show at least one selection

            //
            employees.add(employee);
        }
        adapter.setEmployees(employees);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed()
    {
        Intent intent2=new Intent(MultipleSelectionActivityDamage.this, FreeAsstes.class);
        startActivity(intent2);
        finish();
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}
