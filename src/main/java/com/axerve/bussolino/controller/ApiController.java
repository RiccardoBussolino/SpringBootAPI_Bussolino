package com.axerve.bussolino.controller;

import com.axerve.bussolino.request.ExternalBonificoRequest;
import com.axerve.bussolino.response.internal.InternalBonificoResponse;
import com.axerve.bussolino.response.internal.InternalSaldoResponse;
import com.axerve.bussolino.response.external.ExternalSaldoResponse;
import com.axerve.bussolino.service.BonificoService;
import com.axerve.bussolino.service.SaldoService;
import com.axerve.bussolino.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {
/*    private final TransactionalRepository repository;

    public AccountController(TransactionalRepository repository) {
        this.repository = repository;
    }
*/
    @GetMapping(value = "/letturaSaldo")
    @ResponseStatus(HttpStatus.OK)
    public InternalSaldoResponse getSaldo(@RequestParam String accountId) {
        SaldoService saldoService = new SaldoService(new RestTemplate());
        ExternalSaldoResponse response = saldoService.getSaldo(accountId);
        return Utility.prepareResponse(response);
    }

    @PostMapping(value = "/bonifico")
    @ResponseStatus(HttpStatus.OK)
    public InternalBonificoResponse Bonifico(@RequestParam String accountId, @RequestBody InternalSaldoResponse.BonificoRequest bonificoRequest) {
        BonificoService bonificoService = new BonificoService(new RestTemplate());
        ExternalBonificoRequest response = bonificoService.moneyTransfer(accountId);
        return Utility.prepareResponse(response);
    }
}
