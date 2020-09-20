drop user 'user'@'localhost';

drop schema nba_organization;

create schema nba_organization;

use nba_organization;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on nba_organization.* to user@'localhost';

create table tea_team (
    tea_id bigint unsigned primary key auto_increment,
    tea_city varchar(30) not null,
    tea_name varchar(30) not null,
    tea_conference varchar (4) not null
);

create table ros_roster (
  ros_id bigint unsigned primary key auto_increment,
  ros_first_name varchar(50) not null,
  ros_last_name varchar(50) not null,
  ros_salary float not null,
  ros_born datetime not null,
  ros_team bigint,
  constraint ros_tea_fk foreign key (ros_team)
    references tea_team (tea_id)
);

create table pla_player (
  pla_id bigint unsigned primary key,
  pla_position varchar(15) not null,
  constraint pla_ros_fk foreign key (pla_id)
    references ros_roster (ros_id)
);

create table sta_staff (
  sta_id bigint unsigned primary key,
  sta_function varchar(30) not null,
  constraint sta_ros_fk foreign key (sta_id)
    references ros_roster (ros_id)
);

create table pla_historic (
    pla_id bigint primary key,
    tea_id bigint primary key,
    constraint pla_his_fk foreign key (pla_id)
        references pla_player (pla_id)
    constraint tea_his_fk foreign key (tea_id)
        references tea_team (tea_id)
)