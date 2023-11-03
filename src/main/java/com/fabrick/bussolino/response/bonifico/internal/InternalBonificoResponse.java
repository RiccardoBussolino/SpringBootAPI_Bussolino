package com.fabrick.bussolino.response.bonifico.internal;

import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import io.micrometer.common.util.StringUtils;
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
    public InternalBonificoResponse(ExternalBonificoResponse resp, Long accountId) {
       if(StringUtils.isNotBlank(resp.getMoneyTransferId())){
           this.code ="API999";// codice per OK?
           this.description=resp.toString();
        }
       else {
           this.code="API000";
           this.description="Errore tecnico  La condizione BP049 non e' prevista per il conto id ".concat(accountId.toString());
       }
    }
}
