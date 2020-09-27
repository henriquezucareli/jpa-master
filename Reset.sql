drop user 'user'@'localhost';

drop schema avaliacao;

create schema avaliacao;

use avaliacao;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on avaliacao.* to user@'localhost';

create table tea_team (
    tea_id bigint primary key auto_increment,
    tea_city varchar(30) not null,
    tea_name varchar(30) not null,
    tea_conference varchar (4) not null
);

create table ros_roster (
    ros_id bigint primary key auto_increment,
    ros_first_name varchar(50) not null,
    ros_last_name varchar(50) not null,
    ros_salary float not null,
    ros_born date not null
);

create table pla_player (
    pla_id bigint primary key auto_increment,
    pla_position varchar(15) not null,
    pla_first_name varchar(50) not null,
    pla_last_name varchar(50) not null,
    pla_salary float not null,
    pla_born date not null,
    tea_id bigint,
    constraint pla_tea_fk foreign key (tea_id)
        references tea_team (tea_id)
);

create table sta_staff (
    sta_id bigint primary key,
    sta_function varchar(30) not null,
    tea_id bigint,
    constraint sta_ros_fk foreign key (sta_id)
        references ros_roster (ros_id),
    constraint sta_tea_fk foreign key (tea_id)
        references tea_team (tea_id)
);

create table pla_historic (
    pla_id bigint not null,
    tea_id bigint not null,
    primary key (pla_id, tea_id),
    constraint pla_his_fk foreign key (pla_id)
        references pla_player (pla_id),
    constraint tea_his_fk foreign key (tea_id)
        references tea_team (tea_id)
);