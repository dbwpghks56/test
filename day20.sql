select * from emp;


select * from emp where sal between 1000 and 2000;

select EMPNO, SAl from emp where EMPNO in(7521, 7654, 7844);

select * from emp where empno not in(7521, 7654, 7844);

select * from emp where ename like '%J%';

select empno, ename from emp where ename like 'K%';
select empno, ename from emp where ename like '%K%';
select empno, ename from emp where ename like '%K';

select * from emp where mgr is null;

create table tb1_test1 (
student_id number PRIMARY key, -- null 허용안되
student_name varchar2(30) not null,
phone char(12) unique, -- null 허용
regdate date
);

select * from user_constraints;
select * from user_cons_columns;

insert into tb1_test1 values (1, '제환', '01047486110', sysdate);
select * from tb1_test1;


select * from emp order by sal, comm desc nulls last; -- 생략하면 오름차순, desc 쓰면 내림차순, nulls last는 null이 뒤에 나오게

select * from emp where deptno = 30 order by sal asc, ename desc, empno asc;

select * from emp order by job desc, deptno asc nulls first, ename asc;

insert into tb1_test1 values(2, '제환2', '01044847611', sysdate);
commit;

select * from tb1_test1;

insert into tb1_test1(student_id, student_name, phone) values(3, '제환3','01044448888');
commit;

select * from tb1_test1;

select first_name, salary, department_id from employees where salary >= 15000;
select first_name, salary * 12 as 연봉 from employees where salary * 12 >= 170000;
select first_name, salary from employees where department_id is null;
select first_name, salary, hire_date from employees where hire_date <= '2004/12/31';
select first_name, salary, department_id from employees where department_id in(50, 80);
select first_name, salary, department_id, hire_date from employees where hire_date >= '2005/01/01' and salary between 1300 and 20000;
select * from employees where hire_date between '2005/01/01' and '2005/12/31';
select first_name, salary, hire_date from employees where first_name like 'D%';
select first_name, salary, hire_date from employees where hire_date like '%/12/%';
select first_name, salary, hire_date from employees where first_name like '%le%';
select first_name, salary, hire_date from employees where first_name like '%m';
select first_name, salary, hire_date from employees where first_name like '__r%';
select first_name, commission_pct, salary from employees where commission_pct is not null;
select first_name, commission_pct, salary from employees where commission_pct is null;

select * from employees where hire_date between '2000/01/01' and '2009/12/31' and department_id in (30, 50, 80) and
salary between 5000 and 17000 and commission_pct is not null
order by hire_date asc, salary desc;

select * from emp where mod(deptno, 2) = 1;
select * from emp;

select * from emp where substr(hiredate, 1, 2) = 87;
select * from emp where to_char(hiredate, 'yy') = 87;

select * from emp where substr(ename, -1, 1) = 'E';

select lpad('oracle', 20, '#') from dual;















