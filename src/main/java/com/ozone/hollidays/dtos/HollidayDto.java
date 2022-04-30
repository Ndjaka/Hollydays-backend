package com.ozone.hollidays.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ozone.hollidays.entities.Comment;
import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.entities.Image;
import com.ozone.hollidays.enums.LikesType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HollidayDto {
    private Integer CommentCount;
    private Integer LikeCount;
    private Holliday holiday;
    private List<Image> images = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
}
