package com.fabrick.bussolino.model.bonifico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LegalPersonBeneficiaryModel {
    public String fiscalCode;
    public String legalRepresentativeFiscalCode;
}
