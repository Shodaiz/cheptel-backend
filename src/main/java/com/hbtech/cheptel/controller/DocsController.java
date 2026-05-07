package com.hbtech.cheptel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocsController {

    @GetMapping({"/docs", "/swagger", "/swagger-ui"})
    public String docs() {
        return "redirect:/webjars/swagger-ui/index.html?url=/api-docs";
    }
}
