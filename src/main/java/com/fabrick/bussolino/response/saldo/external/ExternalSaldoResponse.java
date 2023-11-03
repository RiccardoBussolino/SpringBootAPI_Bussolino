package com.fabrick.bussolino.response.saldo.external;

import com.fabrick.bussolino.model.saldo.PayloadModel;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExternalSaldoResponse {
    private HttpStatus status;
    private List<String> error;
    private PayloadModel payload;
}
