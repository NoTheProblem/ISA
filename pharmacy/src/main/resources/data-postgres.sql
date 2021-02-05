INSERT INTO AUTHORITY (name) VALUES ('ROLE_SYSADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPPLIER');


insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date) values ('ROLE_SYSADMIN','Aleksa','Milenovic','aleksaMilenovic','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','aleska@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);

INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE01', 'Paracetamol', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');
INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE02', 'Paracetamol', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');
INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE03', 'Paracetamol', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');
INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE04', 'Paracetamol', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');

INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Jankovic', 'Srbija', 'Novi Sad', 'Bulevar Despota Stefana 7', 'Apoteka', 2.0, 1000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Ja', 'Srbij', 'Nov Sad', 'Bulevar ', 'Apoteka', 0.0, 10.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Ja', 'Srbij', 'Nov Sad', 'Bulevar ', 'Apoteka', 1.0, 10.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Ja', 'Srbij', 'Nov Sad', 'Bulevar ', 'Apoteka', 5.0, 10.0);





