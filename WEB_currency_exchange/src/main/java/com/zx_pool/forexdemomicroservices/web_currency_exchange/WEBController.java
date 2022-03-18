package com.zx_pool.forexdemomicroservices.web_currency_exchange;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WEBController {

    @GetMapping("/inputEUR")
    public String starter(){
        return "inputEUR";
    }


}
