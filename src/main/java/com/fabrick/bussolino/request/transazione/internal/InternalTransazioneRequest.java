package com.fabrick.bussolino.request.transazione.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InternalTransazioneRequest {
   public Long accountId;
   public String fromAccountingDate;
   public String toAccountingDate;
}