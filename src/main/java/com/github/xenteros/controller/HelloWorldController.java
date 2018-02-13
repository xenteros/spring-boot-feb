package com.github.xenteros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
public class HelloWorldController {

    //    @Autowired
    private String helloWorld;

    @GetMapping("/di")
    public String helloInjection() {
        return helloWorld;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hello/{name}/{age}")
    public String helloWorldPath(@PathVariable String name, @PathVariable Long age) {
        return "Hello " + name + ", of age " + age + "!";
    }

    @GetMapping
    public String helloWorldParam(@RequestParam(name = "name", required = false, defaultValue = "Anonymous") String name) {
        return "Hello " + name + "!";
    }

    @PutMapping
    public String helloWorldBody(@RequestBody String name) {
        return "Hello " + name + "!";
    }

    //    @Autowired
    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }

    @Autowired
    public HelloWorldController(String helloWorld) {
        this.helloWorld = helloWorld;
    }

}
