CREATE DATABASE WAREHOUSE;
USE WAREHOUSE;
-- Tạo bảng PRODUCT
CREATE TABLE PRODUCT (
    Product_Id CHAR(5) PRIMARY KEY,
    Product_Name VARCHAR(150) NOT NULL UNIQUE,
    Manufacturer VARCHAR(200) NOT NULL,
    Created DATE DEFAULT (CURRENT_DATE),
    Batch SMALLINT NOT NULL,
    Quantity INT NOT NULL DEFAULT 0,
    Product_Status BIT DEFAULT 1
);
DROP TABLE PRODUCT;
-- Tạo bảng EMPLOYEE
CREATE TABLE EMPLOYEE (
    Emp_Id CHAR(5) PRIMARY KEY,
    Emp_Name VARCHAR(100) NOT NULL,
    Birth_Of_Date DATE,
    Email VARCHAR(100) NOT NULL,
    Phone VARCHAR(100) NOT NULL,
    Address TEXT NOT NULL,
    Emp_Status SMALLINT NOT NULL
);
DROP TABLE EMPLOYEE;
-- Tạo bảng ACCOUNT
CREATE TABLE ACCOUNT (
    Acc_Id INT PRIMARY KEY AUTO_INCREMENT,
    User_Name VARCHAR(30) NOT NULL UNIQUE,
    Password VARCHAR(30) NOT NULL,
    Permission BIT DEFAULT 1,
    Emp_Id CHAR(5) NOT NULL UNIQUE,
    Acc_Status BIT DEFAULT 1,
    FOREIGN KEY (Emp_Id) REFERENCES EMPLOYEE(Emp_Id)
);
DROP TABLE ACCOUNT;
-- Tạo bảng BILL
CREATE TABLE BILL (
    Bill_id INT PRIMARY KEY AUTO_INCREMENT,
    Bill_Code VARCHAR(10) NOT NULL UNIQUE,
    Bill_Type BIT NOT NULL,
    Emp_Id_Created CHAR(5) NOT NULL,
    Created DATE DEFAULT (CURRENT_DATE),
    Emp_Id_Auth CHAR(5) NOT NULL,
    Auth_Date DATE DEFAULT (CURRENT_DATE),
    Bill_Status SMALLINT DEFAULT 0,
    FOREIGN KEY (Emp_Id_Created) REFERENCES EMPLOYEE(Emp_Id),
    FOREIGN KEY (Emp_Id_Auth) REFERENCES EMPLOYEE(Emp_Id)
);
DROP TABLE BILL;
-- Tạo bảng BILL_DETAIL
CREATE TABLE BILL_DETAIL (
    Bill_Detail_Id INT PRIMARY KEY AUTO_INCREMENT,
    Bill_Id INT NOT NULL,
    Product_Id CHAR(5) NOT NULL,
    Quantity INT NOT NULL CHECK (Quantity > 0),
    Price FLOAT NOT NULL CHECK (Price > 0),
    FOREIGN KEY (Bill_Id) REFERENCES BILL(Bill_Id),
    FOREIGN KEY (Product_Id) REFERENCES PRODUCT(Product_Id)
);
DROP TABLE BILL_DETAIL;

-- Xóa dữ liệu từ bảng PRODUCT
DELETE FROM PRODUCT;

-- Xóa dữ liệu từ bảng EMPLOYEE
DELETE FROM EMPLOYEE;

-- Xóa dữ liệu từ bảng ACCOUNT
DELETE FROM ACCOUNT;

-- Xóa dữ liệu từ bảng BILL
DELETE FROM BILL;

-- Xóa dữ liệu từ bảng BILL_DETAIL
DELETE FROM BILL_DETAIL;

