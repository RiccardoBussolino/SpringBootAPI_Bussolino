package com.fabrick.bussolino.request.bonifico.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InternalBonificoRequest {
    public Long accountId;
    public String receiverName;
    public String description;
    public String currency;
    public String amount;
    public String executionDate;

}
