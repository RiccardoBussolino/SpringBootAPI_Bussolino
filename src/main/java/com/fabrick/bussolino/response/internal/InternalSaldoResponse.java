package com.fabrick.bussolino.response.internal;

import com.fabrick.bussolino.response.external.ExternalSaldoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InternalSaldoResponse {
    public String saldo;

    public InternalSaldoResponse(ExternalSaldoResponse externalSaldoResponse) {
        this.saldo = externalSaldoResponse.getPayload().getBalance();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class BonificoRequest {
       public Long accountId;
       public String receiverName;
       public String description;
       public String currency;
       public String amount;
       public String executionDate;

    }
}