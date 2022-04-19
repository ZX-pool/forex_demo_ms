package com.zx_pool.forex_demo_microservices.currency_exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@RestController
public class CurrencyConversionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${forex.url}")
    private String FOREX_URL;

    @GetMapping("/conversion-RESTTemplate/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversionRESTTemplate(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
                FOREX_URL+"/forex/from/{from}/to/{to}",
                CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean response = responseEntity.getBody();

        BigDecimal totalCalculatedAmount = quantity.multiply(response.getConversionMultiple());

        logger.info("{}", response);

        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
                quantity, totalCalculatedAmount, response.getPort());
    }

    @Autowired
    private CurrencyConversionFeignClient feignClient;

    @GetMapping("/conversion-FeignClient/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversionFeignClient(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        CurrencyConversionBean response = feignClient.getExchangeValue(from, to);

        BigDecimal totalCalculatedAmount = quantity.multiply(response.getConversionMultiple());
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
                quantity, totalCalculatedAmount, response.getPort());
    }

    @GetMapping("/conversion-WebClient/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean currencyConversionWebClient(
            @PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        //WebClient client = WebClient.create("http://localhost:8000");
        WebClient client = WebClient.create("forex");
        CurrencyConversionBean response =
                client.get().
                        uri(FOREX_URL+"/forex/from/{from}/to/{to}", "EUR", "UAH").
                        retrieve().
                        bodyToMono(CurrencyConversionBean.class).
                        block();

        BigDecimal totalCalculatedAmount = quantity.multiply(response.getConversionMultiple());
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(),
                quantity, totalCalculatedAmount, response.getPort());
    }

}
