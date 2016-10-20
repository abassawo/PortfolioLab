package com.example.c4q_abass.abassportfoliolab;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by c4q-Abass on 5/6/16.
 */
public class AppModel implements Serializable{

    private final String TAG = "AppModel";

    private String id;
    private String name;
    private String description;
    private String category;
    private String avatarUrl;

    public AppModel(JSONObject json) {
        if (json != null) {
            try {
                id = json.getString("_id");
                name = json.getString("name");
                description = json.getString("description");
                category = json.getString("category");
                avatarUrl = json.getString("avatar_url");
            } catch (JSONException e) {
                Log.w(TAG, e);
            }
        }
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }
}
