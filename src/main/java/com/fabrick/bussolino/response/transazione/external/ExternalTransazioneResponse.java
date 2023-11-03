package com.fabrick.bussolino.response.transazione.external;

import com.fabrick.bussolino.model.transazione.PayloadModel;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalTransazioneResponse {
    private HttpStatus status;
    private List<String> error;
    private PayloadModel payload;
}
