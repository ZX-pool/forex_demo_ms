package com.zx_pool.forexdemomicroservices.web_currency_exchange;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RESTController {


    @GetMapping("/eur-to-uah")
    public String currencyToUah (@RequestParam(value = "EUR") BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", "EUR");
        uriVariables.put("to", "UAH");
        uriVariables.put("quantity", String.valueOf(quantity));

        //get result using RESTTemplate
//        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
//                "http://localhost:8100/conversion-RESTTemplate/from/{from}/to/{to}/quantity/{quantity}",
//                CurrencyConversionBean.class, uriVariables);


        //get result using FeignClient
//        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
//                "http://localhost:8100/conversion-FeignClient/from/{from}/to/{to}/quantity/{quantity}",
//                CurrencyConversionBean.class, uriVariables);


        //get result using WebClient
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8100/conversion-WebClient/from/{from}/to/{to}/quantity/{quantity}",
                CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean response = responseEntity.getBody();

        return String.format("Quantity of currency is =  %s  EUR ! <br>" +
                "Total calculated amount is = %s  UAH ! <br> <br>" +
                "<a href=/> to begin </a>", quantity, response.getTotalCalculatedAmount());
    }
}
