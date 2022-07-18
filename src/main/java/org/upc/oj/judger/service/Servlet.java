package org.upc.oj.judger.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class Servlet  {
    @RequestMapping(value="sayHello.action" ,method = {RequestMethod.GET} ,
            params = {"username=123"}
    )

    public ModelAndView sayHello(){
        ModelAndView mv = new ModelAndView("main");
        mv.setViewName("main");
        mv.addObject("uname","ssss");



        return  mv;
    }
    @RequestMapping(value="saybye.action"

    )
    public String sabye(HttpServletRequest request, HttpSession session){
        //ModelAndView mv = new ModelAndView("main");
        //mv.setViewName("main");
        //mv.addObject("uname","ssss");
        session.setAttribute("nike","for");


        return "forward:sayHello.action";

    }
}
