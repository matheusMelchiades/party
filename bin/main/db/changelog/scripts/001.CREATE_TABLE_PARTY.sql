create table party (
  id integer identity primary key,
  code varchar(10) unique not null,
  name varchar(255) not null,
  number integer(2) unique not null,
);
