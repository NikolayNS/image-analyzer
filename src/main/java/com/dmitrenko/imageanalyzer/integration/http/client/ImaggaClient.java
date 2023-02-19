package com.dmitrenko.imageanalyzer.integration.http.client;

import com.dmitrenko.imageanalyzer.model.dto.response.ImaggaResponse;

public interface ImaggaClient {

    ImaggaResponse analyze(String imageUrl);
}
