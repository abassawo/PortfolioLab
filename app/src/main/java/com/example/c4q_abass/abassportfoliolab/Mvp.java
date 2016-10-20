package com.example.c4q_abass.abassportfoliolab;

import com.example.c4q_abass.abassportfoliolab.model.AppModel;

/**
 * Created by C4Q on 10/19/16.
 */

public class  Mvp {

    public interface Model{

    }

    public interface View{
        boolean isAppInstalled(AppModel app);
    }

    public interface Presenter{
        void launchApplication(AppModel appModel);
        void doGooglePlayQuery(AppModel appModel);
    }
}
