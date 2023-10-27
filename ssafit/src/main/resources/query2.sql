create table if not exists board(
	id int not null auto_increment,
    title varchar(40) not null,
    writer varchar(20) not null,
    content varchar(100) not null,
    reg_date datetime not null,
    view_cnt int null default 0,
    primary key (`id`)
);

insert into board (title,writer,content,reg_date,view_cnt) values("제목1", "장승호","안녕하세요",now(),0);
drop table board;
select * from board;

select
	c.id,
    c.content,
    c.user_seq,
    c.article_id,
    c.board_id
from
	comment c
    left join 
    article a
    on a.id = c.article_id
where
	article_id = 1;