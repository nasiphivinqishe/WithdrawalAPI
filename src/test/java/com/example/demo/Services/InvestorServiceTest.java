package com.example.demo.Services;

import com.example.demo.Models.Investor;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest

public class InvestorServiceTest extends TestCase {
    @Autowired
    private InvestorService investorService = new InvestorService();

    @Test
    public void testSaveInvestor() throws Exception {

        // Create a sample investor
        Investor investors = new Investor();
        investors.setName("Uminathi");
        investors.setSurname("Vinqishe");
        String dobString = "2017-01-20"; // Replace with your desired date string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = dateFormat.parse(dobString);
        investors.setDob(dob);
        investors.setAddress("Mthatha");
        investors.setMobileNumber(2098773473);
        investors.setEmail("umi@vinqi.com");

        // Call the saveInvestors method on the injected controller
        int savedInvestor = investorService.saveInvestors(investors);
        System.out.println(savedInvestor);
    }

    @Test
    public void getInvestorById() throws Exception {
        int id = 4444;

        Investor investors = investorService.getInvestorById(id);

    }
}


