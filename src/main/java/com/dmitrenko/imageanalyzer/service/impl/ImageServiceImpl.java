package com.dmitrenko.imageanalyzer.service.impl;

import com.dmitrenko.imageanalyzer.integration.http.client.ImaggaClient;
import com.dmitrenko.imageanalyzer.model.dto.request.ImageRequest;
import com.dmitrenko.imageanalyzer.model.dto.response.ImageResponse;
import com.dmitrenko.imageanalyzer.service.ImageService;
import com.dmitrenko.imageanalyzer.service.domain.ImageDomainService;
import com.dmitrenko.imageanalyzer.service.domain.ImageObjectDomainService;
import com.dmitrenko.imageanalyzer.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Value("${core-logic.confidence}")
    private Double confidence;

    private final ValidationUtil validationUtil;

    private final ImageDomainService imageDomainService;
    private final ImageObjectDomainService imageObjectDomainService;

    private final ImaggaClient imaggaClient;

    @Override
    public ImageResponse add(ImageRequest request) {
        var imageId = imageDomainService.getByUrl(request.getUrl());

        if(imageId == null) imageId = imageDomainService.add(request);

        if (request.isEnableDetection()) {
            var imaggaResult = imaggaClient.analyze(request.getUrl());
            imaggaResult = validationUtil.validate(imaggaResult);

            var topTags = imaggaResult.getResult()
                .getTags()
                .stream()
                .filter(o -> o.getConfidence() > confidence)
                .map(o -> o.getTag().getEn())
                .map(imageObjectDomainService::addOrGet)
                .toList();

            imageDomainService.update(imageId, topTags);
        }

        return imageDomainService.getById(imageId);
    }

    @Override
    public List<ImageResponse> getByParams(List<String> objects) {
        return objects == null
            ? imageDomainService.getAll()
            : imageObjectDomainService.getImageByObjects(objects);
    }

    @Override
    public ImageResponse getById(UUID imageId) {
        return imageDomainService.getById(imageId);
    }
}
