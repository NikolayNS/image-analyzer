package com.dmitrenko.imageanalyzer.repository;

import com.dmitrenko.imageanalyzer.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    Optional<Image> findByUrl(String url);
}
