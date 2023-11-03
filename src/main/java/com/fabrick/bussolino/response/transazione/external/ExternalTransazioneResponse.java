package com.fabrick.bussolino.response.transazione.external;

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
public class ExternalTransazioneResponse {
List<TransactionModel> list;
}
