package com.example.exam_web.web;

import com.example.exam_web.model.binding.ShipIdModel;
import com.example.exam_web.model.view.ShipViewModel;
import com.example.exam_web.sec.CurrentUser;
import com.example.exam_web.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @GetMapping()
    public String index(Model model){
        if (currentUser.getId() == null){

            return "index";
        }

        Long id = currentUser.getId();
        List<ShipViewModel> currentUser = shipService.findAllShipWithId(id);
        List<ShipViewModel> otherUser = shipService.findOtherShip(id);

        model.addAttribute("currentUser",currentUser);
        model.addAttribute("otherUser",otherUser);
        List<ShipViewModel> allShips = shipService.findAllShips();
        model.addAttribute("allShips",allShips);

        return "home";
    }

    @PostMapping()
    public String attack(ShipIdModel shipIdModel){
      shipService.fire(shipIdModel.getIdCurrent(),shipIdModel.getIdOther());
        return "redirect:/";
    }

    @ModelAttribute
    public ShipIdModel shipIdModel(){
        return new ShipIdModel();
    }
}
