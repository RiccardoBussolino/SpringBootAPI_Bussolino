package com.fabrick.bussolino.model.bonifico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DebtorModel {
    public String name;
    public AcccountModel acccount;
}
