insert into user_account
values(1,1,'bayar@interia.pl','Jan','Kowalski', '$2a$10$ON75YMyEUMAF.i0bIbDkiOoS7gY0WofsQBRwEswGjBXZlj.IMMsXK');
insert into user_account
values(2,1,'janq@interia.pl','Jan','Kowalski', '$2a$10$ON75YMyEUMAF.i0bIbDkiOoS7gY0WofsQBRwEswGjBXZlj.IMMsXK');
insert into user_account
values(3,1,'janq13@interia.pl','Jan','Kowalski', '$2a$10$ON75YMyEUMAF.i0bIbDkiOoS7gY0WofsQBRwEswGjBXZlj.IMMsXK');
INSERT INTO roles VALUES (1,'ADMIN');
INSERT INTO roles VALUES (2,'USER');
INSERT INTO USER_ROLE  VALUES (1,2);
insert into items
values(1,'normal plank','plank',20);
insert into items
values(2,'ordinary brick','brick',15);
insert into items
values(3,'exeptional fork','fork',5);
insert into items
values(4,'unique stick','stick',1);
insert into USERS_RENTED_ITEMS
values(1,1);
insert into USERS_RENTED_ITEMS
values(1,4);
insert into USERS_RENTED_ITEMS
values(1,2);
CREATE table persistent_logins (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;