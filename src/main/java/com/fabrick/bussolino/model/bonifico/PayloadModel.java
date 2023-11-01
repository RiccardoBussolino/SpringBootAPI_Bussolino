package com.fabrick.bussolino.model.bonifico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PayloadModel {

    public String moneyTransferId;
    public String status;
    public String direction;
    public CreditorModel creditor;
    public DebtorModel debtor;
    public String cro;
    public String uri;
    public String trn;
    public String description;
    public LocalDateTime createdDatetime;
    public LocalDateTime accountedDatetime;
    public Date debtorValueDate;
    public Date creditorValueDate;
    public AmountModel amount;
    public Boolean isUrgent;
    public Boolean isInstant;
    public String feeType;
    public String feeAccountId;
    public FeesModel[] fees;
    public Boolean hasTaxRelief;
}
