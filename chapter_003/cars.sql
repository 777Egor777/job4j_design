create database cars_db;
create table body(
                     id serial primary key,
                     name varchar(255)
);
create table engine(
                       id serial primary key,
                       name varchar(255)
);
create table transmission(
                             id serial primary key,
                             name varchar(255)
);
create table car(
                    id serial primary key,
                    name varchar(255),
                    body_id int references body(id),
                    engine_id int references engine(id),
                    transmission_id int references transmission(id)
);
insert into body(name) values('Body1');
insert into body(name) values('Body2');
insert into body(name) values('Body3');
insert into body(name) values('Body4');
insert into body(name) values('Body5');
insert into engine(name) values('Engine1');
insert into engine(name) values('Engine2');
insert into engine(name) values('Engine3');
insert into engine(name) values('Engine4');
insert into engine(name) values('Engine5');
insert into engine(name) values('Engine6');
insert into engine(name) values('Engine7');
insert into engine(name) values('Engine8');
insert into engine(name) values('Engine9');
insert into transmission(name) values('Transmission1');
insert into transmission(name) values('Transmission2');
insert into transmission(name) values('Transmission3');
insert into transmission(name) values('Transmission4');
insert into transmission(name) values('Transmission5');
insert into transmission(name) values('Transmission6');
insert into transmission(name) values('Transmission7');
insert into car(name, body_id, engine_id, transmission_id) values('Car1', 5, 9, 7);
insert into car(name, body_id, engine_id, transmission_id) values('Car2', 2, 7, 4);
insert into car(name, body_id, engine_id, transmission_id) values('Car3', 4, 4, 4);
insert into car(name, body_id, engine_id, transmission_id) values('Car4', 1, 2, 7);
insert into car(name, body_id, engine_id, transmission_id) values('Car5', 4, 9, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Car6', 1, 9, 7);
insert into car(name, body_id, engine_id, transmission_id) values('Car7', 5, 7, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Car8', 1, 2, 4);
insert into car(name, body_id, engine_id, transmission_id) values('Car9', 5, 9, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Car10', 5, 3, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Car11', 1, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values('Car12', 4, 2, 7);
insert into car(name, body_id, engine_id, transmission_id) values('Car13', 1, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values('Car14', 5, 7, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Car15', 4, 2, 4);
insert into car(name, body_id, engine_id, transmission_id) values('Car16', 1, 2, 7);
insert into car(name, body_id, engine_id, transmission_id) values('Car17', 4, 4, 4);

select c.id, c.name as "car", b.name as "body", e.name as "engine", t.name as "transmission"
from car c
         left join body b on c.body_id = b.id
         left join engine e on c.engine_id = e.id
         left join transmission t on t.id = c.transmission_id;

select b.name from body b left join car c on b.id = c.body_id
where c.id is null;

select e.name from engine e left join car c on e.id = c.engine_id
where c.id is null;

select t.name from transmission t left join car c on t.id = c.transmission_id
where c.id is null;