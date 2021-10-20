package com.example.exam.web;

import com.example.exam.model.view.AlbumViewModel;
import com.example.exam.sec.CurrentUser;
import com.example.exam.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final AlbumService albumService;

    public HomeController(CurrentUser currentUser, AlbumService albumService) {
        this.currentUser = currentUser;
        this.albumService = albumService;
    }

    @GetMapping()
    public String index(Model model){
        if (currentUser.getId() == null){
            return "index";
        }

        List<AlbumViewModel> allAlbums = albumService.findAllAlbums();
        model.addAttribute("allAlbums",allAlbums);

        BigDecimal totalSum = allAlbums.stream().map(albums -> {
            return albums.getPrice().multiply(BigDecimal.valueOf(albums.getCopies()));
        }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

        model.addAttribute("totalSum",totalSum);

        return "home";
    }
}
