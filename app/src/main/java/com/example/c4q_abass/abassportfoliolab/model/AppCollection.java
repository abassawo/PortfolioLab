package com.example.c4q_abass.abassportfoliolab.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by c4q-Abass on 5/19/16.
 */
public class AppCollection implements Serializable{
    private List<AppModel> apps;
    private String categoryName;

    public List<AppModel> getApps() {
        return apps;
    }

    public AppCollection(List<AppModel> apps){
        this.categoryName = apps.iterator().next().getCategory();
        this.apps = apps;
    }


    public AppCollection(String categoryName){
        this.categoryName = categoryName;
        this.apps = new ArrayList<>();
    }


    public void add(AppModel appModel){
        this.apps.add(appModel);
    }

    public AppModel get(int position){
       AppModel appModel = null;
       for(int i = 0; i < position; i++){
           appModel = apps.iterator().next();
       }
       return appModel;
    }

    public int size(){
        if(apps == null) return 0;
        return apps.size();
    }

    public void addAll(Collection<AppModel> appModels){
        this.apps.addAll(appModels);
    }


    private void remove(AppModel app){
        this.apps.remove(app);
    }
}