-- Chèn dữ liệu vào bảng PRODUCT
INSERT INTO PRODUCT (Product_Id, Product_Name, Manufacturer, Batch, Quantity, Product_Status) 
VALUES 
('P0001', 'Laptop Lenovo ThinkPad', 'Lenovo', 1001, 50, 1),
('P0002', 'Smartphone Samsung Galaxy S20', 'Samsung', 1002, 30, 1),
('P0003', 'Tablet Apple iPad Air', 'Apple', 1003, 20, 0),
('P0004', 'Monitor Dell UltraSharp', 'Dell', 1004, 40, 1),
('P0005', 'Printer HP LaserJet Pro', 'HP', 1005, 25, 0),
('P0006', 'Wireless Mouse Logitech', 'Logitech', 1006, 35, 1),
('P0007', 'External Hard Drive', 'Seagate', 1007, 15, 0),
('P0008', 'Bluetooth Speaker JBL Flip', 'JBL', 1008, 45, 0),
('P0009', 'Gaming Keyboard', 'Razer', 1009, 10, 1),
('P0010', 'Camera Canon EOS Rebel', 'Canon', 1010, 20, 1),
('P0011', 'Headphones Sony', 'Sony', 1011, 30, 1),
('P0012', 'Smartwatch Fitbit Versa', 'Fitbit', 1012, 25, 1),
('P0013', 'Desktop Computer HP Pavilion', 'HP', 1013, 15, 1),
('P0014', 'Router TP-Link Archer', 'TP-Link', 1014, 20, 1),
('P0015', 'Graphics Card NVIDIA', 'NVIDIA', 1015, 5, 1);

-- Chèn dữ liệu vào bảng EMPLOYEE
INSERT INTO EMPLOYEE (Emp_Id, Emp_Name, Birth_Of_Date, Email, Phone, Address, Emp_Status) 
VALUES 
('E0001', 'John Doe', '1990-05-15', 'john.doe@example.com', '123456789', '123 Main St, City, Country', 0),
('E0002', 'Jane Smith', '1988-09-20', 'jane.smith@example.com', '987654321', '456 Elm St, City, Country', 0),
('E0003', 'Michael Johnson', '1995-03-10', 'michael.johnson@example.com', '456123789', '789 Oak St, City, Country', 1),
('E0004', 'Emily Brown', '1992-11-25', 'emily.brown@example.com', '789456123', '567 Pine St, City, Country', 1),
('E0005', 'David Wilson', '1985-07-12', 'david.wilson@example.com', '159357486', '901 Cedar St, City, Country', 2),
('E0006', 'Sarah Martinez', '1987-02-28', 'sarah.martinez@example.com', '357951468', '345 Maple St, City, Country', 2),
('E0007', 'Daniel Taylor', '1993-08-04', 'daniel.taylor@example.com', '852369147', '234 Birch St, City, Country', 1),
('E0008', 'Jessica Lee', '1989-12-30', 'jessica.lee@example.com', '369258147', '678 Walnut St, City, Country', 0),
('E0009', 'Christopher Garcia', '1991-06-17', 'christopher.garcia@example.com', '147258369', '890 Pineapple St, City, Country', 1),
('E0010', 'Ashley Rodriguez', '1994-10-05', 'ashley.rodriguez@example.com', '258369147', '123 Orange St, City, Country', 1),
('E0011', 'Matthew Hernandez', '1986-04-23', 'matthew.hernandez@example.com', '369147258', '456 Banana St, City, Country', 2),
('E0012', 'Amanda Martinez', '1990-01-08', 'amanda.martinez@example.com', '951357486', '789 Grape St, City, Country', 1),
('E0013', 'James Lopez', '1984-03-18', 'james.lopez@example.com', '753159426', '234 Cherry St, City, Country', 0),
('E0014', 'Jennifer Gonzalez', '1982-07-29', 'jennifer.gonzalez@example.com', '426951357', '567 Mango St, City, Country', 1),
('E0015', 'Robert Perez', '1983-11-11', 'robert.perez@example.com', '951426357', '890 Lemon St, City, Country', 1);

-- Chèn dữ liệu vào bảng ACCOUNT
INSERT INTO ACCOUNT (User_Name, Password, Permission, Emp_Id, Acc_Status) 
VALUES 
('admin', 'admin', 0, 'E0001', 1),
('user1', 'user1', 0, 'E0002', 1),
('user2', 'user2', 0, 'E0003', 1),
('user3', 'user3', 1, 'E0004', 1),
('user4', 'user4', 1, 'E0005', 0),
('user5', 'user5', 1, 'E0006', 1),
('user6', 'user6', 1, 'E0007', 1),
('user7', 'user7', 1, 'E0008', 0),
('user8', 'user8', 1, 'E0009', 0),
('user9', 'user9', 1, 'E0010', 1),
('user10', 'user10', 1, 'E0011', 0),
('user11', 'user11', 1, 'E0012', 1),
('user12', 'user12', 1, 'E0013', 0),
('user13', 'user13', 1, 'E0014', 1),
('user14', 'user14', 1, 'E0015', 1);

