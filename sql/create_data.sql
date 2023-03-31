-- account
INSERT INTO Account
VALUES(1,'admin','0000000000','admin@admin.com',"ROLE_ADMIN",1,'$2a$12$H3v6EAQJEQsjBQr9zKbtee/bcq4xohDhBIpx4et1dp.lFerwmH3x2','2022-12-05',null);
INSERT INTO Account
VALUES(12,'nam','0123458468','nam@gmail.com',"ROLE_USER",1,'$2a$12$3HCqxqyI2luk1T1IXJFK6O0mBcpGRCBwmB7dRvgnAkt.94eC.KQQy','2023-01-05',null);
INSERT INTO Account
VALUES(15,'binh',null,'binh123@gmail.com',"ROLE_USER",1,'$2a$12$oh7xsOqW0HI8KMAlDq7Xb.mVW8q4qGbc1ugGswzBzgWaBdna.D1WC','2022-12-05',null);

-- address
INSERT INTO `address` (`id`,`account_id`, `provine_city`, `dictrict`, `ward`, `detail`) VALUES
                                                                                            (1001,1, 'TP.HCM', 'Quận 1', 'Phường Cầu Ông Lãnh', '32 Phan Văn Trường'),
                                                                                            (1002,12, 'Hà Nội', 'Quận Đống Đa', 'Phường Thô Quan', '62 Nguyễn Văn Chương'),
                                                                                            (1004,15, 'TP.HCM', 'TP Thủ Đức', 'Phường Linh Trung', '208 Hoàng Diệu 2'),
                                                                                            (1005,15, 'TP.HCM', 'Quận 12', 'Phường Tân Thới Nhất', '246 Phan Văn Hới');

-- category
INSERT INTO `category`(`id`,`name`,`image_path`) VALUES
                                                     (1001,'flycam', 'https://i.ibb.co/9VXrByb/product1002-1.jpg'),
                                                     (1002,'robot', 'https://i.ibb.co/Y36g0Sz/product1003-2.jpg'),
                                                     (1003,'camera', 'https://i.ibb.co/sFmdZ7D/product1004-3.jpg'),
                                                     (1004,'play station', 'https://i.ibb.co/sbzvFJX/product1005-3.jpg');

-- label
INSERT INTO `label`(`id`,`name`,`price_rate`) VALUES
                                                  (1001,'new',1),
                                                  (1002,'sale 50%',0.5);

-- product
INSERT INTO `product`
(`id`,`name`,`active`,`category_id`,`price`,`label_id`,`quantity`,`decription`)
VALUES
    (1001,'DJI Mavic 3',true,1001,46900000,null,10
    ,'DJI Mavic 3 được DJI ra mắt với thiết kế hoàn toàn mới và là sự cải tiến toàn diện nhất so với các flycam khác của nhà DJI. Với hai camera được tích hợp, trang bị cảm biến CMOS 4/3inch, DJI Mavic 3 cho phép bạn có thể thực hiện những cảnh quay trên không chuyên nghiệp. '),
    (1002,'Flycam DJI Mini SE',true,1001,77900000,null,15
    ,'Có thiết kế tương tự chiếc DJI Mavic Mini trước đó, DJI Mini SE được ra mắt với mức giá rẻ hơn nhưng vẫn sở hữu hàng loạt những tính năng nổi bật của một chiếc Flycam chất lượng cao, là thiết bị bay phù hợp với những người mới bắt đầu và chưa có nhiều kinh nghiệm bay Flycam.'),
    (1003,'Robot Anki cozmo',true,1002,6500000,null,10
    ,'Khả năng đặc biệt của robot thông minh vui nhộn Cozmo này là có thể ghi nhớ gương mặt bạn qua thời gian, có thể vui đùa với các món đồ chơi riêng hay các món đồ chơi công nghệ khác.'),
    (1004,'GoPro Hero 11 Black Combo Vlog',true,1003,21200000,1001,10
    ,'GoPro Hero 11 Black trang bị các tính năng kỹ thuật số cải tiến, cảm biến ảnh mới 27MP và kết nối Wi-Fi, mang đến khả năng quay phim ở góc nhìn thứ nhất kịch tính với độ phân giải đến 5.3K60.'),
    (1005,'PlayStation 5 / PS5 Standard Edition',true,1004,14200000,null,10
    ,'PS5 sở hữu thiết kế rất mềm mại với những đường cong ấn tượng, không vuông vức "đậm chất Sony" như PS4, hay Sony Xperia...bắt mắt với tông màu trắng chủ đạo kết hợp màu đen bên trong thân máy cùng đèn LED xanh dương mang lại cảm giác đây chính là thiết kế của tương lai.');

