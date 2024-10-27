-- STEP 1 create schema sems
create schema sems;

-- STEP 2 Make sems as default schema 
USE sems;

-- STEP 3 RUN THE SEMS API application to create tables

-- STEP 4 Disable safe updates
SET SQL_SAFE_UPDATES = 0;

-- STEP 5 Delete any existing rows
delete from users;
delete from roles;
delete from users_roles;

-- STEP 6 Create SUPER ADMIN ROLE AND USER having set password as 'mypassword'

-- A. Create USER 
-- This corresponds to the plain text password value - 'mypassword' (SUPER_ADMIN)
set @SEMS_PASSWORD = '$2a$12$SnQXQrEzEAD8pj/3KGVhneF0R1BsRug5a.ON9Up0Qfucdoci7Uvbe';
insert into users (user_id, username, password) values (1, 'varun', @SEMS_PASSWORD);

-- B. Create SADMIN role
insert into roles (role_id, name) values (1, 'SADMIN');

-- C. Create USER_ROLE bridge relation
insert into users_roles (user_id, role_id) values (
(select user_id from users where username = 'varun'),
(select role_id from roles where name = 'SADMIN')
);


-- STEP 7 Revert to the previous value
SET SQL_SAFE_UPDATES = 1;    


-- STEP 8 WORK FROM SEMS API application 