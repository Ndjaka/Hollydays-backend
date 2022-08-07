package com.ozone.hollidays.services;

import com.ozone.hollidays.dtos.HollidayDto;
import com.ozone.hollidays.entities.Comment;
import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.entities.Image;
import com.ozone.hollidays.enums.LikesType;
import com.ozone.hollidays.enums.StatusType;
import com.ozone.hollidays.exception.HollydaysException;
import com.ozone.hollidays.repositories.CommentRepository;
import com.ozone.hollidays.repositories.HollidayRepository;
import com.ozone.hollidays.repositories.ImageRepository;
import com.ozone.hollidays.repositories.LikeRepository;
import com.ozone.hollidays.services.interfaces.AuthService;
import com.ozone.hollidays.services.interfaces.HolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Slf4j
public class HolidayServiceImpl implements HolidayService {

    private final HollidayRepository hollidayRepository;
    private final LikeRepository likeRepository;
    private final ImageRepository imageRepository;
    private final AuthService authService;
    private final MinIOService minIOService;

    private final CommentRepository commentRepository;

    public HolidayServiceImpl(HollidayRepository hollidayRepository, LikeRepository likeRepository, ImageRepository imageRepository, AuthService authService, MinIOService minIOService, CommentRepository commentRepository) {
        this.hollidayRepository = hollidayRepository;
        this.likeRepository = likeRepository;
        this.imageRepository = imageRepository;
        this.authService = authService;
        this.minIOService = minIOService;
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional
    public Holliday saveHoliday(Holliday holliday) {
        hollidayRepository.save(mapToHoliday(holliday));
        return mapToHoliday(holliday);
    }

    @Override
    @Transactional
    public Holliday updaTeHoliday(Holliday holliday) {
        var findHoliday = hollidayRepository.findByIdAndUser(holliday.getId(),authService.getCurrentUser()).orElseThrow(()-> new HollydaysException("Holiday not found"));
        findHoliday.setId(holliday.getId());
        findHoliday.setDescription(holliday.getDescription());
        findHoliday.setHotel(holliday.getHotel());
        hollidayRepository.save(mapToHoliday(findHoliday));
        return mapToHoliday(holliday);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Image> saveImagesOfHoliday(Integer id, List<MultipartFile> multipartFiles) {

        Optional<Holliday> optionalHoliday = hollidayRepository.findById(id);
        Holliday holliday = optionalHoliday.orElseThrow(() -> new HollydaysException("Holiday not found"));

        List<Image> images = new ArrayList<>();

        multipartFiles.forEach(file -> {
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try {
                imageRepository.save(new Image(null, filename, holliday));
                minIOService.WriteToMinIO(filename, file.getInputStream());
                images.add(new Image(null, filename, holliday));
            } catch (Exception e) {
                e.printStackTrace();
            }


        });

        return images;
    }

    @Transactional
    @Override
    public HashMap getHolidays(int page, int per_page) {
        Pageable pageable = PageRequest.of(page, per_page,Sort.by("id").ascending());
        Page<Holliday> pages = hollidayRepository.findAll(pageable);
        List<Holliday> holidays = pages.getContent();
        List<HollidayDto> hollidayDtos = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();

        for(Holliday holiday : holidays){
            HollidayDto hollidayDto = new HollidayDto();

            List<Image> images = imageRepository.findImageByHollidays_Id(holiday.getId()).stream()
                    .map(this::mapToImage)
                    .collect(Collectors.toList());

            List<Comment> comments = commentRepository.findByHollidays_Id(holiday.getId());

            hollidayDto.setHoliday(holiday);
            hollidayDto.getImages().addAll(images);
            hollidayDto.setComments(comments);
            hollidayDto.setCommentCount(comments.size());
            hollidayDto.setLikeCount(likeRepository.findLikesByHollidayAndLikeType(holiday, LikesType.LIKE).size());
            hollidayDtos.add(hollidayDto);
        };

        result.put("holidays", hollidayDtos);
        result.put("pages", pages.getTotalPages());
        result.put("total_elements", pages.getTotalElements());
        result.put("page", page);

        return (HashMap) result;
    }

    private Image mapToImage(Image image) {
        Image image1 = new Image();
        image1.setId(image.getId());
        try {
            image1.setUrl(minIOService.getUrl(image.getUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image1;
    }

    private Holliday mapToHoliday(Holliday holliday){
        Holliday newHoliday = new Holliday();
        newHoliday.setUser(authService.getCurrentUser());
        newHoliday.setHotel(holliday.getHotel());
        newHoliday.setDescription(holliday.getDescription());
        newHoliday.setDate(LocalDateTime.now());
        newHoliday.setId(newHoliday.getId());
        newHoliday.setStatus(StatusType.valueOf("ALL_USER"));
        return newHoliday;
    }

}
