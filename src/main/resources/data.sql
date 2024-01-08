CREATE TABLE Worker (id int not null auto_increment, name varchar(255), primary key (id));
CREATE TABLE Shift (id int not null auto_increment, shiftStart datetime2, shiftEnd datetime2, worker_id int, shop_id int, primary key (id));
CREATE TABLE Shop (id int not null auto_increment, name varchar(255), primary key (id));


INSERT INTO Worker (name) VALUES ('BOB');

