package com.pitangent.assettracking.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.dashboard.EmployeeModel;
import com.pitangent.assettracking.scan.MainActivity;
import com.pitangent.assettracking.ui.details.Details;
import com.pitangent.assettracking.utils.AppData;
import com.pitangent.assettracking.utils.SharedPreferencesManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.MyViewHolder> {
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
        Picasso.get()
                .load(arrayList.get(position).getImage()==null?"https://ecfa.org.ng/wp-content/uploads/2017/06/jefe.png":"http://192.168.0.188/asset_management/assets/employeeprofile/"+arrayList.get(position).getImage())
                .placeholder(R.drawable.loading_spinner)
                .error(R.drawable.user)
                .into(holder.profileImage);
        holder.listing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.listing.setClickable(false);
//                context.startActivity(new Intent(context,EmployeeAssetActivity.class));
                Intent intent=new Intent(context, Details.class);

                intent.putExtra("emp_id", String.valueOf(arrayList.get(position).getId()));
                intent.putExtra("emp_name", String.valueOf(arrayList.get(position).getEmployee_name()));
                context.startActivity(intent);



            }
        });
        holder.qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.qr_code.setClickable(false);
                AppData.empid=String.valueOf(arrayList.get(position).getId());
                SharedPreferencesManager.getInstance().setAssignType("Assign");
                context.startActivity(new Intent(context, MainActivity.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView employee_name,designation;
        public  ImageView qr_code,listing;
        public CircleImageView profileImage;
        public MyViewHolder(View itemView) {
            super(itemView);
            employee_name=itemView.findViewById(R.id.employee_name);
            designation=itemView.findViewById(R.id.designation);
            qr_code=itemView.findViewById(R.id.qr_code);
            listing=itemView.findViewById(R.id.listing);
            profileImage=itemView.findViewById(R.id.profileImage);
            listing.setOnClickListener(this);
            qr_code.setOnClickListener(this);
            listing.setClickable(true);
            qr_code.setClickable(true);

        }

        @Override
        public void onClick(View view) {

        }
    }



}
