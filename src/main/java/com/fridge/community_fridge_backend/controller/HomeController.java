package com.fridge.community_fridge_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(@RequestParam(value = "continue", required = false) String continueParam) {
        if (continueParam != null) {
            // Handle the 'continue' parameter
            return "Redirect or process continue parameter: " + continueParam;
        }
        return "Welcome to the home page of Community fridge !";
    }

    // @GetMapping("/")
    // public RedirectView home(@RequestParam(value = "continue", required = false) String continueParam) {
    //     if (continueParam != null) {
    //         // Redirect to the desired page or process continue parameter
    //         return new RedirectView("/someOtherPage");
    //     }
    //     return new RedirectView("/welcome");
    // }
}


