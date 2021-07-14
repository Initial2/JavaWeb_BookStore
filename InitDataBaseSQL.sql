DROP DATABASE IF EXISTS books


CREATE DATABASE IF NOT EXISTS books

USE books


DROP TABLE IF EXISTS `user`

create table `user`(
		id INT PRIMARY KEY AUTO_INCREMENT,
		 username VARCHAR(12) UNIQUE NOT Null,
		`password`  VARCHAR(12)  NOT NULL,
		email  VARCHAR(50)
);


INSERT INTO 
	`user`(username,`password`,email) 
VALUES
	("sufang123","123456","123576@126.com");
	
	
#SELECT * FROM user
	
#delete FROM `user`	
	

DROP TABLE IF EXIsTs book


create table IF NOT EXISTS book(
		id int(11) PRIMARY KEY AUTO_INCREMENT,
		`name` VARCHAR(100) NOT NULL,
		author VARCHAR(100) NOT NULL,
		price  DECIMAL(11,2) NOT NULL,
		sales int(11)  NOT NULL,
		stock int(11)  NOT NULL ,
		img_path VARCHAR(100)  NOT NULL
			
);


## 插入初始化测试数据
insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , 'java从入门到放弃' , '国哥' , 80 , 9999 , 9 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '数据结构与算法' , '严敏君' , 78.5 , 6 , 13 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '怎样拐跑别人的媳妇' , '龙伍' , 68, 99999 , 52 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '木虚肉盖饭' , '小胖' , 16, 1000 , 50 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , 'C++编程思想' , '刚哥' , 45.5 , 14 , 95 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '蛋炒饭' , '周星星' , 9.9, 12 , 53 , 'static/img/default.jpg');
 
insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '赌神' , '龙伍' , 66.5, 125 , 535 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , 'Java编程思想' , '阳哥' , 99.5 , 47 , 36 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , 'JavaScript从入门到精通' , '婷姐' , 9.9 , 85 , 95 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , 'cocos2d-x游戏编程入门' , '国哥' , 49, 52 , 62 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , 'C语言程序设计' , '谭浩强' , 28 , 52 , 74 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , 'Lua语言程序设计' , '雷丰阳' , 51.5 , 48 , 82 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '西游记' , '罗贯中' , 12, 19 , 9999 , 'static/img/default.jpg');

insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '水浒传' , '华仔' , 33.05 , 22 , 88 , 'static/img/default.jpg');
 
insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '操作系统原理' , '刘优' , 133.05 , 122 , 188 , 'static/img/default.jpg');
 
insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '数据结构 java版' , '封大神' , 173.15 , 21 , 81 , 'static/img/default.jpg');
 
insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , 'UNIX高级环境编程' , '乐天' , 99.15 , 210 , 810 , 'static/img/default.jpg');
 
insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , 'javaScript高级编程' , '国哥' , 69.15 , 210 , 810 , 'static/img/default.jpg');
 
insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '大话设计模式' , '国哥' , 89.15 , 20 , 10 , 'static/img/default.jpg');
 
insert into book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) 
values(null , '人月神话' , '刚哥' , 88.15 , 20 , 80 , 'static/img/default.jpg');
 


## 查看表内容
#select id,name,author,price,sales,stock,img_path from book;





#订单表
USE  books
DROP TABLE IF EXISTS `order`
CREATE table if NOT EXISTS `order`(
	`order_id`  VARCHAR(50) PRIMARY key,	
		`create_time` TIMESTAMP,
		`price` DECIMAL(11,2),
		`status`  int,
	  `user_id` INT ,	
		FOREIGN KEY(user_id) REFERENCES `user`(id)
)


DROP TABLE IF EXISTS order_item

CREATE table if NOT EXISTS order_item(
		 id	INT PRIMARY KEY auto_increment,
		`name` VARCHAR(50) NOT NULL,
		`count` INT NOT NULL,
		`price` DECIMAL(11,2)  NOT NULL,
		`total_price`  DECIMAL(11,2)  NOT NULL,
		`order_id`  VARCHAR(50) NOT NULL,
		
		FOREIGN KEY(order_id) REFERENCES `order`(order_id)

)



