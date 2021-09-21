drop table if exists coins;
commit;
create table coins(
    coin_name varchar(255),
    coin_category varchar(255),
    primary key(coin_name, coin_category)
);
commit;
