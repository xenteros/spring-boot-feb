package com.github.xenteros.controller;

import com.github.xenteros.dto.ExchangeRates;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

//@RestController
public class CurrencyExchangeController {

    @GetMapping("/{from}/{to}")
    public Double getRate(@PathVariable String from, @PathVariable String to) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ExchangeRates> response =
                restTemplate.getForEntity("http://api.fixer.io/latest?base=" + from, ExchangeRates.class);

        return response.getBody().getRates().get(to);
    }

    @GetMapping("/response-entity/{from}/{to}")
    public ResponseEntity<Double> getRateResponseEntity(@PathVariable String from, @PathVariable String to) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ExchangeRates> response =
                restTemplate.getForEntity("http://api.fixer.io/latest?base=" + from, ExchangeRates.class);

        return new ResponseEntity<Double>(response.getBody().getRates().get(to), HttpStatus.CREATED);
    }


}
