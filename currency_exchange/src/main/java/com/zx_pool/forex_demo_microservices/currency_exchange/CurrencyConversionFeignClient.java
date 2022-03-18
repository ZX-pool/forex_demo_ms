package com.zx_pool.forex_demo_microservices.currency_exchange;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "forex")
public interface CurrencyConversionFeignClient {
    @GetMapping("/forex/from/{from}/to/{to}")
    CurrencyConversionBean getExchangeValue(@PathVariable String from, @PathVariable String to);
}
