package com.webec.WGApplication.controller;

import com.webec.WGApplication.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseController {
    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {this.service = service; }
    @GetMapping("/einkauf")
    public String purchases(Model model){
        model.addAttribute("allPurchases", service.getAllPurchases());
        model.addAttribute("pruchaseCount", service.getAllPurchases().size());
        return "purchases";
    }
}