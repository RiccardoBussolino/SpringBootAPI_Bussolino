package com.fabrick.bussolino.model.transazione;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionType {
    private Enumeration enumeration;
    private Value value;

    public enum Enumeration {
        GBS_TRANSACTION_TYPE
    }

    public enum Value {
        GBS_ACCOUNT_TRANSACTION_TYPE_0010,
        GBS_ACCOUNT_TRANSACTION_TYPE_0009,
        GBS_ACCOUNT_TRANSACTION_TYPE_0016
    }
}