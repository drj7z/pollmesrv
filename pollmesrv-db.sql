CREATE TABLE ACCOUNT
(
  id varchar(256) primary key, 
  
  username varchar(64) unique, 
  password varchar(512), 
  telephoneNumber varchar(32), 
  email varchar(256) unique,
  publicKey varchar(8192), 
  name varchar(128)
);
