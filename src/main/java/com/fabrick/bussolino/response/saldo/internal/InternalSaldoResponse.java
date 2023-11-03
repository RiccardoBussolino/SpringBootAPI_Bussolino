package com.fabrick.bussolino.response.saldo.internal;

import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
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
}