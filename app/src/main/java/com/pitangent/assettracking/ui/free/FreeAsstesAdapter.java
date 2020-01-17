package com.pitangent.assettracking.ui.free;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pitangent.assettracking.R;
import com.pitangent.assettracking.model.scanning.AssetModel;

import java.util.ArrayList;


public class FreeAsstesAdapter extends RecyclerView.Adapter<FreeAsstesAdapter.MyViewHolder> {
    ArrayList<AssetModel> arrayList=new ArrayList<>();


    Context context;
    public ArrayList<MyViewHolder> cellViews=null;
    MyViewHolder ownAdapter;
    public FreeAsstesAdapter(ArrayList<AssetModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        cellViews = new ArrayList<>();
        notifyDataSetChanged();
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_asstes_list_new,parent,false);
        ownAdapter=new MyViewHolder(view);
        return ownAdapter;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder. Office_Asset_ID.setText("Office id:"+arrayList.get(position).getOffice_asset_id());
        holder. assets_name.setText(""+arrayList.get(position).getCategory_name());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Office_Asset_ID,assets_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            Office_Asset_ID=itemView.findViewById(R.id.Office_Asset_ID);
            assets_name=itemView.findViewById(R.id.assets_name);
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
