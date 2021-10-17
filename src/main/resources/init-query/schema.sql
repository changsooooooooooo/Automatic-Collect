drop table if exists coin_theme CASCADE;
commit;

drop table if exists coin_list CASCADE;
commit;

create table coin_list
(
    coin_name varchar(255),
    primary key (coin_name)
);
commit;

create table coin_theme(
    coin_name varchar(255),
    coin_category varchar(255),
    primary key(coin_name, coin_category),
    foreign key(coin_name) references coin_list(coin_name)
);
commit;
