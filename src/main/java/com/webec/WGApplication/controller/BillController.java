package com.webec.WGApplication.controller;


import com.webec.WGApplication.model.enums.BillStatus;
import com.webec.WGApplication.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.List;



@Controller
public class BillController {
    private final BillService service;

    public BillController(BillService service) { this.service = service; }
    @GetMapping("/finanzen")
    public String bills(Model model) {
        model.addAttribute("allBills", service.getAllBills());
        model.addAttribute("billCount", service.getAllBills().size());
        return "bills";
    }
    @PostMapping("/finanzen")
    public int bills(@RequestParam @NotBlank String description,
                       @RequestParam @NotBlank double amount,
                       @RequestParam int userIDs,
                       @RequestParam boolean isFix) {

        var created = service.add(
                description.strip(),
                amount,
                userIDs,
                isFix);
        System.out.println(created);

        return created.getId();
    }

}
