package com.dmitrenko.imageanalyzer.service;

import com.dmitrenko.imageanalyzer.model.dto.request.ImageRequest;
import com.dmitrenko.imageanalyzer.model.dto.response.ImageResponse;

import java.util.List;
import java.util.UUID;

public interface ImageService {

    ImageResponse add(ImageRequest request);

    List<ImageResponse> getByParams(List<String> objects);

    ImageResponse getById(UUID imageId);
}
