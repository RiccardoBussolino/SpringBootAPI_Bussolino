package com.fabrick.bussolino.controller;


import com.fabrick.bussolino.response.saldo.external.ExternalSaldoResponse;
import com.fabrick.bussolino.response.bonifico.external.ExternalBonificoResponse;
import com.fabrick.bussolino.response.transazione.external.ExternalTransazioneResponse;
import com.fabrick.bussolino.request.bonifico.internal.InternalBonificoRequest;
import com.fabrick.bussolino.request.transazione.internal.InternalTransazioneRequest;
import com.fabrick.bussolino.response.bonifico.internal.InternalBonificoResponse;
import com.fabrick.bussolino.response.saldo.internal.InternalSaldoResponse;
import com.fabrick.bussolino.response.transazione.internal.InternalTransazioneResponse;
import com.fabrick.bussolino.service.BonificoService;
import com.fabrick.bussolino.service.SaldoService;
import com.fabrick.bussolino.service.TransazioneService;
import com.fabrick.bussolino.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping(value = "/letturaSaldo")
    @ResponseStatus(HttpStatus.OK)
    public InternalSaldoResponse getSaldo(@RequestParam Long accountId) {
        SaldoService saldoService = new SaldoService(new RestTemplate());
        ExternalSaldoResponse response = saldoService.getSaldo(accountId);
        return Utility.prepareResponse(response);
    }

    @GetMapping(value = "/bonifico")
    @ResponseStatus(HttpStatus.OK)
    public InternalBonificoResponse moneyTransfer(InternalBonificoRequest internalBonificoRequest) {
        BonificoService bonificoService = new BonificoService(new RestTemplate());
        ExternalBonificoResponse response = bonificoService.moneyTransfer(internalBonificoRequest);
        return Utility.prepareResponse(response,internalBonificoRequest.getAccountId());
    }
    @GetMapping(value = "/letturaTransazioni")
    @ResponseStatus(HttpStatus.OK)
    public InternalTransazioneResponse transactionList(InternalTransazioneRequest internalTransazioneRequest) {
        TransazioneService transazioneService = new TransazioneService(new RestTemplate());
        ExternalTransazioneResponse response = transazioneService.transactionList(internalTransazioneRequest);
        return Utility.prepareResponse(response,internalTransazioneRequest.getAccountId());
    }
}
