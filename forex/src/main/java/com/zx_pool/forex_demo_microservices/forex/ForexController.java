package com.zx_pool.forex_demo_microservices.forex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/forex/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {

        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

        return exchangeValue;
    }

}

