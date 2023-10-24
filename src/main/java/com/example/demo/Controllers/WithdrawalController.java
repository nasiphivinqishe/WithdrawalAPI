package com.example.demo.Controllers;

import com.example.demo.Models.Withdrawal;
import com.example.demo.Services.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/withdrawals")
public class WithdrawalController {
    @Autowired
    private WithdrawalService withdrawalService;

    @PostMapping("/do-withdrawal")
    public Withdrawal createWithdrawal2(@RequestBody Withdrawal withdrawal) {
        Withdrawal withdrawal1 = withdrawalService.createWithdrawal(withdrawal);
        System.out.println("Returned response from service: " + withdrawal1);
        return withdrawal;
    }
}