-- image
INSERT INTO `techshopdb`.`image`
(`id`,`product_id`,`url`)
VALUES
    (10011,1001,'https://i.ibb.co/rZ8ysQq/product1001-1.jpg'),
    (10012,1001,'https://i.ibb.co/4pDWYNR/product1001-2.jpg'),
    (10013,1001,'https://i.ibb.co/cxWxcRX/product1001-3.jpg'),
    (10014,1001,'https://i.ibb.co/w4kL3n5/product1001-4.jpg'),
    (10021,1002,'https://i.ibb.co/9VXrByb/product1002-1.jpg'),
    (10022,1002,'https://i.ibb.co/PFtcjT8/product1002-2.jpg'),
    (10023,1002,'https://i.ibb.co/Jtpt1yp/product1002-3.jpg'),
    (10024,1002,'https://i.ibb.co/n3x2N5Q/product1002-4.jpg'),
    (10031,1003,'https://i.ibb.co/x713bhs/product1003-1.jpg'),
    (10032,1003,'https://i.ibb.co/Y36g0Sz/product1003-2.jpg'),
    (10033,1003,'https://i.ibb.co/SJbVyhP/product1003-3.jpg'),
    (10034,1003,'https://i.ibb.co/Hrj77jJ/product1003-4.jpg'),
    (10041,1004,'https://i.ibb.co/YNg1gQh/product1004-1.jpg'),
    (10042,1004,'https://i.ibb.co/Cs1sbgk/product1004-2.jpg'),
    (10043,1004,'https://i.ibb.co/sFmdZ7D/product1004-3.jpg'),
    (10051,1005,'https://i.ibb.co/YcC8hJW/product1005-1.jpg'),
    (10052,1005,'https://i.ibb.co/1Z1RN8D/product1005-2.jpg'),
    (10053,1005,'https://i.ibb.co/sbzvFJX/product1005-3.jpg'),
    (10054,1005,'https://i.ibb.co/j66p1Zh/product1005-4.jpg');

-- cartproduct
INSERT INTO `techshopdb`.`cartproduct`
(`id`,`account_id`,`product_id`,`quantity`) VALUES
                                                (1001,12,1002,1),
                                                (1002,12,1004,2);

-- feedback
INSERT INTO `techshopdb`.`feedback`
(`id`,`account_id`,`product_id`,`comment`,`star`)VALUES
    (1001,12,1005,'máy chơi game xịn',5);

-- bill
INSERT INTO `techshopdb`.`bill`
(`id`,`account_id`,`datecreate`,`price`,`shipprice`,`address`,`phone`,`paid`,`status`,`note`) VALUES
                                                                                                  (1001,12,'2022-12-15',14200000,50000,'Phường linh đông,thủ đức,TP HCM','0123458468',1,'đang đóng gói',
                                                                                                   ''),
                                                                                                  (1002,15,'2022-12-18',79200000,70000,'Phường linh trung,thủ đức,TP HCM','484315184',0,'đã nhận',
                                                                                                   'gửi vào buổi trưa');

-- bill_detail
INSERT INTO `techshopdb`.`bill_detail`
(`id`,`bill_id`,`product_id`,`quantity`,`unit_price`)VALUES
                                                         (1001,1001,1005,1,14200000),
                                                         (1002,1002,1005,1,14200000),
                                                         (1003,1002,1002,1,77900000);
