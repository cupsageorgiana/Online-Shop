DROP  DATABASE mg_shop;
CREATE DATABASE IF NOT EXISTS mg_shop;

USE mg_shop;


CREATE TABLE IF NOT EXISTS Admin
(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nume varchar(30) unique,
username varchar(30) unique,
pass varchar(25));

CREATE TABLE IF NOT EXISTS Payment
(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
mode varchar(20) unique);


CREATE TABLE IF NOT EXISTS Customers
(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nume VARCHAR(30) NOT NULL,
tel VARCHAR(15) not null,
email VARCHAR(20) not null unique,
city varchar(20) not null,
Pass varchar(20) not null,
Username varchar(25) not null unique);

CREATE TABLE IF NOT EXISTS Bank_account
(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
acc_number varchar(30) unique,
id_customer int,
CONSTRAINT fk_Cust_Bank
	FOREIGN KEY (id_customer) REFERENCES Customers(id)
	ON DELETE  CASCADE
	ON UPDATE  CASCADE);
    
CREATE TABLE IF NOT EXISTS Category
(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
cname VARCHAR(30) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS Producer
(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
prodname VARCHAR(30) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS Products
(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
pname VARCHAR(30) NOT NULL unique,
price float,
description varchar(3000),
id_category INT,
id_producer INT,
CONSTRAINT fk_ProducerProduct
FOREIGN KEY(id_producer) REFERENCES Producer(id),
CONSTRAINT fk_CategoryProduct
FOREIGN KEY(id_category) REFERENCES Category(id));

CREATE TABLE IF NOT EXISTS Discounts
(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_product int,
new_price float,

CONSTRAINT fk_Prod_Discounts
FOREIGN KEY (id_product) REFERENCES Products(id)
	ON DELETE  CASCADE
	ON UPDATE  CASCADE);


CREATE TABLE IF NOT EXISTS Stock
(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nr_stock int,
cantity int,
id_product int,

CONSTRAINT fk_Stock_Product
FOREIGN KEY (id_product) REFERENCES Products(id)
	ON DELETE  CASCADE
	ON UPDATE  CASCADE);


CREATE TABLE IF NOT EXISTS Orders
(
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
id_customer int,
id_product int,
id_Pay int,
cantity int,
data date,

CONSTRAINT fk_Orders_Products
	FOREIGN KEY (id_product) REFERENCES Products(id)
	ON DELETE  CASCADE
	ON UPDATE  CASCADE,
    
CONSTRAINT fk_Orders_Customers
	FOREIGN KEY (id_customer) REFERENCES Customers(id)
	ON DELETE  CASCADE
	ON UPDATE  CASCADE,

CONSTRAINT fk_Orders_Pay
	FOREIGN KEY (id_Pay) REFERENCES Payment(id)
	ON DELETE  NO ACTION
	ON UPDATE  NO ACTION);


