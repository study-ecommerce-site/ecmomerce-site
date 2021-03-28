-- online.answer_list definition

CREATE TABLE `answer_list` (
  `board_sn` int(10) DEFAULT NULL,
  `content` text,
  `content_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- online.board definition

CREATE TABLE `board` (
  `board_sn` int(10) NOT NULL AUTO_INCREMENT,
  `category` int(10) NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `subject` varchar(40) COLLATE utf8_bin NOT NULL,
  `content` text COLLATE utf8_bin NOT NULL,
  `reg_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `top_show_yn` char(1) COLLATE utf8_bin DEFAULT NULL,
  `file_path` varchar(2083) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`board_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- online.book_order definition

CREATE TABLE `book_order` (
  `product_sn` int(10) NOT NULL,
  `order_product_sn` int(10) NOT NULL,
  `order_sn` int(10) NOT NULL,
  `product_cnt` int(10) NOT NULL,
  `product_option_sn` int(10) NOT NULL,
  PRIMARY KEY (`product_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='주문 상품 테이블';

-- online.comm_code definition

CREATE TABLE `comm_code` (
  `code` int(10) NOT NULL,
  `description` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- online.`member` definition

CREATE TABLE `member` (
  `member_sn` int(10) NOT NULL AUTO_INCREMENT,
  `member_id` varchar(30) DEFAULT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` char(1) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `sns_yn` char(1) DEFAULT NULL,
  `order_sn` int(10) DEFAULT NULL,
  `member_tatus` char(10) DEFAULT NULL,
  PRIMARY KEY (`member_sn`),
  UNIQUE KEY `member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- online.`order` definition

CREATE TABLE `order` (
  `order_sn` int(10) NOT NULL AUTO_INCREMENT,
  `member_sn` int(10) NOT NULL,
  `order_yn` char(1) COLLATE utf8_bin DEFAULT NULL,
  `order_price` int(11) DEFAULT NULL,
  `order_address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `order_status` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`order_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='주문 테이블';

-- online.pay_info definition

CREATE TABLE `pay_info` (
  `pay_sn` int(10) NOT NULL,
  PRIMARY KEY (`pay_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- online.product definition

CREATE TABLE `product` (
  `product_sn` int(10) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(10) NOT NULL,
  `product_option` char(1) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `stock_cnt` int(10) DEFAULT NULL,
  PRIMARY KEY (`product_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- online.product_option definition

CREATE TABLE `product_option` (
  `product_option_sn` int(10) NOT NULL AUTO_INCREMENT,
  `product_sn` int(10) DEFAULT NULL,
  `product_option_name` varchar(100) DEFAULT NULL,
  `plus_price` int(11) unsigned NOT NULL,
  `stock_cnt` int(10) DEFAULT NULL,
  PRIMARY KEY (`product_option_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- online.refund definition

CREATE TABLE `refund` (
  `refund_sn` int(10) NOT NULL AUTO_INCREMENT,
  `order_seq` int(10) DEFAULT NULL,
  `refundcol` varchar(20) COLLATE utf8_bin NOT NULL,
  `acc_number` char(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`refund_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- online.review definition

CREATE TABLE `review` (
  `review_sn` int(10) NOT NULL AUTO_INCREMENT,
  `member_sn` int(10) NOT NULL,
  `product_sn` int(10) NOT NULL,
  `review_sub` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `review_content` text COLLATE utf8_bin,
  `gpa` int(1) DEFAULT NULL,
  `reg_dtae` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`review_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- online.sns_info definition

CREATE TABLE `sns_info` (
  `member_sn` int(10) DEFAULT NULL,
  `sns_code` varchar(100) NOT NULL,
  `sns_type` char(1) DEFAULT NULL,
  `reg_date` datetime DEFAULT NULL,
  PRIMARY KEY (`sns_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- online.verify definition

CREATE TABLE `verify` (
  `member_sn` int(10) DEFAULT NULL,
  `auth_serial_sn` varchar(100) NOT NULL,
  `auth_code` varchar(100) DEFAULT NULL,
  `auth_date` time DEFAULT NULL,
  `auth_yn` char(1) DEFAULT NULL,
  `member_auth_type` char(1) DEFAULT NULL,
  PRIMARY KEY (`auth_serial_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;