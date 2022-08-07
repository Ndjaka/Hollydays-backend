package com.ozone.hollidays.services;

import com.ozone.hollidays.dtos.LikeDto;
import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.entities.Like;
import com.ozone.hollidays.entities.User;
import com.ozone.hollidays.enums.LikesType;
import com.ozone.hollidays.exception.HollydaysException;
import com.ozone.hollidays.repositories.HollidayRepository;
import com.ozone.hollidays.repositories.LikeRepository;
import com.ozone.hollidays.services.interfaces.AuthService;
import com.ozone.hollidays.services.interfaces.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LikeServiceImpl implements LikeService {
    private final AuthService authService;
    private final HollidayRepository hollidayRepository;
    private final LikeRepository likeRepository;

    public LikeServiceImpl(AuthService authService, HollidayRepository hollidayRepository, LikeRepository likeRepository) {
        this.authService = authService;
        this.hollidayRepository = hollidayRepository;
        this.likeRepository = likeRepository;
    }

/**
 * recherche la publication qui a été faite
 * je récupère l'utilisateur qui veut liker
 * je recherche si le like existe déja sur la publication (je le recherche  en fonction de l'id du like )
 * si elle existe je mets a jour le like sinon je créé le like
 */


    @Override

    public LikeDto addLikeOrRemoveLike(LikeDto likeDto) {
        log.info("likeDto :" + likeDto);

        var currentUser = authService.getCurrentUser();
        var holidayFound = hollidayRepository.findById(Math.toIntExact(likeDto.getHolidayId()))
                .orElseThrow(()-> new HollydaysException("Holiday not found"));
        var likeFound = this.likeRepository.findTopByHollidayAndUserOrderByIdDesc(holidayFound,currentUser);

        if(likeFound.isPresent()){
            Like likeShouldBeFounded = likeFound.get();
            likeShouldBeFounded.setLikeType(LikesType.lookUp(likeDto.getType().getAction()));

            likeShouldBeFounded.setHolliday(holidayFound);
            likeShouldBeFounded.setUser(currentUser);
            likeRepository.save(likeShouldBeFounded);
        }else{
            likeRepository.save(mapToLike(likeDto,holidayFound,currentUser));}
        return likeDto;
    }

    private Like mapToLike(LikeDto likeDto, Holliday holliday, User user){
        return Like.builder()
                .likeType(likeDto.getType())
                .user(user)
                .holliday(holliday)
                .build();
    }
}
