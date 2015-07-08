package ololo.controller;

import ololo.service.ForSQL;
import ololo.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String start(Model model){
        return "index";
    }

    @RequestMapping(value = "/signup/{login}/{pass}")
    public ModelAndView signp(@PathVariable String login,@PathVariable String pass) {
        ModelAndView c=new ModelAndView();
        if (new UserServiceImpl().getUser(login)!=null) {
            c.setViewName("");
        return null;
        }
         new ForSQL().querySQL("Insert into users (email,pass) Values('"+login+"','"+pass+"'");
        String b = "/j_spring_security_check";
        c.setViewName(b);
        return c;
    }
}
