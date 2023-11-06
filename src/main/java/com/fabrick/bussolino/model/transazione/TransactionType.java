package com.fabrick.bussolino.model.transazione;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionType {
    private String enumeration;
    private String value;


    /*
    * Sarebbe possibile definirli come ENUM, ma dato che non vi è un perimetro fisso / alimentazione del dominio dinamica, Li definisco come String, in modo da poter accettare tutti i valori.
    *
    * (vedi definizione seguente)*/
    public enum Enumeration {
        GBS_TRANSACTION_TYPE
    }

    public enum Value {
        GBS_ACCOUNT_TRANSACTION_TYPE_0009,
        GBS_ACCOUNT_TRANSACTION_TYPE_0010,
        GBS_ACCOUNT_TRANSACTION_TYPE_0016,

        GBS_ACCOUNT_TRANSACTION_TYPE_0034,
        GBS_ACCOUNT_TRANSACTION_TYPE_0050

    }

    @Override
    public String toString() {
        return "ENUMERATION:".concat(this.enumeration.toString()).concat(" - VALUE: ").concat(this.value.toString());
    }
}