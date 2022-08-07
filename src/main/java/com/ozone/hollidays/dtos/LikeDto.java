package com.ozone.hollidays.dtos;

import com.ozone.hollidays.enums.LikesType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDto {
    private Long holidayId;
    private LikesType type;
}
