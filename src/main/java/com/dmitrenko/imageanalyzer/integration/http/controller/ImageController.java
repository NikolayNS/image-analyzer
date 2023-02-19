package com.dmitrenko.imageanalyzer.integration.http.controller;

import com.dmitrenko.imageanalyzer.model.dto.request.ImageRequest;
import com.dmitrenko.imageanalyzer.model.dto.response.ImageResponse;
import com.dmitrenko.imageanalyzer.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.dmitrenko.imageanalyzer.util.Constant.IMAGES_V1;
import static com.dmitrenko.imageanalyzer.util.Constant.IMAGE_BY_ID_V1;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "Add Image")
    @PostMapping(value = IMAGES_V1, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ImageResponse add(@Valid @RequestBody ImageRequest request) {
        return imageService.add(request);
    }

    @Operation(summary = "Get images by params or all")
    @GetMapping(value = IMAGES_V1, produces = APPLICATION_JSON_VALUE)
    public List<ImageResponse> getByParams(@RequestParam(required = false) List<String> objects) {
        return imageService.getByParams(objects);
    }

    @Operation(summary = "Get image by id")
    @GetMapping(value = IMAGE_BY_ID_V1, produces = APPLICATION_JSON_VALUE)
    public ImageResponse getById(@PathVariable UUID imageId) {
        return imageService.getById(imageId);
    }
}
