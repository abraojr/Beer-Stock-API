package dev.byAbrao.beerstock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/beers")
public class BeerController implements BeerControllerDocs {

    @GetMapping
    public String helloAPI() {
        return "Hello API";
    }
}
