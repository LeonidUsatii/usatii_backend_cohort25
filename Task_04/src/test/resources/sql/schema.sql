drop table if exists event;

create table event (
                       id identity primary key,
                       title varchar(20),
                       start_date varchar(20),
                       expiration_date varchar(30)
);