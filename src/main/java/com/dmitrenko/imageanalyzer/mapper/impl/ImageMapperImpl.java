package com.dmitrenko.imageanalyzer.mapper.impl;

import com.dmitrenko.imageanalyzer.mapper.ImageMapper;
import com.dmitrenko.imageanalyzer.model.dto.request.ImageRequest;
import com.dmitrenko.imageanalyzer.model.dto.response.ImageResponse;
import com.dmitrenko.imageanalyzer.model.entity.Image;
import com.dmitrenko.imageanalyzer.model.entity.ImageObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.dmitrenko.imageanalyzer.util.Constant.STATIC_LABEL;

@Service
@RequiredArgsConstructor
public class ImageMapperImpl implements ImageMapper {

    @Override
    public ImageResponse from(Image source) {
        return new ImageResponse()
            .setId(source.getId())
            .setUrl(source.getUrl())
            .setLabel(source.getLabel())
            .setObjects(source.getObjects().stream().map(ImageObject::getTag).toList());
    }

    @Override
    public Image from(ImageRequest request) {
        return new Image()
            .setUrl(request.getUrl())
            .setLabel(request.getLabel() == null ? STATIC_LABEL : request.getLabel());
    }
}
