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
    public InternalBonificoResponse(ExternalBonificoResponse resp,Long accountId) {
       if("200".equalsIgnoreCase(resp.getStatus().toString())){
           this.code ="API999";// codice per OK?
           this.description=resp.getPayload().toString();
        }
       else {
           this.code="API000";
           this.description="Errore tecnico  La condizione BP049 non e' prevista per il conto id ".concat(accountId.toString());
       }
    }
}
