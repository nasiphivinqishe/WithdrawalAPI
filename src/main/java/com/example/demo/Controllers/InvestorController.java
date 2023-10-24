package com.example.demo.Controllers;

import com.example.demo.Models.Investor;
import com.example.demo.Services.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("investors")

public class InvestorController {
    @Autowired
    InvestorService investorService;

    @PostMapping("/save")
    public int saveInvestors(Investor results) throws Exception {
        return investorService.saveInvestors(results);
    }

    @GetMapping("/all")
    public List<Investor> getAllInvestors() throws Exception {
        return investorService.getAllInvestors();
    }

    @GetMapping("/{id}")
    public Investor getInvestorById(@PathVariable int id) {
        try {
            System.out.println("Controller - ID: " + id);
            Investor investor = investorService.getInvestorById(id);

            return investor;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);

        }
    }
}
