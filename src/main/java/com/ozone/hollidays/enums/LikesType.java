package com.ozone.hollidays.enums;

import com.ozone.hollidays.exception.HollydaysException;

import java.util.Arrays;

public enum LikesType {
    LIKE(1),
    DISLIKE(-1);

    private  int action;

     LikesType(int number){
        this.action = number;
    }

    public static LikesType lookUp(int statusLike){
        return Arrays.stream(LikesType.values())
                .filter(value -> value.getAction() == statusLike).
                findAny()
                .orElseThrow(()-> new HollydaysException("Like not found")) ;
    }


    public int getAction() {
        return action;
    }
}
