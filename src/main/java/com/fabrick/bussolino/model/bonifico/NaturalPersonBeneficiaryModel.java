package com.fabrick.bussolino.model.bonifico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NaturalPersonBeneficiaryModel {
    public String fiscalCode1;
    public String fiscalCode2;
    public String fiscalCode3;
    public String fiscalCode4;
    public String fiscalCode5;
}
