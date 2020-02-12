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

    TextView mTextView;

    public ScoreDisplayViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void updateWithListUser(User user){
        this.mTextView.setText(user.toString());
    }
}
