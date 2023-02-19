package com.dmitrenko.imageanalyzer.service.domain;

import com.dmitrenko.imageanalyzer.model.dto.response.ImageResponse;

import java.util.List;
import java.util.UUID;

public interface ImageObjectDomainService {

    UUID addOrGet(String tag);

    List<ImageResponse> getImageByObjects(List<String> objects);
}
