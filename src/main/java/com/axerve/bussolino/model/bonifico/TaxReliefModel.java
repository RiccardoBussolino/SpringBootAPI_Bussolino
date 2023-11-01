package com.axerve.bussolino.model.bonifico;

import com.axerve.bussolino.model.bonifico.NaturalPersonBeneficiaryModel;
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
