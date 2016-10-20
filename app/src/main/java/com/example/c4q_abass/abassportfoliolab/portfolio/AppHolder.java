package com.example.c4q_abass.abassportfoliolab.portfolio;

/**
 * Created by C4Q on 10/19/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.c4q_abass.abassportfoliolab.Mvp;
import com.example.c4q_abass.abassportfoliolab.R;
import com.example.c4q_abass.abassportfoliolab.model.AppModel;

/**
 * Created by C4Q on 10/19/16.
 */

public class AppHolder extends RecyclerView.ViewHolder implements Mvp.View, View.OnClickListener {
    private ImageView imageView;
    private TextView titleField;
    private Context context;
    private static String TAG = "AppHolder";
    private AppModel app;
    private Mvp.Presenter presenter;

    public AppHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        context = itemView.getContext();
        imageView = (ImageView) itemView.findViewById(R.id.app_logo_iv);
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.placeholder));
        titleField = (TextView) itemView.findViewById(R.id.app_title_tv);
        itemView.setOnClickListener(this);
        presenter = new Presenter(context);
    }

    public void bindApp(AppModel app) {
        if (app == null) return;
        this.app = app;
        if (app.getName() != null)
            titleField.setText(app.getName());
        if (app.getAvatarUrl() != null) {
            bindThumbNail(app);
        }

    }

    private void bindThumbNail(AppModel app) {
        Log.d(TAG, "trying to load avatar from url " + app.getAvatarUrl());
        if (app.getAvatarUrl() != null) {
            Glide.with(context).load(app.getAvatarUrl()).centerCrop().into(imageView);
        } else
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.placeholder));
    }

    @Override
    public void onClick(View v) {
        if(isAppInstalled(app)) {
            presenter.launchApplication(app);
        }else{
            presenter.doGooglePlayQuery(app);
        }
    }

    @Override
    public boolean isAppInstalled(AppModel app) {
        return false;
    }
}