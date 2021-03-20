drop table if exists person, property, policy;

create table person
(
    id    bigserial not null primary key,
    email varchar(255) unique
);

create table property
(
    id          bigserial not null primary key,
    description varchar(512),
    person_id   bigint    not null references person
);

create table policy
(
    id            bigserial not null primary key,
    coverage_type varchar(255),
    created_date  timestamp,
    duration      integer,
    property_id   bigint unique references property on delete cascade
);

insert into person (id, email)
values (1, 'valera@gmail.com'),
       (2, 'maxim@gmail.com');
alter sequence person_id_seq restart with 3;

insert into property (id, description, person_id)
values (1, 'BMW X5', 1),
       (2, 'House', 1),
       (3, 'Jet', 1),
       (4, 'Bike', 2);
alter sequence property_id_seq restart with 5;

insert into policy (id, coverage_type, created_date, duration, property_id)
values (1, 'COMPREHENSIVE', '2021-03-20 08:18:12.733265', 365, 1),
       (2, 'COLLISION', '2021-03-20 08:18:12.733265', 14, 4);
alter sequence policy_id_seq restart with 3;