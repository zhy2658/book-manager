/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50736 (5.7.36)
 Source Host           : localhost:3306
 Source Schema         : book_manager

 Target Server Type    : MySQL
 Target Server Version : 50736 (5.7.36)
 File Encoding         : 65001

 Date: 23/09/2022 09:49:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `author` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '未知',
  `price` float NULL DEFAULT 0,
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '安提哥努斯家族笔记', '由密修会持有，拥有安提哥努斯的力量残留。记载了愚者途径的魔药配方以及安提哥努斯家族的宝藏，但是藏有陷阱。', '安提哥努斯', 450);
INSERT INTO `book` VALUES (2, '贝克兰德的神秘事件', '近期街区发生的一些超凡事件', '佛尔思', 199.9);
INSERT INTO `book` VALUES (3, '亵渎石板', '记录着各途径完整的魔药配方', '未知', 98);
INSERT INTO `book` VALUES (4, '罗塞尔日记', '这一本罗塞尔日记是由罗塞尔女儿贝尔纳黛提供，里面内容是罗塞尔自创的文字，没有任何人能解读', '罗塞尔', 450);
INSERT INTO `book` VALUES (5, 'TEST2', 'TEST1TEST2', '未知', 90);
INSERT INTO `book` VALUES (6, '特伦索斯特黄铜书', '封印物0-02，具备活性，拥有制定秩序规则的超凡能力，必须对外公布才会生效', '未知', 999);
INSERT INTO `book` VALUES (7, '格尔曼斯帕罗和三个女海盗的故事', '格尔曼斯帕罗和三个女海盗的故事', '贝克兰德早报', 2);
INSERT INTO `book` VALUES (8, '莱曼诺的旅行笔记', '来自与旅行家的非凡特性制成，最多可以纪录20多种非凡能力', '未知', 100);
INSERT INTO `book` VALUES (9, 'TEST6', 'TEST1TEST2', '未知', 90);
INSERT INTO `book` VALUES (10, '元气满满的亚龙人女仆', '来自天际省的小黄书，据说有好几卷', '未知', 98);
INSERT INTO `book` VALUES (13, 'TEST21', 'TEST1TEST2', '未知', 90);
INSERT INTO `book` VALUES (14, 'sadasd', 'sadasda', '未知', 999);
INSERT INTO `book` VALUES (22, 'BookNam', 'DescriotionDescriotionDescriotion', 'AuthorAuthor', 22);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bid` int(11) NOT NULL DEFAULT 0,
  `uid` int(11) NOT NULL DEFAULT 0,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bid_borrow`(`bid`) USING BTREE,
  INDEX `sid_borrow`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (9, 14, 34, '2022-09-21 21:38:23');
INSERT INTO `borrow` VALUES (11, 10, 33, '2022-09-21 22:22:29');
INSERT INTO `borrow` VALUES (12, 13, 34, '2022-09-21 22:35:42');

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `series` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('yousa', 'Kus+ACz23vjgdzpBoEb73g==', 'DkJWHo9pan3pmNxOCn+QAQ==', '2022-09-22 16:47:19');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `grade` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE,
  INDEX `uid_student`(`uid`) USING BTREE,
  CONSTRAINT `uid_student` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '冷鸟', '男', '2022', NULL, 2);
INSERT INTO `student` VALUES (6, '星星先生', '男', '2022', 12, 33);
INSERT INTO `student` VALUES (7, '正义小姐', '女', '1960', 19, 34);
INSERT INTO `student` VALUES (16, '特斯里', '女', '2019', 22, 1);
INSERT INTO `student` VALUES (17, 'zhy1', '男', '2022', 12, 44);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '$2a$10$c9Q9PUEKSQOozbcivpA7SeLe7K9o92oBF.OhssC6cP05AxGZ5oxEi',
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'Alice', '$2a$10$c9Q9PUEKSQOozbcivpA7SeLe7K9o92oBF.OhssC6cP05AxGZ5oxEi', 'user');
INSERT INTO `users` VALUES (2, 'Yousa', '$2a$10$c9Q9PUEKSQOozbcivpA7SeLe7K9o92oBF.OhssC6cP05AxGZ5oxEi', 'admin');
INSERT INTO `users` VALUES (33, 'star', '$2a$10$S1IopJfmyL1e3SWLYSteuucBwQQQsd1Z38CHYUSYhsV44SIDWXWRu', 'user');
INSERT INTO `users` VALUES (34, 'Justice', '$2a$10$yRBg3LSrRO0.vIM8z5dnm.g1Zg5kM5jPCecF/ug5kqQov6oPrJe1K', 'user');
INSERT INTO `users` VALUES (44, 'zhy1', '$2a$10$V44zat9KU3kEF8MxJn1oF.9CP4T0NByzFwyCE7oJGem.RSNS7wiQ.', 'user');
INSERT INTO `users` VALUES (45, 'zhy222', '$2a$10$c9Q9PUEKSQOozbcivpA7SeLe7K9o92oBF.OhssC6cP05AxGZ5oxEi', 'user');

-- ----------------------------
-- Procedure structure for addUser
-- ----------------------------
DROP PROCEDURE IF EXISTS `addUser`;
delimiter ;;
CREATE PROCEDURE `addUser`(username VARCHAR(30),role VARCHAR(10),`name` VARCHAR(25))
BEGIN

	IF NOT EXISTS(SELECT * FROM users where username = username) THEN
		INSERT INTO users(username,role) values(username,role);
	ELSE
		DELETE FROM users WHERE username = username;
	END IF;
-- 
	
	
	
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for test
-- ----------------------------
DROP PROCEDURE IF EXISTS `test`;
delimiter ;;
CREATE PROCEDURE `test`()
BEGIN
	
	DECLARE uid INT;
	DECLARE username varchar(30);
-- 	DECLARE role VARCHAR(10);
	
	DECLARE a INT DEFAULT 0;
	
	DECLARE cur CURSOR FOR SELECT * FROM users;
	DECLARE CONTINUE HANDLER FOR 1329 SET a = 1;
	OPEN cur;
	WHILE a=0 DO
		FETCH cur INTO uid,username;
		SELECT uid,username;
	END WHILE;
	CLOSE cur;
	SELECT 1;

	
END
;;
delimiter ;

-- ----------------------------
-- Function structure for testfun
-- ----------------------------
DROP FUNCTION IF EXISTS `testfun`;
delimiter ;;
CREATE FUNCTION `testfun`(i INT)
 RETURNS int(11)
BEGIN
	#Routine body goes here...
	DECLARE a INT DEFAULT 1;
-- 	SELECT COUNT(*) INTO a FROM users;

-- 		分支
-- 	IF NOT EXISTS(SELECT * FROM users WHERE uid =100) THEN
-- 		RETURN 1;
-- 	ELSE
-- 		RETURN 0;
-- 	END IF;

-- 循环
-- 		WHILE a<3  DO
-- 			SET a=a+1;
-- 		END WHILE;

	lp1: LOOP
	SET a=a+1;

		IF a=4 THEN
			LEAVE lp1; 
		END IF; 
	END LOOP lp1;



	RETURN i*a;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table book
-- ----------------------------
DROP TRIGGER IF EXISTS `book_after_delete`;
delimiter ;;
CREATE TRIGGER `book_after_delete` AFTER DELETE ON `book` FOR EACH ROW BEGIN
		DELETE FROM borrow where bid = old.bid;		
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
