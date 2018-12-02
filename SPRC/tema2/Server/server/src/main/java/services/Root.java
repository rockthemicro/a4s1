package services;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Root {

    @RequestMapping("/")
    public String hello() {
        return "Hello! How can we help you travel slower ? :D";
    }
}
