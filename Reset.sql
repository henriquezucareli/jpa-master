drop user 'user'@'localhost';

drop schema avaliacao;

create schema avaliacao;

use avaliacao;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on avaliacao.* to user@'localhost';

create table tea_team (
    id bigint primary key auto_increment,
    tea_city varchar(30) not null,
    tea_name varchar(30) not null,
    tea_conference varchar (4) not null
);

create table ros_roster (
    id bigint primary key auto_increment,
    ros_first_name varchar(50) not null,
    ros_last_name varchar(50) not null,
    ros_salary float not null,
    ros_born datetime not null,
    tea_id bigint,
    constraint ros_tea_fk foreign key (tea_id)
        references tea_team (id)
);

create table pla_player (
    id bigint primary key,
    pla_position varchar(15) not null,
    constraint pla_ros_fk foreign key (id)
        references ros_roster (id)
);

create table sta_staff (
    id bigint primary key,
    sta_function varchar(30) not null,
    constraint sta_ros_fk foreign key (id)
        references ros_roster (id)
);

create table pla_historic (
    pla_id bigint not null,
    tea_id bigint not null,
    primary key (pla_id, tea_id),
    constraint pla_his_fk foreign key (pla_id)
        references pla_player (id),
    constraint tea_his_fk foreign key (tea_id)
        references tea_team (id)
);