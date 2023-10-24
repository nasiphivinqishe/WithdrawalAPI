package com.example.demo.Services;

import com.example.demo.Constants.ProductType;
import com.example.demo.Models.Investment;
import com.example.demo.Models.Investor;
import com.example.demo.Repositories.InvestmentRepository;
import com.example.demo.Repositories.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatabaseSeeder implements ApplicationRunner {
    @Autowired
    private InvestorRepository investorRepository;
    @Autowired
    private InvestmentRepository investmentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Starting seeding..........");
        // Seed investors
        String dobString = "2017-01-20"; // Replace with your desired date string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = dateFormat.parse(dobString);
        Investor investor1 = new Investor("John", "Doe", dob, "123 Main St", 1234567890, "john@example.com");
        int investorId1 = investorRepository.saveInvestor(investor1);

        // Seed investments
        Investment investment1 = new Investment(investorId1, ProductType.SAVINGS, 50000);
        investmentRepository.saveInvestment(investorId1, investment1);

        Investment investment2 = new Investment(investorId1, ProductType.RETIREMENT, 36000);
        investmentRepository.saveInvestment(investorId1, investment2);
    }
}
