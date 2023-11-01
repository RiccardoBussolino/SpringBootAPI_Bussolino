package com.axerve.bussolino.request;

import com.axerve.bussolino.model.bonifico.CreditorModel;
import com.axerve.bussolino.model.bonifico.LegalPersonBeneficiaryModel;
import com.axerve.bussolino.model.bonifico.TaxReliefModel;

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
