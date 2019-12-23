CREATE TABLE users(
   user_id serial PRIMARY KEY,
   userFirstName VARCHAR (50) NOT NULL,
   userLastName VARCHAR (50) NOT NULL,
   password VARCHAR (50) NOT NULL,
   accountId integer not null references account(account_id),
 	isEmployee boolean,
 	isAdmin boolean,
   created_on TIMESTAMP NOT NULL
);
CREATE TABLE account(
   account_id serial PRIMARY KEY,
 	accountNumber integer not null,
 	balance numeric,
 	pinNumber integer not null,
   created_on TIMESTAMP NOT NULL
);
DROP TABLE employee;