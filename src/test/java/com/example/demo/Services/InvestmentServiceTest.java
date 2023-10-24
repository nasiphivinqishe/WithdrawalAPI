package com.example.demo.Services;

import com.example.demo.Constants.ProductType;
import com.example.demo.Models.Investment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InvestmentServiceTest {
    @Autowired
    private InvestmentService investmentService = new InvestmentService();


    @Test
    public void testSaveInvestment() {
        Investment investment = new Investment();
        investment.setInvestorId(2222);
        investment.setProductType(ProductType.SAVINGS);
        investment.setCurrentBalance(10000);

        int investorId = investment.getInvestorId();

        Investment savedInvestment = investmentService.saveInvestment(investorId, investment);

        System.out.println(savedInvestment);
    }

    @Test
    public void testGetAllInvestmentsByInvestorId() {
        int investorId = 1;

        List<Investment> investments = investmentService.getAllInvestmentsByInvestorId(investorId);

        // You should add assertions here to verify the results.
    }
}
