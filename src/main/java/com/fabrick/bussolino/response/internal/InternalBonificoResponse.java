package com.fabrick.bussolino.response.internal;

import com.fabrick.bussolino.model.bonifico.AcccountModel;
import com.fabrick.bussolino.response.external.ExternalBonificoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InternalBonificoResponse {
    String code;
    String description;
    public InternalBonificoResponse(ExternalBonificoResponse resp) {
    }
}
