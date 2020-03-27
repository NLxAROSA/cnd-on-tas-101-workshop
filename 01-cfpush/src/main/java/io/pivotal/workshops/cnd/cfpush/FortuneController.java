package io.pivotal.workshops.cnd.cfpush;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FortuneController  {

    @GetMapping
    public String getFortune()  {
        return "Your future is bright!";
    }

}