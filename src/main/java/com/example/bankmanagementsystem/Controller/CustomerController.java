package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/bank-management")
public class CustomerController {
    ArrayList<Customers> customers = new ArrayList<>();

    //Read
    @GetMapping("/get")
    public ArrayList<Customers> getCustomers() {
        return customers;
    }

    //Add
    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customers customers) {
        this.customers.add(customers);
        return new ApiResponse("Successfully added customer");
    }

    //Update
    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customers customers) {
        this.customers.set(index, customers);
        return new ApiResponse("Successfully updated customer");
    }

    //Delete
    @DeleteMapping("delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        this.customers.remove(index);
        return new ApiResponse("Successfully deleted customer");
    }

    //Deposit
    @PutMapping("/deposit/{id}/{amount}")
    public ApiResponse depositMoney(@PathVariable int id, @PathVariable double amount) {
        for (Customers customer : customers) {
            if (customer.getId() == id) {
                customer.setBalance(customer.getBalance() + amount);
                return new ApiResponse("Money deposited successfully");
            }
        }
        return new ApiResponse("Customer not found");
    }
    //withdraw
    @PutMapping("/withdraw/{id}/{amount}")
    public ApiResponse withdrawMoney(@PathVariable int id, @PathVariable double amount) {
        for (Customers customer : customers) {
            if (customer.getId() == id) {
                if (customer.getBalance() >= amount) {
                    customer.setBalance(customer.getBalance() - amount);
                    return new ApiResponse("Money withdrawn successfully");
                } else {
                    return new ApiResponse("Insufficient balance");
                }
            }
        }
        return new ApiResponse("Customer not found");
    }
}
