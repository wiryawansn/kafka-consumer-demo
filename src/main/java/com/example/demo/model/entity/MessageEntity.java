package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "LOG_TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UUID")
    private String uuid;
    @Column(name = "REFERRAL_NUMBER")
    private String referralNumber;
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "TERMINAL_CODE")
    private String terminalId;

    @Column(name = "PROCESSED_TIME")
    private LocalDateTime processedTimestamp;

    public MessageEntity(String uuid, String referralNumber, Double amount, String currency, String terminalId) {
        this.uuid = uuid;
        this.referralNumber = referralNumber;
        this.amount = amount;
        this.currency = currency;
        this.terminalId = terminalId;
        this.processedTimestamp = LocalDateTime.now();
    }
}