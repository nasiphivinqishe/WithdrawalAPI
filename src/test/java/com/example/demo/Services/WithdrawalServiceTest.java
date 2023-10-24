package com.example.demo.Services;

import com.example.demo.Models.Withdrawal;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class WithdrawalServiceTest {

    @Autowired
    private WithdrawalService withdrawalService = new WithdrawalService();

    @Test
    public void createWithdrawal() {
        Withdrawal withdrawal = new Withdrawal();

        withdrawal.setWithdrawalAmount(1);
        withdrawal.setInvestmentId(4);
        withdrawal.setDateAndTime(new Date());

        Withdrawal savedWithdrawal = withdrawalService.createWithdrawal(withdrawal);

        System.out.println(savedWithdrawal);
    }
}
