package com.example.demo.Services;

import com.example.demo.Models.Investment;
import com.example.demo.Repositories.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {
    @Autowired
    private InvestmentRepository investmentRepository = new InvestmentRepository();

    public Investment saveInvestment(int investorId, Investment investment) {
        System.out.println(investment);
        System.out.println(investorId);
        return investmentRepository.saveInvestment(investorId, investment);
    }


    public List<Investment> getAllInvestmentsByInvestorId(int investorId) {
        return investmentRepository.getAllInvestmentsByInvestorId(investorId);
    }
}
