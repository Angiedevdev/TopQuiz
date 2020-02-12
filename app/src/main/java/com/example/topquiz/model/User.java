package com.example.topquiz.model;


public class User {
   private String mFirstName;
   private int mScore;

   public User(){
   }

   public User(String firstName, int score) {
       mFirstName = firstName;
       mScore = score;
   }

   public String getFirstName() {
       return mFirstName;
   }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public int getScore(){
       return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstName='" + mFirstName + '\'' +
                ", mScore=" + mScore +
                '}';
    }




}
