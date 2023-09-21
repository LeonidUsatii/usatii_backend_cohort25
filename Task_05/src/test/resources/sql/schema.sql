drop table if exists course;

create table course (
                       id identity primary key,
                       begin_date varchar(30),
                       description varchar(1000),
                       end_date varchar(20),
                       price double not null,
                       state varchar(20),
                       title varchar(20)
);
