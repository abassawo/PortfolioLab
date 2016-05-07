package com.example.c4q_abass.abassportfoliolab;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PortfolioActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<AppModel> appList = new ArrayList<AppModel>();
    private AppAdapter appAdapter = new AppAdapter(this, appList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        initViews();
        setupRV(recyclerView);
        new AsyncAppLoader().execute();
    }

    public void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.portfolio_recycler_view);
    }

    public void setupRV(RecyclerView rv) {
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(appAdapter);
    }

    private class AsyncAppLoader extends AsyncTask<Void, Void, List<AppModel>> {

        private static final String TAG = "AppLoader";

        @Override
        protected List<AppModel> doInBackground(Void... params) {
            List<AppModel> appList = new ArrayList<>();
            try {
                String appFileData = AppLoader.getInstance(PortfolioActivity.this).loadAppFile();
                JSONObject jsonData = new JSONObject(appFileData);
                JSONArray jsonArray = jsonData.getJSONArray("apps");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    AppModel app = new AppModel(jsonObject);
                    appList.add(app);
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            return appList;
        }


        @Override
        protected void onPostExecute(List<AppModel> apps) {
            appList = apps;
            appAdapter.setApps(apps);
            appAdapter.notifyDataSetChanged();
        }

    }
}
