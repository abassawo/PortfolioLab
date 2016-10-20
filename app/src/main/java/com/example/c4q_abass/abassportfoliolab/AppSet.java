package com.example.c4q_abass.abassportfoliolab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by c4q-Abass on 5/19/16.
 */
public class AppSet implements Serializable{
    private List<AppModel> apps;
    private String categoryName;

    public AppSet(){
        this.categoryName = "Default";
        this.apps = new ArrayList<>();
    }

    public List<AppModel> getApps() {
        return apps;
    }

    public AppSet(List<AppModel> apps){
        this.categoryName = apps.iterator().next().getCategory();
        this.apps = apps;
    }

    public Set<String> getCategories(){
        Set<String> categories = new HashSet<>();

        for(AppModel app : apps){
            categories.add(app.getDescription());
        }
        return categories;
    }

    public AppSet(String categoryName){
        this.categoryName = categoryName;
        this.apps = new ArrayList<>();
    }


    public AppSet(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            //Verify that we're filtering
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                    AppModel app = new AppModel(jsonObject);
                    this.add(app);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
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
