package com.example.c4q_abass.abassportfoliolab;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by c4q-Abass on 5/19/16.
 */
public class AppSet implements Serializable{
    private Set<AppModel> apps;
    private String categoryName;

    public AppSet(String categoryName){
        this.categoryName = categoryName;
        this.apps = new HashSet<>();
    }

    public AppSet(){
        this.apps = new HashSet<>();
    }

    public void setCategoryName(String name){
        this.categoryName = name;
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
        return apps.size();
    }

    public void addAll(Collection<AppModel> appModels){
        this.apps.addAll(appModels);
    }



    private void remove(AppModel app){
        this.apps.remove(app);
    }
}
