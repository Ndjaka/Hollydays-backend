package com.ozone.hollidays.repositories;

import com.ozone.hollidays.entities.Holliday;
import com.ozone.hollidays.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
        public List<Image> findImageByHollidays_Id(Integer id);
}