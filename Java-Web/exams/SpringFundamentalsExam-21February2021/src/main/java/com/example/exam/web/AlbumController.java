package com.example.exam.web;

import com.example.exam.model.binding.AlbumAddBindingModel;
import com.example.exam.model.service.AlbumServiceModel;
import com.example.exam.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addAlbum(){
        return "add-album";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AlbumAddBindingModel albumAddBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("albumAddBindingModel",albumAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel",bindingResult);

            return "redirect:addAlbum";
        }

        albumService.addAlbum(modelMapper.map(albumAddBindingModel,AlbumServiceModel.class));

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    private String delete(@PathVariable Long id){
        albumService.deleteAlbum(id);
        return "redirect:/";
    }

    @ModelAttribute
    private AlbumAddBindingModel albumAddBindingModel(){
        return new AlbumAddBindingModel();
    }
}
