package cn.lisp.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @author: lisp
 * @date: 2018/11/20 下午 5:47
 * @version: v1.0
 */
@RestController
public class LoginController {

    @RequestMapping("/")
    public String index(){
        return "home";
    }
}
