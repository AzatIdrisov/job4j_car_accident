insert into accident_type(type_name) values ('Две машины');
insert into accident_type(type_name) values ('Машина и человек');
insert into accident_type(type_name) values ('Машина и велосипед');

insert into rule(rule_name) values ('Статья 1');
insert into rule(rule_name) values ('Статья 2');
insert into rule(rule_name) values ('Статья 3');

insert into accident(name, description, address, accident_type_id) values ('Сrush 1', 'Car crush 1', 'Moscow', 1);
insert into accident(name, description, address, accident_type_id) values ('Сrush 2', 'Car crush 2', 'SPB', 1);
insert into accident(name, description, address, accident_type_id) values ('Сrush 3', 'Car crush 3', 'Kazan', 1);
insert into accident(name, description, address, accident_type_id) values ('Сrush 4', 'Car crush 4', 'EKB', 1);