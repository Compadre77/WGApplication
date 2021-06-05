package com.webec.WGApplication.controller;


import com.webec.WGApplication.service.BillService;
import com.webec.WGApplication.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Controller
public class BillController {
    private final BillService service;
    private final UserService userService;

    public BillController(BillService service, UserService userService) { this.service = service;
        this.userService = userService;
    }
    @GetMapping("/finanzen")
    public String bills(Model model) {
        model.addAttribute("allBills", service.getAllBills());
        model.addAttribute("billCount", service.getAllBills().size());
        model.addAttribute("users", userService.getAllUsers());
        return "bills";
    }
    @PostMapping("/finanzen")
    public String bills(
            @RequestParam @NotBlank String description,
            @RequestParam @NotBlank double amount,
            @RequestParam @NotBlank int[] userIDs,
            @RequestParam boolean isFix) {

        service.add(
                description.strip(),
                amount,
                userIDs,
                isFix
        );
        return "redirect:/finanzen";
    }


    @PostMapping("/finanzen/delete/{id}")
    public String deleteContact(@PathVariable int id) {
        var bill = service.findBill(id).orElseThrow(BillNotFound::new);
        service.delete(bill);
        return "redirect:/finanzen";
    }


    @ExceptionHandler(BillNotFound.class)
    @ResponseStatus(NOT_FOUND)
    public String notFound(Model model) {
        model.addAttribute("bills", service.getAllBills());
        model.addAttribute("errorMessage", "Bill not found");
        return "finanzen";
    }
    private static class BillNotFound extends RuntimeException {}

}
