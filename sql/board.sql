desc board;

select * from board;



 insert into board values ( null, "test01", "안녕하세요 홀홀홀", 33, now(), 1, 1, 1, 1);
  insert into board values ( null, "test02", "testtesteteste", 22, now(), 2, 1, 1, 1);
  insert into board values ( null, "test011", "t11esttesteteste", 22, now(), 1, 2, 2, 2);
  
 
 select a.title, b.name, a.hit, a.reg_date
 from board a, user b
 where a.user_no = b.no
 and a.user_no = 2;
 
 delete from board where no = 11;
 
select title, contents from board;

insert into board values 
( null, "test03", "test03333333333333", 0, now(),(select max(g_no)from board a)+1 , 1, 1, 2);
        
        
select max(g_no)from board ;

update board set title = "ss", contents="ss" where user_no = 2;

update board set hit = hit+1 where no =16;

select a.no, a.g_no, a.title, b.name, a.hit, date_format(a.reg_date, '%Y-%m-%d %r'), a.user_no, a.dept, a.o_no 
						from board a, user b 
						where a.user_no = b.no 
						or a.title like concat('%', title, '%')
						order by a.g_no desc, a.o_no asc;

select no, title, contents, hit, user_no, g_no ,dept ,o_no
			 from board where no = 66;
             
             select a.no, a.title, b.name, a.hit, date_format(a.reg_date, '%Y-%m-%d %r')as regDate, a.g_no as gNo, a.o_no as oNo, a.dept, a.user_no as userNo
						from board a, user b
						where a.user_no = b.no;
				  	 
