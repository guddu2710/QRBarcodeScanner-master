package com.pitangent.assettracking.ui.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.scanning.AssetModel;

import java.util.ArrayList;


public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.MyViewHolder> {
    ArrayList<AssetModel> arrayList=new ArrayList<>();
    Context context;
    public ArrayList<MyViewHolder> cellViews=null;
    MyViewHolder ownAdapter;




    public DetailsAdapter(ArrayList<AssetModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        cellViews = new ArrayList<>();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_asstes,parent,false);
        ownAdapter=new MyViewHolder(view);
        return ownAdapter;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.assets_name.setText(arrayList.get(position).getAsset_name());
        holder.assets_id.setText("Office id:"+arrayList.get(position).getOffice_asset_id());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView assets_name,assets_id;
        public MyViewHolder(View itemView) {
            super(itemView);
            assets_name=itemView.findViewById(R.id.assets_name);
            assets_id=itemView.findViewById(R.id.assets_id);
        }

        @Override
        public void onClick(View view) {

        }
    }



}
