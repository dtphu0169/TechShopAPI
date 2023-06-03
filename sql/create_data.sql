-- account
INSERT INTO Account
VALUES(1,'admin','0000000000','admin@admin.com',"ROLE_ADMIN",1,'$2a$12$H3v6EAQJEQsjBQr9zKbtee/bcq4xohDhBIpx4et1dp.lFerwmH3x2','2022-12-05 14:35:05',null);
INSERT INTO Account
VALUES(12,'nam','0123458468','nam@gmail.com',"ROLE_USER",1,'$2a$12$3HCqxqyI2luk1T1IXJFK6O0mBcpGRCBwmB7dRvgnAkt.94eC.KQQy','2023-01-05',null);
INSERT INTO Account
VALUES(15,'binh',null,'binh123@gmail.com',"ROLE_USER",1,'$2a$12$oh7xsOqW0HI8KMAlDq7Xb.mVW8q4qGbc1ugGswzBzgWaBdna.D1WC','2022-12-05 07:47:15',null);

-- address
INSERT INTO `address` (`id`,`account_id`, `provine_city`, `dictrict`, `ward`, `detail`) VALUES
                                                                                            (1001,1, 'TP.HCM', 'Quận 1', 'Phường Cầu Ông Lãnh', '32 Phan Văn Trường'),
                                                                                            (1002,12, 'Hà Nội', 'Quận Đống Đa', 'Phường Thô Quan', '62 Nguyễn Văn Chương'),
                                                                                            (1004,15, 'TP.HCM', 'TP Thủ Đức', 'Phường Linh Trung', '208 Hoàng Diệu 2'),
                                                                                            (1005,15, 'TP.HCM', 'Quận 12', 'Phường Tân Thới Nhất', '246 Phan Văn Hới');

-- category
INSERT INTO `category`(`id`,`name`,`image_path`) VALUES
                                                     (1001,'flycam', 'https://i.ibb.co/LDwhn8F/product1007-1.jpg'),
                                                     (1002,'robot', 'https://i.ibb.co/VNsQGYV/product1008-1.jpg'),
                                                     (1003,'Máy ảnh', 'https://i.ibb.co/sFmdZ7D/product1004-3.jpg'),
                                                     (1004,'play station', 'https://i.ibb.co/1Z1RN8D/product1005-2.jpg');

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
    ,'PS5 sở hữu thiết kế rất mềm mại với những đường cong ấn tượng, không vuông vức "đậm chất Sony" như PS4, hay Sony Xperia...bắt mắt với tông màu trắng chủ đạo kết hợp màu đen bên trong thân máy cùng đèn LED xanh dương mang lại cảm giác đây chính là thiết kế của tương lai.'),
    (1006,'Flycam DJI Phantom 4 Pro V2.0',true,1001,38990000,null,10
    ,'Trở lại đầy ấn tượng và mạnh mẽ, Phantom 4 Pro Version 2.0 vẫn kế thừa tất cả các tính năng tuyệt vời từ phiên bản trước đó nhưng được cải tiến nổi bật hơn nhằm phát triển bộ điều khiển và những kết nối nâng cao.'),
   (1007,'DJI MG-1P',true,1001,23000000,null,10
       ,'DJI MG-1P với những cải tiến mới, bộ điều khiển chuyên nghiệp, camera FPV góc rộng, hệ thống ba radar có độ chính xác cao, cùng với khả năng chống nhiễu và hiệu suất bay ổn định đã giúp MG-1P trở thành thiết bị hỗ trợ tối ưu cho lĩnh vực nông nghiệp hiện đại ngày nay.'),
   (1008,'Robot giáo dục lập trình (P1030069)',true,1002,3990000,null,10
       ,'Codey Rocky English Version của thương hiệu Makeblock là một robot lập trình cho giáo dục STEAM xây dựng các giáo trình học tập và tạo ra các sản phẩm phù hợp với các bậc từ tiểu học đến trung học về lập trình Scratch, Python,..'),
   (1009,'DJI RoboMaster S1 Educational Robot',true,1002,15390000,null,10
       ,'Không chỉ là con robot điều khiển từ xa chạy dưới đất tích hợp camera bình thường, RoboMaster S1 mà DJI vừa ra mắt còn là món đồ chơi để trẻ em có thể tiếp cận tới việc lập trình, tự động hoá thông qua việc tự code quá trình hoạt động của nó.'),
   (1010,'Máy ảnh kỹ thuật số Sony ZV-E10 chính hãng',true,1003,19300000,null,10
       ,'Mặc dù trình làng đầu năm 2020, nhưng cho tới hiện tại Sony ZV-E10 vẫn là dòng máy ảnh được cộng đồng nhiếp ảnh và streamer săn đón.'),
   (1011,'Máy ảnh Canon EOS 2000D Kit EF-S18-55mm F3.5-5.6 III',true,1003,9900000,null,10
       ,'Máy ảnh Canon 2000D là một chiếc máy ảnh DSLR có khả năng chụp ảnh linh hoạt và một bộ tính năng hữu ích. Nó có cảm biến APS-C 24,1 MP mới, độ nhạy sáng đạt ISO 100-6400. Ngoài ra, bạn có thể tạo ra những thước phim Full HD sống động với thiết bị này.'),
   (1012,'Máy game PS4 Pro 1TB ',true,1004,7900000,null,10
       ,'Máy game ps4 pro 1 Tb máy 98-99%, hack, bảo hành 3 tháng, có sẵn 15 thông dụng, khách có thể liên hệ để chọn game cài khi mua');

