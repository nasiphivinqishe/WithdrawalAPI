package com.example.demo.Controllers;

import com.example.demo.Models.Investment;
import com.example.demo.Services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/investments")
public class InvestmentController {
    @Autowired
    private InvestmentService investmentService;

    @GetMapping("/{investorId}")
    public List<Investment> getAllInvestmentsByInvestorId(@PathVariable int investorId) {
        try {
            System.out.println("Controller - ID: " + investorId);
            List<Investment> investments = investmentService.getAllInvestmentsByInvestorId(investorId);
            return investments;
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/save-investment")
    public Investment saveInvestment(@RequestParam int invesorId, @RequestBody Investment investment) {
        return investmentService.saveInvestment(invesorId, investment);
    }
}
