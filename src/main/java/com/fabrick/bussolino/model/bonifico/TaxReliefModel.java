package com.fabrick.bussolino.model.bonifico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TaxReliefModel {
   public String taxDeliefId;
   public Boolean isCondoUpgrade;
   public String creditorFiscalCode;
   public String beneficiaryType;
   public NaturalPersonBeneficiaryModel naturalPersonBeneficiary;
}
