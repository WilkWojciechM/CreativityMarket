insert into user (email, name, password)
values
    ('admin@example.com','admin', '{noop}adminpass'),    --1
    ('creator@example.com','creator', '{noop}creatorpass'), --2
    ('employer@exmaple.com','employer', '{noop}employer'); --3

insert into
    user_role(name, description)
values
    ('ADMIN', 'full permission'), --1
    ('CREATOR', 'basic permission, adding commission project, adding portfolio'), --2
    ('EMPLOYER', 'basic permission, adding commission'); --3

insert into
    user_roles(user_id, role_id)
values
    (1, 1),
    (2, 2),
    (3, 3);