package com.dmitrenko.imageanalyzer.mapper;

import com.dmitrenko.imageanalyzer.model.dto.request.ImageRequest;
import com.dmitrenko.imageanalyzer.model.dto.response.ImageResponse;
import com.dmitrenko.imageanalyzer.model.entity.Image;

import java.util.Collection;
import java.util.List;

public interface ImageMapper {

    ImageResponse from(Image source);

    default List<ImageResponse> from(Collection<Image> source) {
        return source
            .stream()
            .map(this::from)
            .toList();
    }

    Image from(ImageRequest request);
}
