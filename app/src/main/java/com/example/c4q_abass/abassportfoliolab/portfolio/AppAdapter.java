package com.example.c4q_abass.abassportfoliolab.portfolio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q_abass.abassportfoliolab.model.AppCollection;
import com.example.c4q_abass.abassportfoliolab.model.AppModel;
import com.example.c4q_abass.abassportfoliolab.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q-Abass on 5/6/16.
 */
public class AppAdapter extends RecyclerView.Adapter<AppHolder> {
    private static final String TAG = "AppAdapter";
    private Context context;
    private List<AppModel> apps;

    public AppAdapter(Context context) {
        this.context = context;
        apps = new ArrayList<>();
    }

    public AppAdapter(Context context, AppCollection appSet) {
        this.context = context;
        apps = appSet.getApps();
    }

    public void setApps(AppCollection apps) {
        this.apps = apps.getApps();
    }

    @Override
    public AppHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_list_item, parent, false);
        return new AppHolder(view);
    }

    @Override
    public int getItemCount() {
        if(apps == null) return 0;
        return apps.size();
    }

    @Override
    public void onBindViewHolder(AppHolder holder, int position) {
        AppModel app = apps.get(position);
        Log.d(TAG, "binding element at idx " + position);
        holder.bindApp(app);
    }


}
