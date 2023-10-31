create table Transazioni(
    id int not null,
    accountId varchar(9) not null,
    IBAN varchar(27) not null,
    abiCode varchar(5) not null,
    cabCode varchar(5) not null,
    countryCode varchar(2) not null,
    internationalCin varchar(2) not null,
    nationalCin varchar(1) not null,
    account varchar(12) not null,
    productName varchar(100),
    activatedDate DATE,
    currency varchar(3)
);
