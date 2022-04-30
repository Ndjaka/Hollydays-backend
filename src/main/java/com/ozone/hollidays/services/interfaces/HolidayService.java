package com.ozone.hollidays.services.interfaces;

import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface HolidayService {
    public Holliday saveHoliday(Holliday holliday);
    public List<Image> saveImagesOfHoliday(Integer id , List<MultipartFile> multipartFiles);
    public HashMap getHolidays(int page , int per_page);
}
