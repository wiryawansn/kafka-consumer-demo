package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageWrapper {
    private String uuid;
    private String referralNumber; // Keep as String since it's a JSON string
    private Double amount;
    private String currency;
    private String terminalId;
}