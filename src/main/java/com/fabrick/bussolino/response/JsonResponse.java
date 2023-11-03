package com.fabrick.bussolino.response;

import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JsonResponse<T> {
    private HttpStatus status;
    private List<String> error;
    private T payload;

}
