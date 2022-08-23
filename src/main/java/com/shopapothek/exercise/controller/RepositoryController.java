package com.shopapothek.exercise.controller;

import com.shopapothek.exercise.remote.GitHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/repository")
public class RepositoryController {

    @Autowired
    private GitHubClient gitHubClient;

    // return most popular repositories based on no of stars
    @GetMapping(value = "", produces = "application/json")
    public String getMostPopularRepositories( ) {
        return gitHubClient.getRepositories("star:>1", 1, Integer.MAX_VALUE, "stars", "desc");
    }

    // return most popular repositories based on no of stars also option to pass count per page either 10, 50, 100 or any
    @GetMapping(value = "/{count}", produces = "application/json")
    public String getMostPopularRepositoriesByCount(@PathVariable(required = false) Integer count) {
        if(count == null) count = Integer.MAX_VALUE;
        return gitHubClient.getRepositories("star:>1", 1, count, "stars", "desc");
    }

    // return repositories filter based on date
    @GetMapping(value = "/date/{date}", produces = "application/json")
    public String getRepositoriesByDate(@PathVariable String date) {

        return gitHubClient.getRepositories("created:>" + date, 1, Integer.MAX_VALUE,"stars", "desc");
    }
}
