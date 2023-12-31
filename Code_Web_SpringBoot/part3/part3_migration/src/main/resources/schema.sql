create table Board
(
    bno          bigint auto_increment primary key,
    member_email varchar(50)   not null,
    title        varchar(100)  not null,
    content      varchar(1500) not null,
    regdate      timestamp default current_timestamp,
    moddate      timestamp default current_timestamp on update current_timestamp,
    deleted      boolean   default false,
    foreign key (member_email) references Member (email)
);

create table Member
(
    email    varchar(100) primary key,
    password varchar(100) not null,
    name     varchar(50)  not null
)

create table Reply
(
    rno           bigint auto_increment primary key,
    board_bno     bigint not null,
    title         varchar(50)   not null,
    content       varchar(1000) not null,
    replyer       varchar(50)   not null,
    foreign key (board_bno) references Board (bno),
    foreign key (replyer) references Member(email)
);
