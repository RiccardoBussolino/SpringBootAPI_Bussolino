package com.fabrick.bussolino.response.transazione.external;

import com.fabrick.bussolino.model.transazione.TransactionModel;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalTransazioneResponse {
    List<TransactionModel> list;
}
