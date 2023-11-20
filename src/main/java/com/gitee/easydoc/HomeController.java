package com.gitee.easydoc;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
@CrossOrigin(origins={"*"})
public class HomeController {

    @GetMapping("/")
    public String home(Locale locale, Model model) {
        return "redirect:pages/main.html";
    }

}
