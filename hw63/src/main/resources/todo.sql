create database Todo charset utf8;


use Todo;
create table ToDoList
(
    id                int primary key auto_increment,
    short_description varchar(255),
    long_description  text,
    created           timestamp,
    start             date,
    end               date,
    status_id         int,
    category_id       int,
    FOREIGN KEY (category_id) REFERENCES Categories(id),
    FOREIGN KEY (status_id) REFERENCES Status(id)
);
create table Categories
(
    id   int primary key  auto_increment,
    name varchar(255) unique
);

create table Status
(
    id int primary key  auto_increment,
    name varchar(255)
);