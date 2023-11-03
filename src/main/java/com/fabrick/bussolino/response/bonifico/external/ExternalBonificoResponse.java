package com.fabrick.bussolino.response.bonifico.external;

import com.fabrick.bussolino.model.bonifico.PayloadModel;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalBonificoResponse {
    private HttpStatus status;
    private List<String> error;
    private PayloadModel payload;
}
