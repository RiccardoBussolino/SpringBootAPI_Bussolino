package com.fabrick.bussolino.response.saldo;

import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.ResponseConverter;
import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.saldo.internal.InternalSaldoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaldoResponseConverter implements ResponseConverter<InternalSaldoResponse, ExternalSaldoResponse> {
    JsonResponse<ExternalSaldoResponse> resp;
    public JsonResponse<InternalSaldoResponse> convertResponse() {
        return new JsonResponse<>(resp.getStatus(), resp.getError(), new InternalSaldoResponse(resp.getPayload().getBalance()));
    }
}
