package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Investor implements Serializable {
    private String name;
    private String surname;
    private Date dob;
    private String address;
    private int mobileNumber;
    private String email;
}
