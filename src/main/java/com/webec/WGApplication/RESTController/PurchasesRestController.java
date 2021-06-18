package com.webec.WGApplication.RESTController;


import com.webec.WGApplication.model.entity.Purchase;
import com.webec.WGApplication.service.PurchaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchasesRestController {

    private final PurchaseService service;

    public PurchasesRestController(PurchaseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Purchase> getAll(){
        return service.findAll();
    }
}
