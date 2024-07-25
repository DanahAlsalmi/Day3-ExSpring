package com.example.bankmanagementsystem.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customers {
    private int id;
    private String username;
    private double balance;
}
