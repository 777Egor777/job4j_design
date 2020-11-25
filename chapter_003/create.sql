create database item_system_db;
create table role(
                     id serial primary key,
                     name varchar(255)
);
create table rules(
                      id serial primary key,
                      name varchar(255)
);
create table rule_of_role(
                             id serial primary key,
                             role_id int references role(id),
                             rule_id int references rules(id)
);
create table users(
                      id serial primary key,
                      name varchar(255),
                      users_role_id int references role(id)
);
create table category(
                         id serial primary key,
                         name varchar(255)
);
create table state(
                      id serial primary key,
                      name varchar(255)
);
create table item(
                     id serial primary key,
                     name varchar(255),
                     content text,
                     user_id int references users(id),
                     category_id int references category(id),
                     state_id int references state(id)
);
create table attach(
                       id serial primary key,
                       name text,
                       url text,
                       item_id int references item(id)
);
create table comment(
                        id serial primary key,
                        content text,
                        item_id int  references item(id)
);
insert into role(name) values ('Admin');
insert into role(name) values ('User');
insert into rules(name) values('Read');
insert into rules(name) values('Write');
insert into rules(name) values('Close');
insert into rule_of_role(role_id, rule_id) values(1, 1);
insert into rule_of_role(role_id, rule_id) values(1, 2);
insert into rule_of_role(role_id, rule_id) values(1, 3);
insert into rule_of_role(role_id, rule_id) values(2, 1);
insert into rule_of_role(role_id, rule_id) values(2, 2);
insert into category(name) values('Task');
insert into category(name) values('Mistake');
insert into category(name) values('Error');
insert into state(name) values('Open');
insert into state(name) values('Close');