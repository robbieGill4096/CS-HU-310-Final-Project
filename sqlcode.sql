Create Table Item
(ID int auto_increment,
ItemCode varchar(10) NOT NULL UNIQUE,
ItemDescription varchar(50),
Price decimal(4,2) DEFAULT 0,
Primary key (ID));


Create Table Purchase
(ID int auto_increment, 
ItemID int NOT NULL,
Quantity int NOT NULL,
PurchaseDate datetime default now(),
Primary key (ID));

Alter table Purchase
ADD FOREIGN KEY (ItemID) REFERENCES Item(ID);

Create Table Shipment
(ID int auto_increment,
ItemID int NOT NULL,
Quantity int NOT NULL,
ShipmentDate date NOT NULL UNIQUE,
Primary key (ID));

Alter table Shipment
ADD FOREIGN KEY (ItemID) REFERENCES Item(ID);

Delimiter $$           
CREATE PROCEDURE CreateItem(itemCode varchar(10), itemDesc varchar(50), itempri decimal(4,2))
BEGIN
	Insert Into Item (ItemCode, ItemDescription, Price)
	Values (itemCode, itemDesc, itempri);
END;
$$


Delimiter $$
Create Procedure CreatePurchase(itemCode varchar(10), Quan int)
BEGIN
    INSERT INTO Purchase(Quantity, ItemID)
    SELECT Quan as Quantity, Item.ID as ItemID
    FROM Item
    WHERE Item.ItemCode = itemCode;
END;
$$

Delimiter $$
Create Procedure CreateShipment(item_Code varchar(10), shipment_Quantity int,  shipment_Date Date)
BEGIN
	INSERT INTO Shipment(ItemID, Quantity, ShipmentDate)
    SELECT Item.ID as ItemID, shipment_Quantity as Quantity, shipment_Date as ShipmentDate
    FROM Item
    WHERE Item.ItemCode = item_Code;
END;
$$

Delimiter $$
CREATE PROCEDURE DeleteItem(item_Code varchar(10))
BEGIN
DELETE  
from Item
WHERE (Item.ItemCode like item_Code) and Item.ID not IN (select ItemID from Purchase where Purchase.ItemID = Item.ID ) 
and Item.ID not IN (select ItemID from Shipment where Shipment.ItemID = Item.ID );
END;
$$

Delimiter $$
CREATE PROCEDURE DeletePurchase(item_Code varchar(10))
BEGIN
DELETE  
from Purchase
where item_Code IN (select ItemCode from Item where Item.ID = Purchase.ItemID)
order by PurchaseDate desc
Limit 1;
END;
$$

Delimiter $$
CREATE PROCEDURE DeleteShipment(item_Code varchar(10))
BEGIN
DELETE  
from Shipment
where item_Code IN (select ItemCode from Item where Item.ID = Shipment.ItemID)
order by ShipmentDate desc
Limit 1;
END;
$$

Delimiter $$
CREATE PROCEDURE GetItems(item_Code varchar(10))
BEGIN
  IF item_Code like '%\%' THEN
  select * from Item;
  ELSE
  select * from Item Where ItemCode = item_Code;
  END IF;
END;
$$

Delimiter $$
CREATE PROCEDURE GetPurchases(item_Code varchar(10))
BEGIN
  IF item_Code = '%\%' THEN
  select * from Purchase;
  ELSE
  select * 
  from Purchase
  where (select ItemCode from Item where Item.ID = Purchase.ItemID) = item_Code;
  END IF;
END;
$$

Delimiter $$
CREATE PROCEDURE GetShipments (item_Code varchar(10))
BEGIN
  IF item_Code = '%\%' THEN
  select * from Shipment;
  ELSE
  select * 
  from Shipment
  where (select ItemCode from Item where Item.ID = Shipment.ItemID) = item_Code;
  END IF;
END;
$$

Delimiter $$
CREATE PROCEDURE ItemsAvailable(item_Code varchar(10))
BEGIN
  IF item_Code = '%\%'THEN
  SELECT i.ItemCode, i.ItemDescription, IFNULL((sum(ItemShip.Quantity) - sum(ItemPurch.Quantity)),0) as totalQuantity
  FROM Item i
  LEFT JOIN Purchase ItemPurch ON i.ID = ItemPurch.ItemID  
  LEFT JOIN Shipment as ItemShip ON i.ID = ItemShip.ItemID
  group by i.ItemCode;
  ELSE
  SELECT i.ItemCode, i.ItemDescription, IFNULL((sum(ItemShip.Quantity) - sum(ItemPurch.Quantity)), 0) as totalQuantity
  FROM Item i
  LEFT JOIN Purchase ItemPurch ON i.ID = ItemPurch.ItemID  
  LEFT JOIN Shipment as ItemShip ON i.ID = ItemShip.ItemID 
  where i.ItemCode like item_Code
  group by i.ItemCode;
  END IF;
END;
$$
 
Delimiter $$
CREATE PROCEDURE UpdateItem (item_Code varchar(10), item_price int)
BEGIN
UPDATE Item
SET Item.Price = item_price
WHERE Item.ItemCode = item_Code;
END;
$$
