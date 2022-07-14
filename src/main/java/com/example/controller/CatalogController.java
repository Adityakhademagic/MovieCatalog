package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;
    public List<Catalog> catalogs = new ArrayList<>();

    @GetMapping("/{userId}")
    public List<Catalog> getAllCatalog(@PathVariable("userId") String userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8082/rating/users/" + userId, UserRating.class);
        return ratings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
            return new Catalog(movie.getName(), "SRK", rating.getRating());
        })
        .collect(Collectors.toList());

    }




    @PostMapping(value= "/addRating",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Rating addRating(@RequestBody  List<Rating> ratings) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Rating> entity = new HttpEntity<Rating>((Rating) ratings,headers);

        return restTemplate.exchange("http://localhost:8082/rating/addRatings", HttpMethod.POST, entity, Rating.class).getBody();

//
//        Rating rating = restTemplate.postForObject("http://localhost:8082/rating/addRatings","Post", Rating.class);
//        System.out.println(rating);

    }


}


