package com.fabrick.bussolino.service;

import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.service.ws.SaldoService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.fabrick.bussolino.utility.MockedObject.generateJsonResponseKOSaldo;
import static com.fabrick.bussolino.utility.MockedObject.generateJsonResponseSaldo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SaldoServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @Inject
    private SaldoService saldoService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        saldoService = new SaldoService(restTemplate);
    }

    @Test
    public void testGetSaldo() {
        Long accountId = 12345678L;
        JsonResponse<ExternalSaldoResponse> jsonResponse = generateJsonResponseSaldo();
        ResponseEntity<JsonResponse<ExternalSaldoResponse>> responseEntity = ResponseEntity.ok(jsonResponse);
        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<JsonResponse<ExternalSaldoResponse>>>any())).thenReturn(responseEntity);
        JsonResponse<ExternalSaldoResponse> result = saldoService.getSaldo(accountId);
        assertEquals(jsonResponse, result);
    }

    @Test
    public void testGetSaldoKO() {
        Long accountId = 12345678L;
        JsonResponse<ExternalSaldoResponse> jsonResponse = generateJsonResponseKOSaldo();
        ResponseEntity<JsonResponse<ExternalSaldoResponse>> responseEntity = ResponseEntity.ok(jsonResponse);
        when(restTemplate.exchange(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<JsonResponse<ExternalSaldoResponse>>>any())).thenReturn(responseEntity);
        JsonResponse<ExternalSaldoResponse> result = saldoService.getSaldo(accountId);
        assertEquals(jsonResponse, result);
    }

}
