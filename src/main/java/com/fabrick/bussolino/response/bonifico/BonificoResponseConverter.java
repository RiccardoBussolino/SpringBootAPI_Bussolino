package com.fabrick.bussolino.response.bonifico;

import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.ResponseConverter;
import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import com.fabrick.bussolino.response.bonifico.internal.InternalBonificoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BonificoResponseConverter implements ResponseConverter<InternalBonificoResponse, ExternalBonificoResponse> {
    JsonResponse<ExternalBonificoResponse> response;
    Long accountId;
    public JsonResponse<InternalBonificoResponse> convertResponse() {
        return new JsonResponse<>(response.getStatus(), response.getError(), new InternalBonificoResponse(response.getPayload(), accountId));
    }
}
