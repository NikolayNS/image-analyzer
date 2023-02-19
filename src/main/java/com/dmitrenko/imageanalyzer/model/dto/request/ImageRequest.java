package com.dmitrenko.imageanalyzer.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ImageRequest {

    @NotBlank(message = "field [url] can't be empty")
    private String url;

    private String label;
    private boolean enableDetection;
}
