package dev.byAbrao.beerstock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Manages beer stock")
public interface BeerControllerDocs {

    @ApiOperation(value = "Hello world!")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "returns a simple hello world")})
    String helloAPI();
}
