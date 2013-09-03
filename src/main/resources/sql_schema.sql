create user 'improveit'@'localhost' identified by 'improveit';
grant all on improve_it.* to 'improveit'@'localhost';

create database if not exists improve_it;

create table if not exists improve_it.users	(
	id int not null auto_increment primary key,
	user_first_name varchar(30) null default "Kitten",
	user_second_name varchar(30) null default "-",
	user_fathers_name varchar(30) null default "-",
	user_phonenumber varchar(11) null,
	user_email varchar(30) null,
	user_password varchar(100) not null,
	user_birthdate date null default "1900-01-01",
	user_serial mediumint(4) null,
	user_number mediumint(6) null,
	user_region varchar(30) null default "-",
	user_city varchar(30) null default "-",
	user_street varchar(30) null default "-",
	user_done boolean not null default 0,
	user_step enum('first', 'second', 'third', 'finale') not null default 'first',
	created timestamp default now()
);

create unique index un_email on improve_it.users (user_email);
create unique index un_phonenumber on improve_it.users (user_phonenumber);
create unique index un_serial_number on improve_it.users (user_serial, user_number);
