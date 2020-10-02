package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.app.entity.User;
import web.app.service.LoginService;
import web.app.service.impl.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    LoginService userDetailService;

    @RequestMapping("/users")
    public String get(Principal user, Model model)
    {
        List<User> users = userService.getModUsers(user.getName());
        model.addAttribute("users", users);
        return "users";
    }
    @PostMapping("/password")
    String changePassword(@RequestParam("oldPassword") String oldPass,@RequestParam("newPassword") String newPass, Principal userPrincipal, Model model)
    {
        String  username = userPrincipal.getName();
        User user =userService.get(username);
        // TODO: 9/25/2020  changePassword
        if(user.getPassword().equals(oldPass))
        {
            userService.changePassword(username, newPass);
            //update password
            return "redirect:/admin";
        }else{
            //throw error
            model.addAttribute("isWrongPassword", true);
            return "password";
        }

    }

    @GetMapping("/password")
    public String getPassword(Model model)
    {
        model.addAttribute("isWrongPassword", false);
        return "password";
    }

}
