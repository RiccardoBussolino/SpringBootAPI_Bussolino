package com.fabrick.bussolino.response.external;

import com.fabrick.bussolino.model.saldo.PayloadModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ExternalSaldoResponse {
    private HttpStatus status;
    private List<String> error;
    private PayloadModel payload;
}
