package com.BillDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/bills/")
public class Controller {
    private final BillService billService;

    @Autowired
    public Controller(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<Bill> getBills() {
        return billService.getBills();
    }

    @PostMapping("/add")
    public void addBills(@RequestBody Bill bill) {
        System.out.println("Received");
        billService.addBill(bill);
    }
}