-- image
INSERT INTO `image`
(`id`,`product_id`,`url`)
VALUES
    (10011,1001,'https://i.ibb.co/JRnqXw7/product1001-1.jpg'),
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
    (10054,1005,'https://i.ibb.co/j66p1Zh/product1005-4.jpg'),
    (10061,1006,'https://i.ibb.co/0jcTy84/product1006-1.jpg'),
    (10062,1006,'https://i.ibb.co/pKbZYqV/product1006-2.jpg'),
    (10063,1006,'https://i.ibb.co/YPcKHtN/product1006-3.jpg'),
    (10064,1006,'https://i.ibb.co/zsJBWKF/product1006-4.jpg'),
    (10065,1006,'https://i.ibb.co/YpMShqH/product1006-5.jpg'),
    (10071,1007,'https://i.ibb.co/LDwhn8F/product1007-1.jpg'),
    (10072,1007,'https://i.ibb.co/z7nZ8q9/product1007-2.jpg'),
    (10073,1007,'https://i.ibb.co/5n7H3LR/product1007-3.jpg'),
    (10074,1007,'https://i.ibb.co/QDQC67v/product1007-4.jpg'),
    (10081,1008,'https://i.ibb.co/VNsQGYV/product1008-1.jpg'),
    (10082,1008,'https://i.ibb.co/8sY4XNj/product1008-2.jpg'),
    (10083,1008,'https://i.ibb.co/5TdNCQF/product1008-3.jpg'),
    (10084,1008,'https://i.ibb.co/pJvwXVp/product1008-4.jpg'),
    (10091,1009,'https://i.ibb.co/nMSB4dg/product1009-1.webp'),
    (10092,1009,'https://i.ibb.co/Xkdd2ny/product1009-2.webp'),
    (10093,1009,'https://i.ibb.co/hKNmrGM/product1009-3.webp'),
    (10094,1009,'https://i.ibb.co/HHTt41y/product1009-4.webp'),
    (10101,1010,'https://i.ibb.co/MsDR5zJ/product1010-1.webp'),
    (10102,1010,'https://i.ibb.co/vJg6XGH/product1010-2.webp'),
    (10103,1010,'https://i.ibb.co/4PW4VwD/product1010-3.webp'),
    (10111,1011,'https://i.ibb.co/cDDWfGK/product1011-1.webp'),
    (10112,1011,'https://i.ibb.co/sPVh0bK/product1011-2.webp'),
    (10113,1011,'https://i.ibb.co/TR3dDBd/product1011-3.webp'),
    (10114,1011,'https://i.ibb.co/K9LK6Vc/product1011-4.webp'),
    (10121,1012,'https://i.ibb.co/8gNR0Z9/product1012-1.webp'),
    (10122,1012,'https://i.ibb.co/B6nLvhZ/product1012-2.webp'),
    (10123,1012,'https://i.ibb.co/xsgY8nP/product1012-3.webp');


-- cartproduct
INSERT INTO `cartproduct`
(`id`,`account_id`,`product_id`,`quantity`) VALUES
                                                (1001,12,1002,1),
                                                (1002,12,1004,2);

-- feedback
INSERT INTO `feedback`
(`id`,`account_id`,`product_id`,`comment`,`star`)VALUES
    (1001,12,1005,'máy chơi game xịn',5);

-- bill
INSERT INTO `bill`
(`id`,`account_id`,`datecreate`,`price`,`shipprice`,`address`,`phone`,`paid`,`status`,`transaction_no`,`note`) VALUES
                                                                                                                   (1001,12,'2022-12-15',14200000,50000,'Phường linh đông,thủ đức,TP HCM','0123458468',1,'đang đóng gói',null,
                                                                                                                    ''),
                                                                                                                   (1002,15,'2022-12-18',79200000,70000,'Phường linh trung,thủ đức,TP HCM','484315184',0,'đã nhận',null,
                                                                                                                    'gửi vào buổi trưa');

-- bill_detail
INSERT INTO `bill_detail`
(`id`,`bill_id`,`product_id`,`quantity`,`unit_price`)VALUES
                                                         (1001,1001,1005,1,14200000),
                                                         (1002,1002,1005,1,14200000),
                                                         (1003,1002,1002,1,77900000);
