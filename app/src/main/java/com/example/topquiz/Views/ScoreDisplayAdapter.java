package com.example.topquiz.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

import java.util.List;


/**
 * This apps is created by Angie, in 02 2020.
 * TopQuiz. Developped since formation.
 */

public class ScoreDisplayAdapter extends RecyclerView.Adapter<ScoreDisplayViewHolder> {

    private List<User> mUsersListScore;
    private int limitView = 5;

    public ScoreDisplayAdapter(List<User> mUsersListScore) {
        this.mUsersListScore = mUsersListScore;
    }

    @NonNull
    @Override
    public ScoreDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.score_display_item, parent, false);

        return new ScoreDisplayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreDisplayViewHolder holder, int position) {
        holder.updateWithListUser(this.mUsersListScore.get(position));
    }

    @Override
    public int getItemCount() {
        if(mUsersListScore.size() > limitView) {
            return limitView;
        } else {
            return this.mUsersListScore.size();
        }
    }
}