-- Chèn dữ liệu vào bảng BILL
INSERT INTO BILL (Bill_Code, Bill_Type, Emp_Id_Created, Emp_Id_Auth, Bill_Status) 
VALUES 
('B0001', 1, 'E0001', 'E0002', 1),
('B0002', 0, 'E0003', 'E0004', 1),
('B0003', 1, 'E0005', 'E0006', 0),
('B0004', 0, 'E0007', 'E0008', 1),
('B0005', 1, 'E0009', 'E0010', 0),
('B0006', 0, 'E0011', 'E0012', 2),
('B0007', 1, 'E0013', 'E0014', 0),
('B0008', 0, 'E0015', 'E0001', 1),
('B0009', 1, 'E0002', 'E0003', 0),
('B0010', 0, 'E0004', 'E0005', 2),
('B0011', 1, 'E0006', 'E0007', 2),
('B0012', 0, 'E0008', 'E0009', 1),
('B0013', 1, 'E0010', 'E0011', 0),
('B0014', 0, 'E0012', 'E0013', 1),
('B0015', 1, 'E0014', 'E0015', 0);

-- Chèn dữ liệu vào bảng BILL_DETAIL
INSERT INTO BILL_DETAIL (Bill_Id, Product_Id, Quantity, Price) 
VALUES 
(1, 'P0001', 2, 1200),
(1, 'P0002', 3, 800),
(2, 'P0003', 1, 500),
(2, 'P0004', 2, 3500),
(3, 'P0005', 4, 150),
(3, 'P0006', 1, 400),
(4, 'P0007', 3, 1300),
(4, 'P0008', 2, 800),
(5, 'P0009', 1, 140),
(5, 'P0010', 2, 750),
(6, 'P0011', 1, 300),
(6, 'P0012', 1, 250),
(7, 'P0013', 2, 650),
(7, 'P0014', 1, 80),
(8, 'P0015', 1, 700);

-- 1.Thống kê chi phí theo ngaỳ tháng năm
DELIMITER //
CREATE PROCEDURE CostByDate( out cost float , in date DATE ,in billtype boolean)
BEGIN
    SELECT 
        SUM(bd.Quantity * bd.Price) AS Total_Cost
    FROM BILL b
    JOIN BILL_DETAIL bd ON b.Bill_id = bd.Bill_Id
    WHERE b.Bill_STATUS = 2 AND b.BILL_TYPE = billtype AND b.AUTH_DATE = date
    GROUP BY b.AUTH_DATE;
END//
DELIMITER ;


-- 2.Thống kê chi phí theo khoảng thời gian
DELIMITER //
CREATE PROCEDURE CostByTimeRange(IN startDate DATE,IN endDate DATE)
BEGIN
    SELECT 
        DATE_FORMAT(b.Created, '%Y-%m-%d') AS Year_Month_Date,
        SUM(bd.Quantity * bd.Price) AS Total_Cost
    FROM BILL b
    JOIN BILL_DETAIL bd ON b.Bill_id = bd.Bill_Id
    JOIN PRODUCT p ON bd.Product_Id = p.Product_Id
    WHERE b.Created BETWEEN startDate AND endDate
    GROUP BY Year_Month_Date;
END//
DELIMITER ;


-- 3.Thống kê doanh thu theo ngày tháng năm 
DELIMITER //
CREATE PROCEDURE RevenueByDate()
BEGIN
    SELECT 
        DATE_FORMAT(b.Created, '%Y-%m-%d') AS Date,
        SUM(bd.Quantity * bd.Price) AS Revenue
    FROM BILL b
    JOIN BILL_DETAIL bd ON b.Bill_id = bd.Bill_Id
    JOIN PRODUCT p ON bd.Product_Id = p.Product_Id
    GROUP BY Date;
END//
DELIMITER ;
CALL RevenueByDate();


-- 4.Thống kê doanh thu theo khoảng thời gian
DELIMITER //
CREATE PROCEDURE RevenueByTimeRange(IN startDate DATE,IN endDate DATE)
BEGIN
    SELECT 
        DATE_FORMAT(b.Created, '%Y-%m-%d') AS Date,
        SUM(bd.Quantity * bd.Price) AS Revenue
    FROM BILL b
    JOIN BILL_DETAIL bd ON b.Bill_id = bd.Bill_Id
    JOIN PRODUCT p ON bd.Product_Id = p.Product_Id
    WHERE b.Created BETWEEN startDate AND endDate
    GROUP BY Date;
