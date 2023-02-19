package com.dmitrenko.imageanalyzer.service.domain.impl;

import com.dmitrenko.imageanalyzer.mapper.ImageMapper;
import com.dmitrenko.imageanalyzer.model.dto.response.ImageResponse;
import com.dmitrenko.imageanalyzer.model.entity.ImageObject;
import com.dmitrenko.imageanalyzer.repository.ImageObjectRepository;
import com.dmitrenko.imageanalyzer.service.domain.ImageObjectDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageObjectDomainServiceImpl implements ImageObjectDomainService {

    private final ImageObjectRepository imageObjectRepository;

    private final ImageMapper imageMapper;

    @Override
    @Transactional
    public UUID addOrGet(String tag) {
        var object = imageObjectRepository.findByTag(tag)
            .orElse(imageObjectRepository.saveAndFlush(new ImageObject().setTag(tag)));
        return object.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageResponse> getImageByObjects(List<String> objects) {
        return imageMapper.from(imageObjectRepository
            .findAllByTagIn(objects)
            .stream()
            .map(ImageObject::getImages)
            .flatMap(Set::stream)
            .toList());
    }
}
