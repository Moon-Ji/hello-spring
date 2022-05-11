package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Controller 찾고 없으면 html 파일 찾음(index.html 이 있어도 home.html 이 우선)
    @GetMapping("/")
    public String home() {
        return "home";
    }

}
