package com.shopapothek.exercise.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "repositories", url = "${github.url}")
public interface GitHubClient {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    String getRepositories(@RequestParam(value="q") String query,
                           @RequestParam(value="page") Integer page,
                           @RequestParam(value="per_page") Integer count,
                           @RequestParam(value="sort") String sort,
                           @RequestParam(value="order") String order);
}
