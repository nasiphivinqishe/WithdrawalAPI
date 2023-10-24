package com.example.demo.Models;

import com.example.demo.Constants.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Investment implements Serializable {
    private int investorId;
    private ProductType productType;
    private int currentBalance;


}
