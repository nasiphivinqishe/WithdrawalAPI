package com.example.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Api(tags = "Demo Controller")
public class DemoApplicationController {

    @GetMapping("names")
    @ApiOperation("Returns a name")
    public String returnSomething() {
        return "Nasiphi";
    }


    @GetMapping("api-doc")
    public String apiDocs() {
        //find a way to interpret that yaml file and return as html
        return "Nasiphi";
    }
}
