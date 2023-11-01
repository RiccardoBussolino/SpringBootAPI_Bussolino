package com.fabrick.bussolino.model.saldo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PayloadModel {
    public Date date;
    public String balance;
    public String availableBalance;
    public String currency;
}