END//
DELIMITER ;
CALL RevenueByTimeRange('2024-01-01', '2024-03-31');


-- 5.Thống kê số nhân viên theo từng trạng thái
DELIMITER //
CREATE PROCEDURE EmployeeStatusCount()
BEGIN
    SELECT 
        CASE Emp_Status 
            WHEN 0 THEN 'Inactive' 
            WHEN 1 THEN 'Active' 
            ELSE 'Unknown' 
        END AS Employee_Status,
        COUNT(*) AS Employee_Count
    FROM EMPLOYEE
    GROUP BY Emp_Status;
END//
DELIMITER ;
CALL EmployeeStatusCount();



-- 6.Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian
DELIMITER //
CREATE PROCEDURE MostImportedProduct(IN startDate DATE,IN endDate DATE)
BEGIN
    SELECT 
        p.Product_Name,
        SUM(CASE WHEN b.Bill_Type = 1 THEN bd.Quantity ELSE 0 END) AS Total_Import_Quantity
    FROM BILL b
    JOIN BILL_DETAIL bd ON b.Bill_id = bd.Bill_Id
    JOIN PRODUCT p ON bd.Product_Id = p.Product_Id
    WHERE b.Created BETWEEN startDate AND endDate AND b.Bill_Type = 1
    GROUP BY bd.Product_Id
    ORDER BY Total_Import_Quantity DESC
    LIMIT 1;
END//
DELIMITER ;
CALL MostImportedProduct('2024-01-01', '2024-03-31');



-- 7.Thống kê sản phẩm nhập ít nhất trong khoảng thời gian
DELIMITER //
CREATE PROCEDURE LeastImportedProduct(IN startDate DATE,IN endDate DATE)
BEGIN
    SELECT 
        p.Product_Name,
        SUM(CASE WHEN b.Bill_Type = 1 THEN bd.Quantity ELSE 0 END) AS Total_Import_Quantity
    FROM BILL b
    JOIN BILL_DETAIL bd ON b.Bill_id = bd.Bill_Id
    JOIN PRODUCT p ON bd.Product_Id = p.Product_Id
    WHERE b.Created BETWEEN startDate AND endDate
    GROUP BY bd.Product_Id
    ORDER BY Total_Import_Quantity ASC
    LIMIT 1;
END//
DELIMITER ;
CALL LeastImportedProduct('2024-01-01', '2024-03-31');


-- 8.Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian
DELIMITER //
CREATE PROCEDURE MostExportedProduct(IN startDate DATE,IN endDate DATE)
BEGIN
    SELECT 
        p.Product_Name,
        SUM(CASE WHEN b.Bill_Type = 0 THEN bd.Quantity ELSE 0 END) AS Total_Export_Quantity
    FROM BILL b
    JOIN BILL_DETAIL bd ON b.Bill_id = bd.Bill_Id
    JOIN PRODUCT p ON bd.Product_Id = p.Product_Id
    WHERE b.Created BETWEEN startDate AND endDate AND b.Bill_Type = 0
    GROUP BY bd.Product_Id
    ORDER BY Total_Export_Quantity DESC
    LIMIT 1;
END//
DELIMITER ;
CALL MostExportedProduct('2024-01-01', '2024-03-31');



-- 9.Thống kê sản phẩm xuất ít nhất trong khoảng thời gian
DELIMITER //
CREATE PROCEDURE LeastExportedProduct(IN startDate DATE,IN endDate DATE)
BEGIN
    SELECT 
        p.Product_Name,
        SUM(CASE WHEN b.Bill_Type = 0 THEN bd.Quantity ELSE 0 END) AS Total_Export_Quantity
    FROM BILL b
    JOIN BILL_DETAIL bd ON b.Bill_id = bd.Bill_Id
    JOIN PRODUCT p ON bd.Product_Id = p.Product_Id
    WHERE b.Created BETWEEN startDate AND endDate
    GROUP BY bd.Product_Id
    ORDER BY Total_Export_Quantity ASC
    LIMIT 1;
END//
DELIMITER ;
CALL LeastExportedProduct('2024-01-01', '2024-03-31');

