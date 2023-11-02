package com.fabrick.bussolino.request.bonifico.external;

import com.fabrick.bussolino.model.bonifico.CreditorModel;
import com.fabrick.bussolino.model.bonifico.TaxReliefId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExternalBonificoRequest {
    public CreditorModel creditor;
    public LocalDate executionDate;
    public String uri;
    public String description;
    public Long amount;
    public String currency;
    public Boolean isUrgent;
    public Boolean isInstant;
    public String feeType;
    public String feeAccountId;
    public TaxReliefId taxRelief;

}
