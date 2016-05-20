package com.example.c4q_abass.abassportfoliolab;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q-Abass on 5/19/16.
 */
public class PortfolioFragment extends Fragment {

    private static PortfolioFragment INSTANCE;
    private static String APPSET_KEY;
    private RecyclerView recyclerView;
    private AppAdapter appAdapter;
    private AppSet appSet;
    private String appCategory;

    public static PortfolioFragment getInstance(String category){
       Bundle args = new Bundle();
       args.putString(APPSET_KEY, category); //fixme
       if(INSTANCE == null){
           INSTANCE = new PortfolioFragment();
       }
       INSTANCE.setArguments(args);
       return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            appCategory = args.getString(APPSET_KEY);
        }else{
            appCategory = "Apps";
        }

        appSet = new AppSet(appCategory);
        appAdapter = new AppAdapter(getActivity(), appSet);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.portfolio_recycler_view);
        setupRV(recyclerView);
        new AsyncAppLoader().execute();
    }


    public void setupRV(RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(appAdapter);
    }

    private class AsyncAppLoader extends AsyncTask<Void, Void, AppSet> {

        private static final String TAG = "JSONReader";

        @Override
        protected AppSet doInBackground(Void... params) {
            AppSet appList = new AppSet();
            try {
                String appFileData = JSONReader.getInstance(getActivity()).readJSON();
                JSONObject jsonData = new JSONObject(appFileData);
                JSONArray jsonArray = jsonData.getJSONArray("apps");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //Verify that we're filtering
                    if(jsonObject.getString("category").equalsIgnoreCase(appCategory)){
                        AppModel app = new AppModel(jsonObject);
                        appList.add(app);
                    }

                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            return appList;
        }


        @Override
        protected void onPostExecute(AppSet apps) {
            appSet = apps;
            appAdapter.setApps(apps);
            appAdapter.notifyDataSetChanged();
        }

    }

}
