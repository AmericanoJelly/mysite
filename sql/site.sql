desc site;

insert into site value(null, 'Mysite','안녕하세요. 유진이의 사이트에 오신걸 환영합니다.','/assets/images/default.jpg','이 사이트는  웹 프로그램밍 실습과제 예제 사이트입니다.\n메뉴는 사이트 소개, 방명록, 게시판이 있구요. full stack 수업 배운 거 있는거 없는 거 다 합쳐서만들어 놓은 사이트 입니다.');
select * from site;
 delete from site where no = 1;

select title, welcome_message, profile_url, discription  from site;

select * from user;
alter table user add column role enum('USER','ADMIN') default 'USER' after gender;
update user set role ='ADMIN' where no = 1;