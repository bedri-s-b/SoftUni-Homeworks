package com.example.exam12august2020.web;

import com.example.exam12august2020.model.binding.TaskAddBindingModel;
import com.example.exam12august2020.model.service.TaskServiceModel;
import com.example.exam12august2020.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add(){
        return "add-task";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid TaskAddBindingModel taskAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("taskAddBindingModel",taskAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel",bindingResult);
            return "redirect:add";
        }
        taskService.addTask(modelMapper.map(taskAddBindingModel, TaskServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/progress/{id}")
    public String progress(@PathVariable Long id){

        taskService.changeProgress(id);
        return "redirect:/";
    }

    @ModelAttribute
    public TaskAddBindingModel taskAddBindingModel(){
        return new TaskAddBindingModel();
    }
}
