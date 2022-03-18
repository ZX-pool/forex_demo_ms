create table exchange_value
(
    id                  INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    currency_from       VARCHAR(20) NOT NULL,
    currency_to         VARCHAR(20) NOT NULL,
    conversion_multiple DECIMAL     NOT NULL,
    port                INT DEFAULT 0
);

insert into exchange_value(currency_from, currency_to, conversion_multiple)
values ('USD', 'UAH', 32);
insert into exchange_value(currency_from, currency_to, conversion_multiple)
values ('EUR', 'UAH', 35);
insert into exchange_value(currency_from, currency_to, conversion_multiple)
values ('PLN', 'UAH', 7);