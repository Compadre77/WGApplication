package com.webec.WGApplication.RESTController;


import com.webec.WGApplication.model.entity.Bill;
import com.webec.WGApplication.service.BillService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillsRestController {

    private final BillService service;

    public BillsRestController(BillService service){
        this.service = service;
    }

    @GetMapping
    public List<Bill> getAll(){
        return service.findAll();
    }

}
