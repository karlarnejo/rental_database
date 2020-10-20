/* ---------------------------------------------------------------------- */
/* Script generated with: DeZign for Databases V9.1.3                     */
/* Target DBMS:           PostgreSQL 9                                    */
/* Project file:          Rental.dez                                      */
/* Project name:                                                          */
/* Author:                                                                */
/* Script type:           Database creation script                        */
/* Created on:            2016-06-29 15:46                                */
/* ---------------------------------------------------------------------- */


/* ---------------------------------------------------------------------- */
/* Add tables                                                             */
/* ---------------------------------------------------------------------- */

/* ---------------------------------------------------------------------- */
/* Add table "Staff"                                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE Staff (
    StaffID INTEGER  NOT NULL,
    Fname CHARACTER VARYING(40),
    LName CHARACTER VARYING(40),
    StaffUsername CHARACTER VARYING(40),
    StaffPassword CHARACTER VARYING(40),
    CONSTRAINT PK_Staff PRIMARY KEY (StaffID)
);

/* ---------------------------------------------------------------------- */
/* Add table "Game"                                                       */
/* ---------------------------------------------------------------------- */

CREATE TABLE Game (
    GameID INTEGER  NOT NULL,
    GameName CHARACTER VARYING(100),
    Comments CHARACTER VARYING(250),
    DirPictures CHARACTER VARYING(1000),
    Availability BOOLEAN,
    Description CHARACTER VARYING(3000),
    CONSTRAINT PK_Game PRIMARY KEY (GameID)
);

/* ---------------------------------------------------------------------- */
/* Add table "Movie"                                                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE Movie (
    MovieID INTEGER  NOT NULL,
    MovieName CHARACTER VARYING(100)  NOT NULL,
    Comments CHARACTER VARYING(250),
    DirPictures CHARACTER VARYING(1000),
    Availability BOOLEAN,
    Description CHARACTER VARYING(3000),
    CONSTRAINT PK_Movie PRIMARY KEY (MovieID)
);

/* ---------------------------------------------------------------------- */
/* Add table "Customer"                                                   */
/* ---------------------------------------------------------------------- */

CREATE TABLE Customer (
    CustomerID INTEGER  NOT NULL,
    UserFName CHARACTER VARYING(40),
    UserLName CHARACTER VARYING(40),
    CustomerUsername CHARACTER VARYING(40),
    CustomerPassword CHARACTER VARYING(40),
    Age INTEGER,
    Address CHARACTER VARYING(40),
    Birthday DATE,
    CONSTRAINT PK_Customer PRIMARY KEY (CustomerID)
);

/* ---------------------------------------------------------------------- */
/* Add table "ProductRent"                                                */
/* ---------------------------------------------------------------------- */

CREATE TABLE ProductRent (
    RentID INTEGER  NOT NULL,
    DateBorrowed DATE,
    DateDue DATE,
    StaffID INTEGER  NOT NULL,
    CustomerID INTEGER  NOT NULL,
    MovieID INTEGER,
    GameID INTEGER,
    CONSTRAINT PK_ProductRent PRIMARY KEY (RentID)
);

/* ---------------------------------------------------------------------- */
/* Add foreign key constraints                                            */
/* ---------------------------------------------------------------------- */

ALTER TABLE ProductRent ADD CONSTRAINT Staff_ProductRent 
    FOREIGN KEY (StaffID) REFERENCES Staff (StaffID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ProductRent ADD CONSTRAINT Customer_ProductRent 
    FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ProductRent ADD CONSTRAINT Movie_ProductRent 
    FOREIGN KEY (MovieID) REFERENCES Movie (MovieID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE ProductRent ADD CONSTRAINT Game_ProductRent 
    FOREIGN KEY (GameID) REFERENCES Game (GameID) ON DELETE CASCADE ON UPDATE CASCADE;
