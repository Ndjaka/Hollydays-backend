package com.ozone.hollidays.enums;

public enum Gender {
    MALE (0) , 
    FEMALE(1) ;

    private int gender;

    Gender(int i) {
        this.gender = i;
    }

    public int getGender() {
        return gender;
    }
}
