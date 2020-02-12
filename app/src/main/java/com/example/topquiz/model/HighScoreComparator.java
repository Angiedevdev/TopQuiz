package com.example.topquiz.model;

import java.util.Comparator;

/**
 * This apps is created by Angie, in 01 2020.
 * TopQuiz. Developped since formation.
 */

public class HighScoreComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        User u1 = (User) o1;
        User u2 = (User) o2;
        if (u1.getScore() == u2.getScore())
            return 0;
        else if (u2.getScore() > u1.getScore())
            return 1;
        else
            return -1;
    }
}
