package com.example.topquiz.Views;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

/**
 * This apps is created by Angie, in 02 2020.
 * TopQuiz. Developped since formation.
 */

public class ScoreDisplayViewHolder extends RecyclerView.ViewHolder {

    TextView mTextViewName;
    TextView mTextViewScore;

    public ScoreDisplayViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextViewName = itemView.findViewById(R.id.txt_name);
        mTextViewScore = itemView.findViewById(R.id.txt_score);
    }

    public void updateWithListUser(User user){
        this.mTextViewName.setText(user.getFirstName());
        this.mTextViewScore.setText(Integer.toString(user.getScore()));
    }
}
