package com.fabrick.bussolino.response.transazione.internal;

import com.fabrick.bussolino.model.transazione.TransactionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InternalTransazioneResponse {
    List<TransactionModel> list;
}
