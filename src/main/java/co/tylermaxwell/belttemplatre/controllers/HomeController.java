package co.tylermaxwell.belttemplatre.controllers;

import co.tylermaxwell.belttemplatre.models.LoginUser;
import co.tylermaxwell.belttemplatre.models.User;
import co.tylermaxwell.belttemplatre.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    // Add once service is implemented:
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model) {

        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {
        System.out.println(newUser);
        // TO-DO Later -- call a register method in the service
        // to do some extra validations and create a new user!
        User user = userService.register(newUser, result);
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", user.getId());
        session.setAttribute("user", user);

        return "redirect:/home";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {

        // Add once service is implemented:
        User user = userService.login(newLogin, result);

        if(result.hasErrors()|| user == null) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", user.getId());
        session.setAttribute("user", user);

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(HttpSession session){
        if(session.getAttribute("userId") == null){
            return "redirect:/logout";
        }
        return "home.jsp";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


}
