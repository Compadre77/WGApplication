package com.webec.WGApplication.controller;

import com.webec.WGApplication.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
