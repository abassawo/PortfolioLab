package com.example.c4q_abass.abassportfoliolab;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.c4q_abass.abassportfoliolab.helpers.JSONReader;
import com.example.c4q_abass.abassportfoliolab.model.AppCollection;
import com.example.c4q_abass.abassportfoliolab.model.AppModel;
import com.example.c4q_abass.abassportfoliolab.portfolio.PortfolioListFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabs;
    private TabAdapter tabAdapter;
    private ViewPager viewPager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        tabs.setupWithViewPager(viewPager);
        new AsyncAppLoader().execute();
    }




    private static class TabAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(String title, AppCollection appSet) {
            fragments.add(PortfolioListFragment.newInstance(title, appSet));
            fragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }



private class AsyncAppLoader extends AsyncTask<Void, Void, Map<String, List<AppModel>>> {
        Map<String, List<AppModel>> appMap = new HashMap<>();
        private static final String TAG = "AsyncLoader";

        @Override
        protected Map<String, List<AppModel>> doInBackground(Void... params) {

            try {
                String appFileData = JSONReader.getInstance(MainActivity.this).readJSON();
                JSONObject jsonData = new JSONObject(appFileData);
                JSONArray jsonArray = jsonData.getJSONArray("apps");


                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //Verify that we're filtering
                    List<AppModel> apps;
                    String category  = jsonObject.getString("category");
                    Log.d(TAG, "bkground thread. elem at idx " + i + " was " + category);
                    AppModel app = new AppModel(jsonObject);

                    if(appMap.containsKey(category)){
                        apps = appMap.get(category);
                    }else{
                        apps = new ArrayList<>();
                    }

                    apps.add(app);
                    appMap.put(category, apps);

                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
            return appMap;
        }


        @Override
        protected void onPostExecute(Map<String, List<AppModel>> apps) {
            for(String name : apps.keySet()){
                List<AppModel> appList = apps.get(name);
                PortfolioListFragment fragment = PortfolioListFragment.newInstance(name, new AppCollection(appList));
                tabAdapter.addFragment(name,  new AppCollection(appList));
            }
            tabAdapter.notifyDataSetChanged();

        }

    }



}
