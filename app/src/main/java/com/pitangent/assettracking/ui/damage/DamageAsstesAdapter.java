package com.pitangent.assettracking.ui.damage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.scanning.AssetModel;

import java.util.ArrayList;


public class DamageAsstesAdapter extends RecyclerView.Adapter<DamageAsstesAdapter.MyViewHolder> {
    ArrayList<AssetModel> arrayList=new ArrayList<>();
    Context context;
    public ArrayList<MyViewHolder> cellViews=null;
    MyViewHolder ownAdapter;




    public DamageAsstesAdapter(ArrayList<AssetModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        cellViews = new ArrayList<>();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_asstes_list,parent,false);
        ownAdapter=new MyViewHolder(view);
        return ownAdapter;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder. assets_manufacturer.setText(arrayList.get(position).getAsset_manufacturer());
        holder. assets_id.setText("Office id:"+arrayList.get(position).getOffice_asset_id());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView assets_manufacturer,assets_id;
        public MyViewHolder(View itemView) {
            super(itemView);
            assets_manufacturer=itemView.findViewById(R.id.assets_manufacturer);
            assets_id=itemView.findViewById(R.id.assets_id);


        }

        @Override
        public void onClick(View view) {

        }
    }

    public void filterList(ArrayList<AssetModel> filterdNames) {
        this.arrayList = filterdNames;
        notifyDataSetChanged();
    }


}
