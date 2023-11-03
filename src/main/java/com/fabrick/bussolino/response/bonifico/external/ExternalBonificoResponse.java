package com.fabrick.bussolino.response.bonifico.external;

import com.fabrick.bussolino.model.bonifico.AmountModel;
import com.fabrick.bussolino.model.bonifico.CreditorModel;
import com.fabrick.bussolino.model.bonifico.DebtorModel;
import com.fabrick.bussolino.model.bonifico.FeesModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalBonificoResponse {

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
    public List<FeesModel> fees;
    public Boolean hasTaxRelief;
}
