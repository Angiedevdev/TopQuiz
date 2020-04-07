package com.example.topquiz.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This apps is created by Angie, in 01 2020.
 * TopQuiz. Developped since formation.
 */

public class ListUserCreator {

    private List<User>  mUserList;

    public ListUserCreator (){
        mUserList = new ArrayList<User>();
    }

    public List<User> getUserList() {
        return mUserList;
    }

    public void addUser(User user) {
            mUserList.add(user);
    }
    public void removeUser(User user) {
        mUserList.remove(user);
    }
}


