package com.github.xenteros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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




}
