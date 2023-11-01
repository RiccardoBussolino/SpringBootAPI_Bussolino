package com.fabrick.bussolino.request;

import com.fabrick.bussolino.model.bonifico.CreditorModel;
import com.fabrick.bussolino.model.bonifico.LegalPersonBeneficiaryModel;
import com.fabrick.bussolino.model.bonifico.TaxReliefModel;

import java.util.Date;

public class ExternalBonificoRequest {
    public CreditorModel creditor;
    public Date executionDate;
    public String uri;
    public String description;
    public Long amount;
    public String currency;
    public Boolean isUrgent;
    public Boolean isInstant;
    public String feeType;
    public String feeAccountId;
    public TaxReliefModel taxRelief;
    public LegalPersonBeneficiaryModel legalPersonBeneficiary;
}
