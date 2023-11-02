package com.fabrick.bussolino.model.bonifico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TaxReliefId {
   public String taxReliefId;
   public Boolean isCondoUpgrade;
   public String creditorFiscalCode;
   public String beneficiaryType;
   public NaturalPersonBeneficiaryModel naturalPersonBeneficiary;
   public LegalPersonBeneficiaryModel legalPersonBeneficiary;
}
