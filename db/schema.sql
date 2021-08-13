CREATE TABLE rule
(
    rule_id serial primary key,
    rule_name varchar(2000)
);

CREATE TABLE accident_type
(
    type_id serial primary key,
    type_name varchar(2000)
);

CREATE TABLE accident
(
    id serial primary key,
    name varchar(2000),
    description varchar(2000),
    address varchar(2000),
    accident_type_id int not null references accident_type(type_id)
);

CREATE TABLE accident_rules
(
    id serial primary key,
    accident_id int references accident(id),
    rule_id int references  rule(rule_id)
);

