package com.fabrick.bussolino.model.bonifico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AmountModel {
   public Long debtorAmount;
   public String debtorCurrency;
   public Long creditorAmount;
   public String creditorCurrency;
   public Date creditorCurrencyDate;
   public Long exchangeRate;
}
