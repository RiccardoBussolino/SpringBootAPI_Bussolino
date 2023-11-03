package com.fabrick.bussolino.model.transazione;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionModel {
    String transactionId;
    String operationId;
    Date accountingDate;
    Date valueDate;
    TransactionType type;
    double amount;
    String currency;
    String description;
}
