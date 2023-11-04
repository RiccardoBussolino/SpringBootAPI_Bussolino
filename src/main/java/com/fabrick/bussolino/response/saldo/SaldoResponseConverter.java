package com.fabrick.bussolino.response.saldo;

import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.ResponseConverter;
import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.saldo.internal.InternalSaldoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaldoResponseConverter implements ResponseConverter<InternalSaldoResponse, ExternalSaldoResponse> {
    JsonResponse<ExternalSaldoResponse> resp;

    public JsonResponse<InternalSaldoResponse> convertResponse() {
        JsonResponse<InternalSaldoResponse> response;
        if (this.resp != null) {
            response = new JsonResponse<>(resp.getStatus(), resp.getError(), new InternalSaldoResponse());
            if (resp.getPayload() != null) {
                response.getPayload().setSaldo(resp.getPayload().getBalance());
            }
        } else {
            response = new JsonResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, Arrays.asList("Errore sconosciuto - non è stato possibile recuperare l'informazione richiesta"), new InternalSaldoResponse());
        }
        return response;
    }
}
