create table if not exists items
(
    id          bigserial primary key,
    name        varchar(255) not null,
    description varchar(255) not null,
    in_stock    boolean      not null
);

create table if not exists carts
(
    id bigserial primary key
);

create table if not exists cart_item
(
    id bigserial primary key,
    item_id bigint not null,
    size int8,
    amount integer,
    cart_id bigint not null,
    constraint cart_item_carts foreign key (cart_id) references carts (id) on delete cascade on update no action,
    constraint cart_item_item foreign key (item_id) references items (id) on delete cascade on update no action
);

create table if not exists availability
(
    id bigserial primary key,
    size    int8    not null,
    amount  integer not null
);

create table if not exists items_availability
(
    item_id bigint not null,
    availability_id bigint not null,
    primary key (item_id, availability_id),
    constraint items_availability_items foreign key (item_id) references items(id) on delete cascade on update no action,
    constraint items_availability_availability foreign key (availability_id) references availability(id) on delete cascade on update no action
);

create table if not exists users
(
    id       bigserial primary key,
    name     varchar(255)  not null,
    username varchar(255)  not null unique,
    password varchar(255)  not null,
    cart_id  bigint unique not null,
    constraint users_cart foreign key (cart_id) references carts (id) on delete cascade on update no action
);

create table if not exists users_roles
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_users_roles_users foreign key (user_id) references users (id) on delete cascade on update no action
);

create table if not exists items_images
(
    item_id bigint       not null,
    image   varchar(255) not null,
    constraint items_images_items foreign key (item_id) references items (id) on delete cascade on update no action
);
