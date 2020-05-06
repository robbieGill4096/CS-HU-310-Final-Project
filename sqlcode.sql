Create Database FinalProject;
USE FinalProject;


CREATE TABLE Item
(
ID int auto_increment,
ItemCode varchar(10) NOT NULL,
UNIQUE (ItemCode),
ItemDescription varchar(50),
Price decimal(4,2) DEFAULT 0,
primary key(id)
);

CREATE TABLE Purchase
(
ID int auto_increment,
ItemID int NOT NULL,
Quantity int NOT NULL,
PurchaseDate datetime DEFAULT CURRENT_TIMESTAMP,
primary key(id),
FOREIGN KEY (ID) REFERENCES Item(ID)
);

CREATE TABLE Shipment
(
ID int auto_increment,
ItemID int NOT NULL,
Quantity int NOT NULL,
ShipmentDate date NOT NULL UNIQUE,
primary key(id),
FOREIGN KEY (ID) REFERENCES Item(ID)
);
