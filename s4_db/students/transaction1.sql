-- lost update
begin;

update student set salary = 100 + 1 where id = 2;

commit;

select * from student where id = 2;

-- non-repeatable read
begin;

select * from student;
-- select again after commit of transaction 2
select * from student;

rollback;

-- phantom read
begin;

select * from student;
-- select again after commit of transaction 2
select * from student;

rollback;

-- serialization anomaly
begin transaction isolation level serializable;

select sum(salary) from student where department = 'Physics';

select * from student where name = 'Vitaliy';

insert into student (id, name, address, department, salary)
values (4, 'Pavel', 'Stroiteley, 4', 'Maths', 100);

commit;

-- SELECT FOR UPDATE
select * from student order by name
    for update skip locked
limit 2;

-- Deadlock
begin;

select * from student where id = 2 for update;
update student set salary = 200 where id = 3;

commit;