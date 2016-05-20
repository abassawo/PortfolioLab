package com.example.c4q_abass.abassportfoliolab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by c4q-Abass on 5/6/16.
 */
public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppHolder> {
    private static final String TAG = "AppAdapter" ;
    private Context context;
    private AppSet apps;

    public AppAdapter(Context context, AppSet apps) {
        this.context = context;
        this.apps = apps;
    }

    public void setApps(AppSet apps){
        this.apps = apps;
    }

    @Override
    public AppHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_list_item, parent, false);
        return new AppHolder(view);
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    @Override
    public void onBindViewHolder(AppHolder holder, int position) {
        AppModel app = apps.get(position);
        holder.bindApp(app);
    }

    public class AppHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private AppModel app;
        private ImageView imageView;
        private TextView titleField;

        public AppHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.app_logo_iv);
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.placeholder));
            titleField = (TextView) itemView.findViewById(R.id.app_title_tv);
            itemView.setOnClickListener(this);
        }

        public void bindApp(AppModel app){
            this.app = app;
            titleField.setText(app.getName());
            if(app.getAvatarUrl() != null) {
                bindThumbNail();
            }
        }

        private void bindThumbNail() {
            Log.d(TAG, "trying to load avatar from url " + app.getAvatarUrl());
            if(app.getAvatarUrl() != null) {
                Glide.with(context).load(app.getAvatarUrl()).centerCrop().into(imageView);
            }else
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.placeholder));
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, app.getName(), Toast.LENGTH_SHORT).show();
        }
    }
}
