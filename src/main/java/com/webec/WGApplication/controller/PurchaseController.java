package com.webec.WGApplication.controller;

import com.webec.WGApplication.service.PurchaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
    public String purchases(@RequestParam int amount,
                         @RequestParam @NotBlank String description,
                         @RequestParam boolean checked){

        service.add(
                amount,
                description.strip(),
                checked);
        return "redirect:/einkauf";
    }

    @PostMapping("/einkauf/löschen/{id}")
    public String deletePurchase(@PathVariable int id){
        var purchase = service.findPurchase(id).orElseThrow(PurchaseNotFound::new);
        service.delete(purchase);
        return "redirect:/einkauf";
    }

    @ExceptionHandler(PurchaseController.PurchaseNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("purchases", service.getAllPurchases());
        model.addAttribute("errorMessage", "Purchase not found");
        return "einkauf";
    }
    private static class PurchaseNotFound extends RuntimeException {}
}