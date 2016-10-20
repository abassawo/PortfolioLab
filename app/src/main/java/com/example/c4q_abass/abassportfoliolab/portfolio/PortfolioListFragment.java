package com.example.c4q_abass.abassportfoliolab.portfolio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q_abass.abassportfoliolab.model.AppCollection;
import com.example.c4q_abass.abassportfoliolab.Mvp;
import com.example.c4q_abass.abassportfoliolab.R;
import com.example.c4q_abass.abassportfoliolab.model.AppModel;

/**
 * Created by c4q-Abass on 5/19/16.
 */
public class PortfolioListFragment extends Fragment {

    private static final String CATEGORY_KEY = "category_tag_key" ;
    private static PortfolioListFragment INSTANCE;
    private static String APPSET_KEY = "appset";
    private RecyclerView recyclerView;
    private AppAdapter appAdapter;
    private String TAG = PortfolioListFragment.class.getSimpleName();
    private Mvp.Presenter presenter;


    public static PortfolioListFragment newInstance(String TAG, AppCollection appSet){
       Bundle args = new Bundle();
       INSTANCE = new PortfolioListFragment();
       args.putSerializable(APPSET_KEY, appSet);
       args.putString(CATEGORY_KEY, TAG);
       INSTANCE.setArguments(args);
       return INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        TAG = args.getString(CATEGORY_KEY) + "Fragment";
        AppCollection appSet = (AppCollection) args.getSerializable(APPSET_KEY);
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
