package com.fabrick.bussolino.response.external;

import com.fabrick.bussolino.model.saldo.PayloadModel;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ExternalBonificoResponse {
    private HttpStatus status;
    private List<String> error;
    private PayloadModel payload;
}
