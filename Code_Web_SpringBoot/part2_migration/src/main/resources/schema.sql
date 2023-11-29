create table GuestBook(
    gno bigint auto_increment primary key,
    title varchar(100) not null,
    writer varchar(50) not null,
    content varchar(1500) not null,
    regdate timestamp default current_timestamp,
    moddate timestamp default current_timestamp on update current_timestamp,
    deleted boolean default false
    )
