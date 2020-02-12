package com.example.topquiz.Views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

import java.util.ArrayList;

/**
 * This apps is created by Angie, in 02 2020.
 * TopQuiz. Developped since formation.
 */

public class ScoreDisplayRecyclerView extends Fragment {
    private ArrayList<User> mUsers;
    private ScoreDisplayAdapter adapter;
    RecyclerView recyclerView;

    public ScoreDisplayRecyclerView(){
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_score, container, false);
        this.configureRecyclerView();
        updateUI(mUsers);
        return view;
    }

    public  void onDestroy(){
        super.onDestroy();
    }

    public void configureRecyclerView(){
        this.mUsers = new ArrayList<>();
        this.adapter = new ScoreDisplayAdapter(this.mUsers);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void updateUI(ArrayList<User> users){
        mUsers.addAll(users);
        adapter.notifyDataSetChanged();
    }
}

