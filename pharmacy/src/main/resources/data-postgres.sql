INSERT INTO AUTHORITY (name) VALUES ('ROLE_SYSADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_ADMIN');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO AUTHORITY (name) VALUES ('ROLE_SUPPLIER');

insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date) values ('ROLE_SYSADMIN','Aleksa','Milenovic','aleksaMilenovic','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','aleska@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);

INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE01', 'Paracetamol', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');
INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE02', 'Pandol', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');
INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE03', 'Aspirin', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');
INSERT INTO medicine (code, name, type, shape, composition, manufacturer, prescription_requirement, additional_notes, evaluation_grade, loyalty_score, contraindications, rdiot) values ('N02BE04', 'Analgin', 'Analgetik', 'Tablete' ,'Sastav', 'Srbija', TRUE, 'Radi bilo koji posao', 0.0 , 0, 'Nema', '2');

INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka A', 'Srbija', 'Novi Sad', 'Narodnog fronta 10', 'Opis apoteke A', 2.0, 1000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka B Merkator', 'Srbija', 'Novi Sad', 'Bulevar Oslobođenja 102 ', 'Opis Apoteke B', 0.0, 2000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka C', 'Srbija', 'Novi Sad', 'Narodnog fronta 10', 'Opis Apoteke C', 1.0, 3000.0);
INSERT INTO pharmacy (name, country, city, address, pharmacy_description, evaluation_grade, counseling_price) values ('Apoteka D', 'Srbija', 'Novi Sad', 'Bulevar Mihajla Pupina 9', 'Opis Apoteke D', 5.0, 1000.0);

insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, evaluation_grade) values ('ROLE_DERMATOLOGIST','Aleksa','Milenovic','Derma','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','aleska1@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07', 1);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id) values ('ROLE_ADMIN','Branislav','Lazarevic','slav','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','branislavlazarevic@gmail.com','Srbija','Sabac','Marsala Tita 140',true, '2021-01-02 18:57:58.508-07',1);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id,evaluation_grade) values ('ROLE_PHARMACIST','Kristijan','Savic','ris','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','risfizika@gmail.com','Srbija','Jagodina','despotovac',true, '2021-01-02 18:57:58.508-07',1,0);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date,loyalty_score, penalty_score) values ('ROLE_USER','Zorana','Stamenkovic','zor','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','zoranastamenkovic@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07',0,0);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, evaluation_grade) values ('ROLE_DERMATOLOGIST','Dermatolog','Dermatologovic','Derma99','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','aleska11@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07', 1);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id,evaluation_grade) values ('ROLE_PHARMACIST','Farmaceut','farmaceutic','ris99','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','risfizidsfka@gmail.com','Srbija','Jagodina','despotovac',true, '2021-01-02 18:57:58.508-07',null ,0);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id) values ('ROLE_ADMIN','Branislav','Lazarevic','slav12','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','branislavlazarevic12@gmail.com','Srbija','Sabac','Marsala Tita 140',true, '2021-01-02 18:57:58.508-07',1);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, evaluation_grade) values ('ROLE_SUPPLIER','Aleksa','Milenovic','sup','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','supleksa@gmail.com','Srbija','Novi Sad','Negde u NS',true, '2021-01-02 18:57:58.508-07', 6);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id,evaluation_grade) values ('ROLE_PHARMACIST','Pera','Savic','ris11','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','risfifdfszika@gmail.com','Srbija','Jagodina','despotovac',true, '2021-01-02 18:57:58.508-07',3,0);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id,evaluation_grade) values ('ROLE_PHARMACIST','Kristijan','Peric','ris12','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','risfizfdsfdsfdsfsdika@gmail.com','Srbija','Jagodina','despotovac',true, '2021-01-02 18:57:58.508-07',3,2);
insert into registeredusers (tip,first_name,last_name,username,password,email,country,city,address, enabled, last_password_reset_date, pharmacy_id,evaluation_grade) values ('ROLE_PHARMACIST','Pera','Peric','ris13','$2a$10$gIodpuRGSH.7RUeBAMtX2OCrsSdqjBlRuLdTeqz5svZZcKHoQSwUK','risffsfsfizika@gmail.com','Srbija','Jagodina','despotovac',true, '2021-01-02 18:57:58.508-07',3,4);

insert into absence_request(request_text, start_date,end_date, status,type_of_absence, employee_id,type_of_employee, employee_name,pharmacy_id) values ('zahtev za godisnji', '2021-01-11', '2021-03-30','nov', 'Godisnji odmor', 4,'ROLE_PHARMACIST', 'Kristijan Savic - ris',1);
insert into absence_request(request_text, start_date,end_date, status,type_of_absence, employee_id,type_of_employee, employee_name,pharmacy_id) values ('zahtev za godisnji', '2021-05-05', '2021-12-12','Odobreno', 'Godisnji odmor', 4,'ROLE_PHARMACIST', 'Kristijan Savic - ris',1);

insert into pharmacy_dermatologist(pharmacy_id, dermatologist_id) values (1,2);

insert into pharmacy_pharmacists(pharmacy_id, pharmacists_id) values(1,4);
insert into pharmacy_pharmacists(pharmacy_id, pharmacists_id) values(2,7);

