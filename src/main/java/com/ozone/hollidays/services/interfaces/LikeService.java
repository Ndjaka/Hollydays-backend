package com.ozone.hollidays.services.interfaces;

import com.ozone.hollidays.dtos.LikeDto;


public interface LikeService {

    /**
     * this function is for added or removed like on the holiday
     * @param likeDto parameter who contains id of holidas and type of like
     * @return LikeDto like should be added
     * */
    public LikeDto addLikeOrRemoveLike(LikeDto likeDto);

}
