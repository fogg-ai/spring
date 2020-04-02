-- drop database if exists academy;
-- create database if not exists academy default character set utf8;
-- use academy;
create table students
(
    id         int primary key auto_increment,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    age        int          not null,
    "group"    varchar(255) not null
    , birth_date date         not null
);