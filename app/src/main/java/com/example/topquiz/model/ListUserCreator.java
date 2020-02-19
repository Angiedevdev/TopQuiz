package com.example.topquiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This apps is created by Angie, in 01 2020.
 * TopQuiz. Developped since formation.
 */

public class ListUserCreator {

    public List<User> mUserList;

    public ListUserCreator (){
        mUserList = new ArrayList<User>();
    }

    public List<User> getUserList() {
        return mUserList;
    }

    public void addUser(User user){
        mUserList.add(user);
    }
}


