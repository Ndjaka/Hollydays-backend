package com.ozone.hollidays.enums;

public enum LikesType {
    LIKE(1),
    DISLIKE(-1);

    private final int action;

     LikesType(int number){
        this.action = number;
    }
}
