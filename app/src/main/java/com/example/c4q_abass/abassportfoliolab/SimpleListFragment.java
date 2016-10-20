package com.example.c4q_abass.abassportfoliolab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by c4q-Abass on 5/19/16.
 */
public class SimpleListFragment extends Fragment {

    private static final String CATEGORY_KEY = "category_tag_key" ;
    private static SimpleListFragment INSTANCE;
    private static String APPSET_KEY = "appset";
    private RecyclerView recyclerView;
    private AppAdapter appAdapter;
    private String TAG = SimpleListFragment.class.getSimpleName();


    public static SimpleListFragment newInstance(String TAG, AppSet appSet){
       Bundle args = new Bundle();
       INSTANCE = new SimpleListFragment();
        args.putSerializable(APPSET_KEY, appSet);
        args.putString(CATEGORY_KEY, TAG);
       INSTANCE.setArguments(args);
       return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        TAG = args.getString(CATEGORY_KEY);
        AppSet appSet = (AppSet) args.getSerializable(APPSET_KEY);
        appAdapter = new AppAdapter(getActivity(), appSet);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.portfolio_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d(TAG, "Count was " + appAdapter.getItemCount());
        recyclerView.setAdapter(appAdapter);
    }

}
