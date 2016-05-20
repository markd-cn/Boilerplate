package net.boblog.app.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by dave on 16/5/16.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public HashMap<String, Object> index(HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("sid", session.getId());
        return map;
    }
}
