package com.example.demo.Services;

import com.example.demo.Models.Investor;
import com.example.demo.Repositories.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestorService {
    @Autowired
    private InvestorRepository investorRepository = new InvestorRepository();

    public int saveInvestors(Investor investors) throws Exception {
        System.out.println(investors);
        return investorRepository.saveInvestor(investors);
    }

    public List<Investor> getAllInvestors() throws Exception {
        return investorRepository.getAllInvestors();
    }

    public Investor getInvestorById(int id) {
        System.out.println("Service - ID: " + id);
        return investorRepository.getInvestorById(id);
    }
}
