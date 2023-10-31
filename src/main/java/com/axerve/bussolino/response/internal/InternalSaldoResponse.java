package com.axerve.bussolino.response.internal;

import com.axerve.bussolino.response.external.ExternalSaldoResponse;
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