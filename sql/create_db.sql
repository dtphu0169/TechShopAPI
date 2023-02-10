CREATE database TechShopDB CHARACTER SET utf8 COLLATE utf8_bin ;

use TechShopDB ;

create table account(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	user_name nvarchar(225) not null,
    phone nvarchar(11) null,
	email nvarchar(225) not null,
    role nvarchar(225) not null,
    active bool not null,
    password nvarchar(225) not null,
    register_date datetime not null
);

create table address(
	id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    account_id int(6) UNSIGNED not null,
    Provine_city nvarchar(225) not null,
	Dictrict nvarchar(225) not null,
	Ward nvarchar(225) not null,
	Detail nvarchar(225) not null,
    constraint fk_add foreign key(account_id)references account(id)
);

create table category(
	id int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name nvarchar(225) not null,
	image_path nvarchar(225) not null
);
   
create table label(
	id int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name nvarchar(225) not null,
	price_rate double null
);

create table product(
	id int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name nvarchar(255) not null,
	active bool not null,
	category_id int UNSIGNED not null,
	price int not null,
	label_id int UNSIGNED null,
	quantity int not null,
	decription nvarchar(1000) null,
	constraint fk_pro foreign key(category_id)references category(id),
		foreign key(label_id)references label(id)
);

create table image(
	id int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	product_id int(6) UNSIGNED not null,
	url nvarchar(225) null,
    constraint fk_img foreign key(product_id)references product(id)
	);
 
 create table cartproduct(
	id int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	account_id int UNSIGNED not null,
	product_id int UNSIGNED not null,
	quantity int not null,
	constraint fk_cart foreign key(account_id)references account(id),
		foreign key(product_id)references product(id)
);

create table feedback(
	id int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    account_id int UNSIGNED not null,
	product_id int UNSIGNED not null,
    comment nvarchar(500) null,
    star int not null,
    constraint fk_feed foreign key(account_id)references account(id),
		foreign key(product_id)references product(id)
);

create table bill(
	id int  UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	customer_name nvarchar(225) not null,
	datecreate datetime not null,
	price int not null,
	shipprice int not null,
	address nvarchar(225) not null,
	phone bigint not null,
	paid bool not null,
	status nvarchar(225) not null,
	note nvarchar(1000) null
);

create table bill_detail(
	id int UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	bill_id int UNSIGNED not null,
	product_id int UNSIGNED not null,
	quantity int not null,
    unit_price int not null,
	constraint fk_bill foreign key(bill_id)references bill(id),
		foreign key(product_id)references product(id)
);