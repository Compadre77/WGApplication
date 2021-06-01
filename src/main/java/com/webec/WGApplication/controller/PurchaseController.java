package com.webec.WGApplication.controller;

import com.webec.WGApplication.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@Controller
public class PurchaseController {
    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {this.service = service; }

    @GetMapping("/einkauf")
    public String purchases(Model model){
        model.addAttribute("allPurchases", service.getAllPurchases());
        model.addAttribute("purchaseCount", service.getAllPurchases().size());
        return "purchases";
    }

    @PostMapping("/einkauf")
    public int purchases(@RequestParam @NotBlank String description,
                         @RequestParam boolean checked){

        var created = service.add(
                description.strip(),
                checked);
        System.out.println(created);
        return created.getId();
    }
}