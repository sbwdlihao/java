package com.lihao.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bencong.lh
 * @version $Id: HomeController, v0.1 2017年04月07日 上午10:33 bencong.lh Exp $
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
