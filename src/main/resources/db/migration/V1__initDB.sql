create table Person(
     person_id serial primary key,
     first_name varchar(100) not null,
     last_name varchar(100) not null,
     middle_name varchar(100) not null,
     birthday timestamp with time zone,
     gender varchar(15) not null,
     passport_series int not null,
     passport_number int not null UNIQUE
);

create table CONTRACT(
    id_contract serial primary key,
    start_date timestamp with time zone not null ,
    end_date timestamp with time zone not null,
    type_contract varchar(50) not null ,
    max_speed int,
    package_channel varchar(100),
    rate varchar(100),
    owner_id int references Person(person_id)
);


