desc board;

select * from board;


 insert into board values ( 1, "test01", "안녕하세요 홀홀홀", 33, now(), 1, 1, 1, 1);
  insert into board values ( 2, "test02", "testtesteteste", 22, now(), 2, 1, 1, 1);
 
 select a.title, b.name, a.hit, a.reg_date
 from board a, user b
 where a.user_no = b.no
order by a.g_no desc, a.o_no asc;
 
