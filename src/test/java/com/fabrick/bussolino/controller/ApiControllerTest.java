package com.fabrick.bussolino.controller;


import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.request.transazione.internal.InternalTransazioneRequest;
import com.fabrick.bussolino.response.JsonResponse;
import com.fabrick.bussolino.response.bonifico.internal.InternalBonificoResponse;
import com.fabrick.bussolino.response.saldo.internal.InternalSaldoResponse;
import com.fabrick.bussolino.response.transazione.internal.InternalTransazioneResponse;
import com.fabrick.bussolino.service.BonificoService;
import com.fabrick.bussolino.service.SaldoService;
import com.fabrick.bussolino.service.TransazioneService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.fabrick.bussolino.utility.MockedObject.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApiController.class)
public class ApiControllerTest {
    @Autowired
    private ApiController controller;
    @MockBean
    private SaldoService saldoService;

    @MockBean
    private BonificoService bonificoService;

    @MockBean
    private TransazioneService transazioneService;

    @Test
    public void testSaldo() {
        when(saldoService.getSaldo(10000000L)).thenReturn(generateJsonResponseSaldo());
        JsonResponse<InternalSaldoResponse> response = controller.getSaldo(10000000L);
        Assert.assertEquals(response.getStatus(), HttpStatus.OK);
        Assert.assertEquals(response.getPayload().getSaldo(), "1");

    }

    @Test
    public void testSaldoKO() {
        when(saldoService.getSaldo(10000000L)).thenReturn(generateJsonResponseKOSaldo());
        JsonResponse<InternalSaldoResponse> response = controller.getSaldo(1L);
        Assert.assertEquals(response.getStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
        Assert.assertTrue(!response.getError().isEmpty());
        Assert.assertEquals(response.getPayload().getSaldo(), null);

    }

    @Test
    public void testBonifico() {
        when(bonificoService.moneyTransfer(any())).thenReturn(generateJsonResponseBonifico());
        JsonResponse<InternalBonificoResponse> response = controller.moneyTransfer(new InternalBonificoRequest(10000000L, "Mario", "DescrizioneBonifico", "EUR", "1", "2023-10-01"));
        Assert.assertEquals(response.getStatus(), HttpStatus.OK);
        Assert.assertNotNull(response.getPayload());

    }

    @Test
    public void testBonificoKO() {
        when(bonificoService.moneyTransfer(any())).thenReturn(generateJsonResponseKOBonifico());
        JsonResponse<InternalBonificoResponse> response = controller.moneyTransfer(new InternalBonificoRequest(1L, "", "", "EUR", "", ""));
        Assert.assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST);
        Assert.assertTrue(!response.getError().isEmpty());
        Assert.assertEquals(response.getPayload().getCode(), "API000");
        Assert.assertNotNull(response.getPayload().getDescription());

    }

    public void testTransazione() {
        when(transazioneService.transactionList(any())).thenReturn(generateJsonResponseTransazione());
        JsonResponse<InternalBonificoResponse> response = controller.moneyTransfer(new InternalBonificoRequest(10000000L, "Mario", "DescrizioneBonifico", "EUR", "1", "2023-10-01"));
        Assert.assertEquals(response.getStatus(), HttpStatus.OK);
        Assert.assertNotNull(response.getPayload());

    }


    @Test
    public void testTransazioneKO() {
        when(transazioneService.transactionList(any())).thenReturn(generateJsonResponseKOTransazione());
        JsonResponse<InternalTransazioneResponse> response = controller.transactionList(new InternalTransazioneRequest(1L, "2023-10-01", "2023-10-01"));
        Assert.assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST);
        Assert.assertNull(response.getPayload().getList());

    }


}