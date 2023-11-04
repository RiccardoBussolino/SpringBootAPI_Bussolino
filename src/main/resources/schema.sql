CREATE TABLE transazioni (
    transaction_id VARCHAR(255) NOT NULL,
    operation_id VARCHAR(255) NOT NULL,
    accounting_date DATE NOT NULL,
    value_date DATE NOT NULL,
    type VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    currency VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (transaction_id)
);