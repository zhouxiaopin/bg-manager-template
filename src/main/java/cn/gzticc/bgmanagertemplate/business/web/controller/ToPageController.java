package cn.gzticc.bgmanagertemplate.business.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RestController
@RequestMapping("/page")
public class ToPageController {
    @GetMapping("/myDesktop")
    public ModelAndView myDesktop(ModelAndView mv){
        mv.setViewName("myDesktop");
        return mv;
    }
}
