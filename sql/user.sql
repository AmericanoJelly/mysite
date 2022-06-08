select * from user;
insert into user values (null, 'admin', 'admin@mysite.com','admin', 'male', now());

select email, password
from user;

select no, name, email, gender
from user
where no= 2
and password = '0000';

update user set name='yujin', email='jyj@mysite.com', password='1234', gender='male'
where no = 2;



