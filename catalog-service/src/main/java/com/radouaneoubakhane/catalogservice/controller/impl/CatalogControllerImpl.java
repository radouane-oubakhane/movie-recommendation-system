package com.radouaneoubakhane.catalogservice.controller.impl;

import com.radouaneoubakhane.catalogservice.controller.CatalogController;
import com.radouaneoubakhane.catalogservice.dto.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;


@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogControllerImpl implements CatalogController {

    @GetMapping("/recommended")
    public HttpResponse<Page<MovieResponse>> getRecommendedMovies() {
        return null;
    }



}
