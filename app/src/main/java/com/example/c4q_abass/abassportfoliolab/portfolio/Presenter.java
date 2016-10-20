package com.example.c4q_abass.abassportfoliolab.portfolio;

import android.content.Context;
import android.widget.Toast;

import com.example.c4q_abass.abassportfoliolab.Mvp;
import com.example.c4q_abass.abassportfoliolab.model.AppModel;

/**
 * Created by C4Q on 10/19/16.
 */

public class Presenter implements Mvp.Presenter {

    private Context context;

    public Presenter(Context context){
        this.context = context;
    }

    @Override
    public void launchApplication(AppModel appModel) {
        Toast.makeText(context, appModel.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void doGooglePlayQuery(AppModel appModel) {
        Toast.makeText(context, "Goog play " + appModel.getName(), Toast.LENGTH_SHORT).show();
    }
}
