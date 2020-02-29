create table "group"(
                        id int primary key auto_increment,
                        group_name varchar(255) UNIQUE not null
);

create table students (
                          id int primary key auto_increment,
                          first_name varchar(255) not null,
                          last_name varchar(255) not null,
                          age int not null,
                          "group" int not null ,
                          FOREIGN KEY ("group") REFERENCES "group"(id)
);

