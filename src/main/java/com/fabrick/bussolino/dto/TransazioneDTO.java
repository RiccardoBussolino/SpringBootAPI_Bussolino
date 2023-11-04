package com.fabrick.bussolino.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "transazioni")
public class TransazioneDTO {
    @Id
    @Column(name = "transaction_id", nullable = false)
    private String transactionId;
    @Column(name = "operation_id", nullable = false)
    private String operationId;
    @Column(name = "accounting_date", nullable = false)
    private Date accountingDate;
    @Column(name = "value_date", nullable = false)
    private Date valueDate;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "amount", nullable = false)
    private double amount;
    @Column(name = "currency", nullable = false)
    private String currency;
    @Column(name = "description", nullable = false)
    private String description;
}
