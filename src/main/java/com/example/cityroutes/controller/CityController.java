package com.example.cityroutes.controller;

import com.example.cityroutes.helper.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    Loader cityLoader;

    @GetMapping("/connected")
    public String isPathExists(@RequestParam(value = "origin") String source, @RequestParam(value="destination") String target){
        return cityLoader.checkRoute(source,target);
    }

}
