package pl.sda.meetup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.meetup.dto.UserLoginDto;
import pl.sda.meetup.services.UserServiceImpl;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());
        return "registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userLoginDto") @Valid UserLoginDto userLoginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registerForm";
        }
        log.info(userLoginDto.getPassword());
        log.info(userLoginDto.getEmail());
        log.info(userLoginDto.getName());

        UserLoginDto savedUser = userService.saveUser(userLoginDto);
        return "redirect:/";
    }


    @GetMapping("/log")
    public String showLoginForm() {
        return "loginForm";
    }

}
