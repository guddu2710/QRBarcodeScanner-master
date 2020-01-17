package com.pitangent.project.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pitangent.project.R;
import com.pitangent.project.model.dashboard.EmployeeModel;

import java.util.ArrayList;


public class DashBoardAdapter extends RecyclerView.Adapter<com.pitangent.project.ui.dashboard.DashBoardAdapter.MyViewHolder> {
    ArrayList<EmployeeModel> arrayList=new ArrayList<>();
    Context context;
    public ArrayList<MyViewHolder> cellViews=null;
    MyViewHolder ownAdapter;




    public DashBoardAdapter(ArrayList<EmployeeModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        cellViews = new ArrayList<>();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_emplyees,parent,false);
        ownAdapter=new MyViewHolder(view);
        return ownAdapter;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.employee_name.setText(arrayList.get(position).getEmployee_name());
        holder.designation.setText(arrayList.get(position).getEmployee_designation());
        holder.listing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context.startActivity(new Intent(context,EmployeeAssetActivity.class));
                Intent intent=new Intent(context, com.pitangent.project.ui.details.Details.class);
                intent.putExtra("id", String.valueOf(arrayList.get(position).getId()));
                intent.putExtra("employee_name", String.valueOf(arrayList.get(position).getEmployee_name()));
                context.startActivity(intent);


            }
        });
        holder.qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.pitangent.project.utils.SharedPreferencesManager.getInstance().setAssignType("Assign");
                context.startActivity(new Intent(context, com.pitangent.project.scan.ScanningActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView employee_name,designation;
        ImageView qr_code,listing;
        public MyViewHolder(View itemView) {
            super(itemView);
            employee_name=itemView.findViewById(R.id.employee_name);
            designation=itemView.findViewById(R.id.designation);
            qr_code=itemView.findViewById(R.id.qr_code);
            listing=itemView.findViewById(R.id.listing);
            listing.setOnClickListener(this);
            qr_code.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }



}
