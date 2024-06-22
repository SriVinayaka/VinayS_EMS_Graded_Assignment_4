
DROP TABLE IF EXISTS roles;
CREATE TABLE roles(
role_id INT PRIMARY KEY,
name VARCHAR(255)
);

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles(
user_id INT PRIMARY KEY,
role_id INT
);



insert into roles (
role_id,
name
)
values (
1,
'ADMIN'
);


insert into users (
user_id,
password,
username) 
values (
1,
'$2a$12$QzxLNIM/2jjnGByIY94x8.B/4y0U9aFo1pIsgMFt1uYdcjVMFNOYu',
'SriGanesha'
);



insert into users_roles (
user_id,
role_id
)
values (
1,
1
);


password
$2a$12$QzxLNIM/2jjnGByIY94x8.B/4y0U9aFo1pIsgMFt1uYdcjVMFNOYu

