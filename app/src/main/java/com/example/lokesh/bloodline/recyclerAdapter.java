package com.example.lokesh.bloodline;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {

    private List<recycle_viewlist> recycle_viewlists;
    private Context context;

    public recyclerAdapter( Context context,List<recycle_viewlist> recycle_viewlists) {
        this.recycle_viewlists = recycle_viewlists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.list_recycle_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        recycle_viewlist recycle_viewlist = recycle_viewlists.get(position);

        holder.rview_name.setText(recycle_viewlist.getName());
        holder.rview_phone.setText(recycle_viewlist.getPhone());
        holder.rview_bloodgroup.setText(recycle_viewlist.getBlood_group());
        holder.rview_city.setText(recycle_viewlist.getCity());

    }

    @Override
    public int getItemCount() {
        return recycle_viewlists.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{

         TextView rview_name;
         TextView rview_phone;
         TextView rview_bloodgroup;
         TextView rview_city;

        public ViewHolder(View itemView) {
            super(itemView);

            rview_name = (TextView) itemView.findViewById(R.id.rview_name);
            rview_phone = (TextView) itemView.findViewById(R.id.rview_phone);
            rview_bloodgroup = (TextView) itemView.findViewById(R.id.rview_bloodgroup);
            rview_city = (TextView) itemView.findViewById(R.id.rview_city);


        }
    }
}
