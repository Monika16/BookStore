CREATE TABLE category (

	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id) 

);

CREATE TABLE user_detail (
	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(50),
	email VARCHAR(100),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY(id),
);

CREATE TABLE product (
	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	author VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id),	
);	


CREATE TABLE address (
	id IDENTITY,
	user_id int,
	address_line_one VARCHAR(100),
	address_line_two VARCHAR(100),
	city VARCHAR(20),
	state VARCHAR(20),
	country VARCHAR(20),
	postal_code VARCHAR(10),
	is_billing BOOLEAN,
	is_shipping BOOLEAN,
	CONSTRAINT fk_address_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_address_id PRIMARY KEY (id)
);

-- the cart table to store the user cart top-level details
CREATE TABLE cart (
	id IDENTITY,
	user_id int,
	grand_total DECIMAL(10,2),
	cart_lines int,
	CONSTRAINT fk_cart_user_id FOREIGN KEY (user_id ) REFERENCES user_detail (id),
	CONSTRAINT pk_cart_id PRIMARY KEY (id)
);


INSERT into category (name,description,image_url,is_active)
		values ('Thrillers','It is about Mystery!','CAT_1.png',true);
INSERT into category (name,description,image_url,is_active)
		values ('Fiction','It is about Science Fiction!','CAT_2.png',true);
INSERT into category (name,description,image_url,is_active)
		values ('Drama','It is about Drama!','CAT_3.png',true);
		
INSERT into user_detail (first_name,last_name,role,enabled,password,email,contact_number) values
	('Monika','Thokala','ADMIN',true,'admin','m@gmail.com','206-897-3456');
INSERT into user_detail (first_name,last_name,role,enabled,password,email,contact_number) values
	('Sam','Jones','SUPPLIER',true,'1234','s@gmail.com','206-123-4561');
INSERT into user_detail (first_name,last_name,role,enabled,password,email,contact_number) values
	('Arya','Staples','SUPPLIER',true,'1234','a@gmail.com','206-887-9012');

INSERT into address (user_id,adress_line_one,address_line_two,city,state,country,postal_code,is_billing,is_shipping) values
	(2,'No.234','Snow St.','Edission','New Jersey','US','93501',true,false);

INSERT into cart(user_id, grand_total, cart_lines) VALUES (null, 0, 0);


INSERT into product (code,name,author,description,unit_price,quantity,is_active,category_id,supplier_id) values
	('PRA123ABCDEFX','Nineteen Eighty-Four','George Orwell','Nineteen Eighty-Four is set in Oceania, one of three inter-continental superstates that divided the world after a global war',20.55,10,true,2,2);
INSERT into product (code,name,author,description,unit_price,quantity,is_active,category_id,supplier_id) values
	('PRA123DEFDEFX','Dune','Frank Herbert','The story explores the multi-layered interactions of politics, religion, ecology, technology, and human emotion.',15.55,20,true,2,3);
INSERT into product (code,name,author,description,unit_price,quantity,is_active,category_id,supplier_id) values
	('PRA123GHIDEFX','Ender Game','Orson Scott Card','It is a 1985 military science fiction novel',150,7,true,2,3);
INSERT into product (code,name,author,description,unit_price,quantity,is_active,category_id,supplier_id) values
	('PRA123ADCFESX','The Girl on the Train','Paula Hawkins','It is a psychological thriller',25,6,true,1,2);
INSERT into product (code,name,author,description,unit_price,quantity,is_active,category_id,supplier_id) values
	('PRA123ZQWFESX','The Da Vinci Code','Dan Brown','It follows symbologist Robert Langdon and cryptologist Sophie Neveu after a murder in the Louvre Museum in Paris',12.34,15,true,1,3);
INSERT into product (code,name,author,description,unit_price,quantity,is_active,category_id,supplier_id) values
	('PRA123SWRFESX','Killing Floor','Lee Child','The book won the Anthony Award and Barry Award for best first novel',300,12,true,1,2);
INSERT into product (code,name,author,description,unit_price,quantity,is_active,category_id,supplier_id) values
	('PRA123ASDSWRD','Drama','Raina Telgemeier','The story focuses on Callie, a middle schooler and theater-lover who works in her schools drama production crew',30,20,true,3,2);
INSERT into product (code,name,author,description,unit_price,quantity,is_active,category_id,supplier_id) values
	('PRA123BCDSWRD','Hamllet','RWilliam Shakesphere','The Tragedy of Hamlet, Prince of Denmark, often shortened to Hamlet, is a tragedy',20,5,true,3,3);
INSERT into product (code,name,author,description,unit_price,quantity,is_active,category_id,supplier_id) values
	('PRA123RWDSWRD','Othelo','RWilliam Shakesphere','It is based on the story Un Capitano Moro by Cinthio, a disciple of Boccaccio',200,5,true,3,2);