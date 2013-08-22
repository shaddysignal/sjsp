/*create user 'improveit'@'localhost' identified by 'improveit';
grant all on improve_it.* to 'improveit'@'localhost';
*/
create database if not exists improve_it;

create table if not exists improve_it.users (id int not null auto_increment primary key, user_first_name varchar(30) not null default "-", user_second_name varchar(30) not null default "-", user_fathers_name varchar(30) not null default "-", user_phonenumber int(11) null unique, user_email varchar(30) null unique, user_password varchar(40) not null default "", user_birthdate date not null default "00-00-0000", user_serial mediumint(4) null, user_number mediumint(6) null, user_region varchar(30) not null default "-", user_city varchar(30) not null default "-", user_street varchar(30) not null default "-", created timestamp default now());

create unique index un_serial_number on improve_it.users (user_serial, user_number);