insert into working_hours(end_time,start_time, work_day, dermatologist_id, pharmacy_id) values ('15:00:00.000','08:00:00.000','Ponedeljak',2,1);
insert into working_hours(end_time,start_time, work_day, dermatologist_id, pharmacy_id) values ('15:00:00','08:00:00','Ponedeljak', 6,2);
insert into working_hours_pharmacist(end_time,start_time, work_day, pharmacist_id, pharmacy_id) values ('23:00:00','15:00:00','Ponedeljak', 4, 1);
insert into working_hours_pharmacist(end_time,start_time, work_day, pharmacist_id, pharmacy_id) values ('23:00:00','15:00:00','Ponedeljak', 7, 2);
insert into working_hours_pharmacist(end_time,start_time, work_day, pharmacist_id, pharmacy_id) values ('23:00:00','15:00:00','Ponedeljak', 10, 3);
insert into working_hours_pharmacist(end_time,start_time, work_day, pharmacist_id, pharmacy_id) values ('23:00:00','15:00:00','Ponedeljak', 11, 3);
insert into working_hours_pharmacist(end_time,start_time, work_day, pharmacist_id, pharmacy_id) values ('23:00:00','15:00:00','Ponedeljak', 12, 3);


insert into promotion(title,text,start_date,end_date,type,pharmacy_id, patient_id) values('Bekutan promocija','Za sve bekutan proizvode vazi promocija 2+1','2021-01-11','2021-07-30','promocija',1, 5);

insert into examination(date,duration_minutes,is_free,price,dermatologist_id,pharmacy_id,loyalty_score) values ('2021-06-07 08:00:00',30,false,400,2,1,0);
insert into examination(date,duration_minutes,is_free,price,dermatologist_id,pharmacy_id,loyalty_score) values ('2021-06-07 10:00:00',30,false,400,2,1,0);

insert into medicine_quantity_pharmacy(quantity,medicine_id,pharmacy_id) values (5,1,1);
insert into medicine_quantity_pharmacy(quantity,medicine_id,pharmacy_id) values (5,2,1);
insert into medicine_quantity_pharmacy(quantity,medicine_id,pharmacy_id) values (5,3,1);

insert into purchase_order(create_date,end_date,price,status,pharmacy_admin_id,pharmacy_id) values ('2021-05-05','2021-06-06',0,'created',3,1);

insert into medicine_quantity_order(quantity,medicineID,purchase_order_id) values(1,5,1);
insert into medicine_quantity_order(quantity,medicineID,purchase_order_id) values(2,7,1);
insert into medicine_quantity_order(quantity,medicineID,purchase_order_id) values(3,5,1);


insert into bid(end_date,price,status,purchase_order_id,supplier_id) values('2021-06-06',1000,'obrada',1,9);
insert into bid(end_date,price,status,purchase_order_id,supplier_id) values('2021-06-07',1250,'obrada',1,9);
insert into bid(end_date,price,status,purchase_order_id,supplier_id) values('2021-08-08',1300,'obrada',1,9);
insert into bid(end_date,price,status,purchase_order_id,supplier_id) values('2021-07-07',1750,'obrada',1,9);

insert into purchase_order(create_date,end_date,price,status,pharmacy_admin_id,pharmacy_id) values ('2021-05-05','2021-07-07',0,'created',3,1);
insert into purchase_order(create_date,end_date,price,status,pharmacy_admin_id,pharmacy_id) values ('2021-05-05','2021-07-07',0,'created',3,1);



insert into bid(end_date,price,status,purchase_order_id,supplier_id) values('2021-08-07',1000,'obrada',2,9);
insert into bid(end_date,price,status,purchase_order_id,supplier_id) values('2021-07-08',1250,'obrada',2,9);
insert into bid(end_date,price,status,purchase_order_id,supplier_id) values('2021-07-09',1300,'obrada',2,9);
insert into bid(end_date,price,status,purchase_order_id,supplier_id) values('2021-07-08',1750,'obrada',2,9);

insert into examination(date,duration_minutes,is_free,price,dermatologist_id,pharmacy_id,loyalty_score) values ('2021-08-31 08:00:00',30,false,400,2,1,0);
insert into examination(date,duration_minutes,is_free,price,dermatologist_id,pharmacy_id,loyalty_score) values ('2021-08-31 10:00:00',30,false,400,2,1,0);

insert into examination(date,duration_minutes,is_free,price,dermatologist_id,pharmacy_id,loyalty_score) values ('2021-08-14 08:00:00',30,true,400,2,1,0);
insert into examination(date,duration_minutes,is_free,price,dermatologist_id,pharmacy_id,loyalty_score) values ('2021-08-10 10:00:00',30,true,400,2,1,0);

insert into examination(date,duration_minutes,is_free,penalty,price,dermatologist_id,patient_id, pharmacy_id,loyalty_score) values ('2021-06-07 10:00:00',30,false,false,400,2,5,1,0);
insert into examination(date,duration_minutes,is_free,penalty,price,dermatologist_id,patient_id, pharmacy_id,loyalty_score) values ('2021-06-08 10:00:00',40,false,false,500,2,5,1,0);

insert into reservation(end_date, pick_up_date, picked_up, price, patient_id, pharmacy_id) values ('2021-06-05','2021-06-05', true, 1000, 5,1);
insert into medicine_quantity_reservation(quantity,medicine_id,reservation_id) values (10,1,1);
insert into medicine_quantity_reservation(quantity,medicine_id,reservation_id) values (10,2,1);

insert into reservation_medicineis values (1,1);
insert into reservation_medicineis values (1,2);


insert into loyalty_program values (1,0.01, 0, 'clan', 100);
insert into loyalty_program values (2,1.0, 101, 'bronzani', 500);
insert into loyalty_program values (3,3.0, 501, 'zlatni' ,99999);

insert into counseling(date,duration_minutes,free,penalty,price,pharmacist_id,patient_id,loyalty_score) values ('2021-06-07 10:00:00',30,false,true,400,4,5,0);
insert into counseling(date,duration_minutes,free,penalty,price,pharmacist_id,patient_id,loyalty_score) values ('2021-06-08 10:00:00',30,false,true,500,7,5,0);
