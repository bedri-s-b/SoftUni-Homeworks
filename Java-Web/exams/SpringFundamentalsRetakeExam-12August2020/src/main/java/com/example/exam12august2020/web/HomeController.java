package com.example.exam12august2020.web;

import com.example.exam12august2020.model.view.TaskViewModel;
import com.example.exam12august2020.sec.CurrentUser;
import com.example.exam12august2020.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final TaskService taskService;

    public HomeController(CurrentUser currentUser, TaskService taskService) {
        this.currentUser = currentUser;
        this.taskService = taskService;
    }

    @GetMapping()
    public String index(Model model){
        if (currentUser.getId() == null){

            return "index";
        }

        List<TaskViewModel> tasks = taskService.findAllTaskOrderDate();
        model.addAttribute("tasks",tasks);
        return "home";
    }
}
