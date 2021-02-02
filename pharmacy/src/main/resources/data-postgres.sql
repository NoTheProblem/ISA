INSERT INTO AUTHORITY (name) VALUES ('ROLE_SYSADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPPLIER');


insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date) values ('ROLE_SYSADMIN','Aleksa','Milenovic','aleksaMilenovic','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','aleska@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);




