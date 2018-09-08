--------------------------------------------------------
-- Archivo creado  - viernes-agosto-31-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SERIES
--------------------------------------------------------

  CREATE TABLE SERIES 
   (ID INTEGER NOT NULL, 
    COUNTRY VARCHAR2(255), 
    DATERELEASED DATE, 
    NUMBEROFEPISODES VARCHAR2(255), 
    TITLE VARCHAR2(255), 
    PRICE FLOAT(126), 
    HOURS INTEGER,
    CONSTRAINT SERIES_PK PRIMARY KEY
    (
       ID
    ));
    COMMIT;

Insert into SERIES (ID,COUNTRY,DATERELEASED,NUMBEROFEPISODES,TITLE,PRICE,HOURS) values (1,'USA',to_date('05/03/13','DD/MM/RR'),'99','LOST',10203040.9999,120);
Insert into SERIES (ID,COUNTRY,DATERELEASED,NUMBEROFEPISODES,TITLE,PRICE,HOURS) values (2,'New Zeland',to_date('10/07/14','DD/MM/RR'),'80','Game Of Thrones',999999.8888,80);
Insert into SERIES (ID,COUNTRY,DATERELEASED,NUMBEROFEPISODES,TITLE,PRICE,HOURS) values (3,'USA',to_date('17/12/16','DD/MM/RR'),'100','The Walking Dead',515.36,77);
Insert into SERIES (ID,COUNTRY,DATERELEASED,NUMBEROFEPISODES,TITLE,PRICE,HOURS) values (4,'USA',to_date('13/11/13','DD/MM/RR'),'50','Breaking Bad',2387535.145,70);
Insert into SERIES (ID,COUNTRY,DATERELEASED,NUMBEROFEPISODES,TITLE,PRICE,HOURS) values (5,'MEX',to_date('17/12/81','DD/MM/RR'),'13','Sense 8',696969.69,24);
Insert into SERIES (ID,COUNTRY,DATERELEASED,NUMBEROFEPISODES,TITLE,PRICE,HOURS) values (6,'USA',to_date('01/02/15','DD/MM/RR'),'15','Better call Saul',12345.54321,18);
Insert into SERIES (ID,COUNTRY,DATERELEASED,NUMBEROFEPISODES,TITLE,PRICE,HOURS) values (7,'England',to_date('20/05/14','DD/MM/RR'),'20','Sherlock',15.12345,50);
Insert into SERIES (ID,COUNTRY,DATERELEASED,NUMBEROFEPISODES,TITLE,PRICE,HOURS) values (8,'USA',to_date('08/08/01','DD/MM/RR'),'3','Matrix',3.14159,8);
 COMMIT;
