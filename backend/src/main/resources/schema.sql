create table if not exists  customer (
    id long primary key auto_increment,
    name varchar(50),
    age int,
    active boolean
);