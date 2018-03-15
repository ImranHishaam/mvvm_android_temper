package com.example.imranhishaam.temper.view;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imranhishaam.temper.R;
import com.example.imranhishaam.temper.model.JobModel;
import com.example.imranhishaam.temper.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imranhishaam on 3/14/18.
 */

public class MainFragment extends Fragment {
    public final static String ITEMS_COUNT_KEY = "MainFragment$Items";

    public static MainFragment createInstance(ArrayList<JobModel> jobList) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ITEMS_COUNT_KEY, jobList);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_main, container, false);
        setupRecyclerView(recyclerView);
        return recyclerView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainAdapter recyclerAdapter = new MainAdapter();
        Bundle bundle = getArguments();
        ArrayList<JobModel> jobList = (ArrayList< JobModel>) bundle.<JobModel>getParcelableArrayList(ITEMS_COUNT_KEY);
        recyclerAdapter.setJobList(jobList);
        recyclerView.setAdapter(recyclerAdapter);
    }

}