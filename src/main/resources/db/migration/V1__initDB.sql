create table Person(
     person_id serial primary key,
     first_name varchar(100) not null,
     last_name varchar(100) not null,
     middle_name varchar(100) not null,
     birthday date,
     gender varchar(15) not null,
     passport_series int not null,
     passport_number int not null UNIQUE
);

create table Rate(
                     id_rate serial primary key,
                     numberOfMinutes integer not null,
                     numberOfSms integer not null ,
                     numberOfGb integer not null
);

create table CONTRACT(
    id_contract serial primary key,
    start_date date not null ,
    end_date date not null ,
    max_speed int,
    package_channel varchar(100),
    rate int references Rate(id_rate),
    owner_id int references Person(person_id)
);



