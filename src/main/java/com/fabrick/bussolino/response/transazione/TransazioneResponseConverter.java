package com.fabrick.bussolino.response.transazione;

import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.ResponseConverter;
import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
import com.fabrick.bussolino.response.transazione.internal.InternalTransazioneResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransazioneResponseConverter implements ResponseConverter<InternalTransazioneResponse, ExternalTransazioneResponse> {
    JsonResponse<ExternalTransazioneResponse> response;

    @Override
    public JsonResponse<InternalTransazioneResponse> convertResponse() {
        return new JsonResponse<>(response.getStatus(), response.getError(), new InternalTransazioneResponse(response.getPayload().getList()));
    }
}
