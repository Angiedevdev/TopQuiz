package com.example.topquiz.model;

import java.util.Comparator;

/**
 * This apps is created by Angie, in 01 2020.
 * TopQuiz. Developped since formation.
 */

public class AlphabeticComparator implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        User u1 = (User) o1;
        User u2 = (User) o2;

        return u1.getFirstName().compareTo(u2.getFirstName());
    }
}
