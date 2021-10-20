package com.example.exam.web;

import com.example.exam.model.binding.UserLoginBindingModel;
import com.example.exam.model.binding.UserRegisterBindingModel;
import com.example.exam.model.service.UserServiceModel;
import com.example.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        boolean password = userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword());
        boolean freeName = userService.userNameFree(userRegisterBindingModel.getUsername());

        if (bindingResult.hasErrors() || !freeName || !password){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);

            return "redirect:register";
        }
        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("isFound")){
            model.addAttribute("isFound",true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors() || userService.userNameFree(userLoginBindingModel.getUsername())){
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",bindingResult);
            if (userService.userNameFree(userLoginBindingModel.getUsername())){
               redirectAttributes.addFlashAttribute("isFound",false);
            }
            return "redirect:login";
        }
        UserServiceModel userServiceModel = userService.findFyUsernameAndPassword(userLoginBindingModel.getUsername(),userLoginBindingModel.getPassword());
        userService.loginUser(userServiceModel.getUsername(),userServiceModel.getId());
        return "redirect:/";
    }

    @GetMapping("/logout")
    private String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

}
