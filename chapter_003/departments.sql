create table departments_db;
create table departments(
                            id serial primary key,
                            name varchar(255)
);
create table employees(
                          id serial primary key,
                          name varchar(255),
                          dep_id int references departments(id)
);
insert into departments(name) values('it');
insert into departments(name) values('press');
insert into departments(name) values('economic');
insert into employees(name, dep_id) values('Kate', 2);
insert into employees(name, dep_id) values('Egor', 1);
insert into employees(name, dep_id) values('Petr', 1);
insert into employees(name, dep_id) values('Alex', null);
insert into employees(name, dep_id) values('Viktor', null);
insert into employees(name, dep_id) values('Ivan', 1);
insert into employees(name, dep_id) values('Tracy', 2);
insert into employees(name, dep_id) values('Bob', 2);
insert into employees(name, dep_id) values('Khabib', null);
insert into employees(name, dep_id) values('Tom', 3);

select * from employees e left join departments d on e.dep_id = d.id;
select * from employees e right join departments d on e.dep_id = d.id;
select * from employees e full join departments d on e.dep_id = d.id;
select * from employees cross join departments;

select * from employees e left join departments d on e.dep_id = d.id
where d.id is null;

select * from employees e left join departments d on e.dep_id = d.id;
select * from departments d right join employees e on d.id = e.dep_id;

create table teens(
                      name varchar(255),
                      gender char
);
insert into teens(name, gender) values('Alex', 'm');
insert into teens(name, gender) values('Petr', 'm');
insert into teens(name, gender) values('Fedor', 'm');
insert into teens(name, gender) values('Tom', 'm');
insert into teens(name, gender) values('Quentin', 'm');
insert into teens(name, gender) values('Darya', 'w');
insert into teens(name, gender) values('Maria', 'w');
insert into teens(name, gender) values('Alla', 'w');
select t1.name as "Имя1", t2.name as "Имя2" from teens t1 cross join teens t2
where t1.gender != t2.gender and t1.gender like 'm';
