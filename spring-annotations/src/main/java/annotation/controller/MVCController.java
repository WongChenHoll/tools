package annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author WangChenHol
 * @date 2021-9-15 14:25
 **/
@RequestMapping("/mvc")
@Controller
public class MVCController {

    @GetMapping("/test")
    public ModelAndView test() {
        return new ModelAndView("index", "riqi", new Date());
    }

}
