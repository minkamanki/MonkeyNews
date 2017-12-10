package uutiset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//Juuripolku
@Controller
public class MonkeyNewsController {
    
    //Palautetaan news.html sivu.
    @GetMapping("*")
    public String menu() {
        return "news";
    }
}
