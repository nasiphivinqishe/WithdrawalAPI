package com.example.demo.Services;

import com.example.demo.Models.Withdrawal;
import com.example.demo.Repositories.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WithdrawalService {
    @Autowired
    private WithdrawalRepository withdrawalRepository = new WithdrawalRepository();

    public Withdrawal createWithdrawal(Withdrawal withdrawal) {
        System.out.println(withdrawal);
        try {
            // Retrieve information and perform calculations
            double withdrawalAmount = withdrawal.getWithdrawalAmount();
            int investmentId = withdrawal.getInvestmentId();
            String investmentType = withdrawalRepository.getInvestmentType(investmentId);
            int age = withdrawalRepository.getInvestorAge(investmentId);
            double currentBalance = withdrawalRepository.getCurrentBalance(investmentId);

            if ("retirement".equalsIgnoreCase(investmentType) && age <= 64) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Investor is not eligible for retirement withdrawals.");
            }
            if (withdrawalAmount > currentBalance * 0.9) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Withdrawal amount exceeds the limit. You can only withdraw up to 90% of your current balance.");
            }
            // Calculate the new balance
            double newBalance = currentBalance - withdrawalAmount;
            // Create the withdrawal and update the balance
            withdrawalRepository.createWithdrawals(withdrawal, newBalance);
            System.out.println("Check why get error"+withdrawal);
            return withdrawal;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
