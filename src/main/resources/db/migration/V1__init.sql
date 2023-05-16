create table products(id bigserial primary key,
                      title varchar(255),
                      price int);
insert into products(title, price) values
('Milk', 80), ('Bread', 50), ('Apple', 60);

create table users
(
    id       bigserial primary key,
    name     varchar(255),
    password varchar(255)

);

insert into users(name, password) values
('Bob', 'bob'), ('Nick', 'nick');

create table roles(id bigserial primary key,
                      title varchar(255));
insert into roles(title) values
('ROLE_USER'), ('ROLE_ADMIN') ;

create table users_roles
(
    user_id  bigint not null references users (id),
    roles_id bigint not null references roles (id),
    primary key (user_id, roles_id)
);

insert into users_roles(user_id, roles_id) VALUES
(1,1),(2,2),(1,2);

create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    total_price int    not null,
    address     varchar(255),
    phone       varchar(255)
);


create table order_items
(
    id                bigserial primary key,
    user_id           bigint not null references users (id),
    order_id          bigint not null references orders (id),
    title             varchar(255) not null ,
    quantity          bigint not null,
    price_per_product int    not null,
    price             int    not null
);

alter table users add orders_id bigint references orders(id);