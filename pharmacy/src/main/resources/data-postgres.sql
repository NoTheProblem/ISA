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

INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka Jankovic', 'Srbija', 'Novi Sad', 'Bulevar oslobodjenja 135', 'Apoteka', 2.0, 1000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka Benu Merkator', 'Srbija', 'Nov Sad', 'TC Merkator, Bulear Oslobodjenja 102 ', 'Apoteka', 0.0, 2000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Benu Apoteka', 'Srbija', 'Nov Sad', 'Narodnog fronta 10', 'Apoteka', 1.0, 3000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka 409', 'Srbija', 'Nov Sad', 'Bulevar Mihajla Pupina 9', 'Apoteka', 5.0, 1000.0);



insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, evaluation_grade) values ('ROLE_DERMATOLOGIST','Aleksa','Milenovic','Derma','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','aleska1@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07', 1);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id) values ('ROLE_ADMIN','Branislav','Lazarevic','slav','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','branislavlazarevic@gmail.com','Srbija','Sabac','Marsala Tita 140',true, '2021-01-02 18:57:58.508-07',1);

insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id,evaluation_grade) values ('ROLE_PHARMACIST','Kristijan','Savic','ris','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','risfizika@gmail.com','Srbija','Jagodina','despotovac',true, '2021-01-02 18:57:58.508-07',1,0);


insert into promotion(title, text, start_date, end_date, type, pharmacy_id) values('Bekutan akcija', 'Svi bekutan prozivodi su sada kod nas vamo tmao','2021-01-11', '2021-03-30','promocija',1)

insert into AbsenceRequest(request_text, start_date,end_date, status,type_of_absence, employee_id,type_of_employee, employee_name) values

('zahtev za godisnji', '2021-01-11', '2021-03-30','nov', 'Godisnji odmor', 4,'ROLE_PHARMACIST', 'Kristijan Savic - ris')


