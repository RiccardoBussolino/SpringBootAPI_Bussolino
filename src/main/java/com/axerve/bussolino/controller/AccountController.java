package com.axerve.bussolino.controller;

import com.axerve.bussolino.response.internal.InternalSaldoResponse;
import com.axerve.bussolino.response.external.ExternalSaldoResponse;
import com.axerve.bussolino.service.SaldoService;
import com.axerve.bussolino.utility.Utility;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/letturaSaldo")
public class AccountController {
/*    private final TransactionalRepository repository;

    public AccountController(TransactionalRepository repository) {
        this.repository = repository;
    }
*/
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public InternalSaldoResponse getSaldo(@RequestParam String accountId) {
        SaldoService saldoService = new SaldoService(new RestTemplate());
        ExternalSaldoResponse response = saldoService.getAccount(accountId);
        return Utility.prepareResponse(response);
    }
}
