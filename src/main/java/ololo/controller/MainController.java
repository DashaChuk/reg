package ololo.controller;

import ololo.entity.User;
import ololo.service.ForSQL;
import ololo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String start(Model model){
        return "index";
    }

    @RequestMapping(value="/logup",method = RequestMethod.GET)
    public String stamjrt(Model model){
        return "logup";
    }

    @RequestMapping(value = "/logup",method=RequestMethod.POST)
    public ModelAndView signp(HttpServletRequest req) {
        ModelAndView c=new ModelAndView();
        if (new UserServiceImpl().getUser(req.getParameter("j_username"))!=null) {
            c.setViewName("/logup");
        return c;
        }
         new ForSQL().querySQL("Insert into users (email,pass) Values('"+req.getParameter("j_username")+"','"+req.getParameter("j_password")+"')");
        User us=new UserServiceImpl().getUser(req.getParameter("j_username"));
        authenticateUserAndSetSession(us, req);
        String b = "redirect:/";
        c.setViewName(b);
        return c;
    }@Autowired
    protected AuthenticationManager authenticationManager;
    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getLogin();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
