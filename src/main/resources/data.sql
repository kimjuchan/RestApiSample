insert into user(id,join_date,name,pasword,ssn)
values(90001,now(), 'user1', '1111',  '1010-222');

insert into user(id,join_date,name,pasword,ssn)
values(90002,now(), 'user2','1111',  '1010-222');

insert into user(id,join_date,name,pasword,ssn)
values(90003,now(), 'user3','1111',  '1010-222');

insert into user(id,join_date,name,pasword,ssn)
values(90004,now(), 'user4','1111',  '1010-222');

insert into post(description,user_id) values('post1','90002');
insert into post(description,user_id) values('post2','90003');