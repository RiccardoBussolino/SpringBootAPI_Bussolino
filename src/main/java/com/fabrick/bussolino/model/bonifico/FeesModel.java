package com.fabrick.bussolino.model.bonifico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FeesModel {
   public  String feeCode;
   public String descritpion;
   public String amount;
   public String currency;
}
