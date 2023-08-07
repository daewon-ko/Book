create table GuestBook(
    gno bigint auto_increment primary key,
    Member_email varchar(50) not null foreign key,
    title varchar(100) not null,
    content varchar(1500) not null,
    regdate timestamp default current_timestamp,
    moddate timestamp default current_timestamp on update current_timestamp,
    deleted boolean default false
    )

create table Member(
    email varchar(100) primary key,
    password varchar(100) not null,
    name varchar(50) not null
)

create table Reply(
    rno bigint auto_increment primary key,
    GuestBook_gno bigint foreign key,
    title varchar(50) not null,
    content varchar(1000) not null,
    replyer varchar(50) not null
    )
