package com.fabrick.bussolino.response.transazione;

import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.ResponseConverter;
import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
import com.fabrick.bussolino.response.transazione.internal.InternalTransazioneResponse;
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
public class TransazioneResponseConverter implements ResponseConverter<InternalTransazioneResponse, ExternalTransazioneResponse> {
    JsonResponse<ExternalTransazioneResponse> resp;

    @Override
    public JsonResponse<InternalTransazioneResponse> convertResponse() {
        JsonResponse<InternalTransazioneResponse> response;
        if (this.resp != null) {
            response = new JsonResponse<>(resp.getStatus(), resp.getError(), new InternalTransazioneResponse());
            if (resp.getPayload() != null) {
                response.getPayload().setList(resp.getPayload().getList());
            }
        } else {
            response = new JsonResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, Arrays.asList("Errore sconosciuto - non è stato possibile recuperare l'informazione richiesta"), null);
        }
        return response;
    }
}

