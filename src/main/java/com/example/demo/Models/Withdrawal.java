package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Withdrawal implements Serializable {
    private int withdrawalAmount;
    private int investmentId;
    private Date dateAndTime;
}
