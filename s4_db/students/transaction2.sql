-- lost update
begin;
update student set salary = 101 + 1 where id = 2;

commit;

-- non-repeatable read
begin;

update student set salary = 300 where id = 2;

commit;

-- phantom read
begin;

insert into student (id, name, address, department)
values (4, 'Nikolay', 'Stroiteley, 4', 'Physics');

commit;

-- serialization anomaly
begin transaction isolation level serializable;

select sum(salary) from student where department = 'Maths';

insert into student (id, name, address, department, salary)
values (4, 'Vitaliy', 'Stroiteley, 4', 'Physics', 200);

commit;

-- SELECT FOR UPDATE
select * from student order by name
    for update skip locked
limit 2;

-- Deadlock
begin;

select * from student where id = 3 for update;
update student set salary = 200 where id = 2;

commit;
