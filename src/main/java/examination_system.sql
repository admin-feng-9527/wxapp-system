/*
Navicat MySQL Data Transfer

Source Server         : MyConnection
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : examination_system

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2019-06-06 22:41:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `collegeID` int(11) NOT NULL,
  `collegeName` varchar(200) NOT NULL COMMENT '课程名',
  PRIMARY KEY (`collegeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', 'Java培训');
INSERT INTO `college` VALUES ('2', 'Android培训');
INSERT INTO `college` VALUES ('3', 'C++培训');
INSERT INTO `college` VALUES ('4', 'Vue培训');
INSERT INTO `college` VALUES ('5', 'JavaScript培训');
INSERT INTO `college` VALUES ('6', 'Python培训');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseID` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(200) NOT NULL COMMENT '课程名称',
  `teacherID` int(11) NOT NULL,
  `courseTime` varchar(200) DEFAULT NULL COMMENT '开课时间',
  `classRoom` varchar(200) DEFAULT NULL COMMENT '开课地点',
  `courseWeek` int(200) DEFAULT NULL COMMENT '学时',
  `courseType` varchar(20) DEFAULT NULL COMMENT '课程类型',
  `collegeID` int(11) NOT NULL COMMENT '所属院系',
  `score` int(11) NOT NULL COMMENT '学分',
  PRIMARY KEY (`courseID`),
  KEY `collegeID` (`collegeID`),
  KEY `teacherID` (`teacherID`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacherID`) REFERENCES `teacher` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'Java基础', '1001', '周二', '石C5-401', '18', '全程班', '1', '2');
INSERT INTO `course` VALUES ('2', 'Android开发', '1001', '周四', '石C4-302', '18', '全程班', '1', '4');
INSERT INTO `course` VALUES ('3', 'C语言', '1001', '周四', '石C4-302', '18', '普通班', '1', '2');
INSERT INTO `course` VALUES ('4', 'Vue前端框架', '1001', '周五', '石C6-101', '18', '全程班', '1', '2');
INSERT INTO `course` VALUES ('5', 'Java实战', '1001', '周四', '石C4-410', '18', '普通班', '2', '3');
INSERT INTO `course` VALUES ('6', 'Python实战', '1001', '周二', '石C4-204', '18', '普通班', '2', '2');
INSERT INTO `course` VALUES ('7', 'JavaScript', '1001', '周四', '石C4-208', '19', '普通班', '2', '2');
INSERT INTO `course` VALUES ('9', 'PHP', '1002', '周五', 'undefined', '16', '就业班', '1', '1');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleID` int(11) NOT NULL,
  `roleName` varchar(20) NOT NULL,
  `permissions` varchar(255) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', 'admin', null);
INSERT INTO `role` VALUES ('1', 'teacher', null);
INSERT INTO `role` VALUES ('2', 'student', null);

-- ----------------------------
-- Table structure for `selectedcourse`
-- ----------------------------
DROP TABLE IF EXISTS `selectedcourse`;
CREATE TABLE `selectedcourse` (
  `courseID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `mark` int(11) DEFAULT NULL COMMENT '成绩',
  KEY `courseID` (`courseID`),
  KEY `studentID` (`studentID`),
  CONSTRAINT `selectedcourse_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`),
  CONSTRAINT `selectedcourse_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of selectedcourse
-- ----------------------------
INSERT INTO `selectedcourse` VALUES ('2', '10001', '77');
INSERT INTO `selectedcourse` VALUES ('1', '10002', '66');
INSERT INTO `selectedcourse` VALUES ('1', '10003', '78');
INSERT INTO `selectedcourse` VALUES ('2', '10003', '99');
INSERT INTO `selectedcourse` VALUES ('5', '10001', '66');
INSERT INTO `selectedcourse` VALUES ('3', '10001', '90');
INSERT INTO `selectedcourse` VALUES ('4', '10001', '82');
INSERT INTO `selectedcourse` VALUES ('7', '10001', null);
INSERT INTO `selectedcourse` VALUES ('6', '10002', null);
INSERT INTO `selectedcourse` VALUES ('6', '10001', null);
INSERT INTO `selectedcourse` VALUES ('7', '10001', '89');
INSERT INTO `selectedcourse` VALUES ('7', '10002', null);
INSERT INTO `selectedcourse` VALUES ('7', '10003', '93');
INSERT INTO `selectedcourse` VALUES ('7', '10004', null);
INSERT INTO `selectedcourse` VALUES ('7', '10005', '78');
INSERT INTO `selectedcourse` VALUES ('7', '10006', '86');
INSERT INTO `selectedcourse` VALUES ('7', '10009', null);
INSERT INTO `selectedcourse` VALUES ('1', '10001', null);

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthYear` date DEFAULT NULL COMMENT '出生日期',
  `grade` date DEFAULT NULL COMMENT '入学时间',
  `collegeID` int(11) NOT NULL COMMENT '院系id',
  PRIMARY KEY (`userID`),
  KEY `collegeID` (`collegeID`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`)
) ENGINE=InnoDB AUTO_INCREMENT=10014 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('10001', '小黄', '女', '1996-05-09', '2019-05-03', '1');
INSERT INTO `student` VALUES ('10002', '小黄', '男', '1995-05-16', '2019-02-23', '1');
INSERT INTO `student` VALUES ('10003', '小陈', '男', '1996-09-02', '2015-09-02', '4');
INSERT INTO `student` VALUES ('10004', '小华', '女', '1996-09-02', '2019-04-02', '3');
INSERT INTO `student` VALUES ('10005', '小左', '女', '1996-09-02', '2015-09-02', '6');
INSERT INTO `student` VALUES ('10006', '小拉', '男', '1996-09-02', '2015-09-02', '2');
INSERT INTO `student` VALUES ('10009', '小米', '男', '1995-09-09', '2019-03-02', '3');
INSERT INTO `student` VALUES ('10010', '小可', '女', '1996-09-02', '2015-09-02', '4');
INSERT INTO `student` VALUES ('10011', '小汤', '女', '1995-01-01', '2019-01-01', '1');
INSERT INTO `student` VALUES ('10012', '小陈', '男', '1988-07-28', '2019-04-15', '2');
INSERT INTO `student` VALUES ('10013', '小蜜', '女', '1996-05-31', '2017-05-31', '1');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birthYear` date NOT NULL,
  `degree` varchar(20) DEFAULT NULL COMMENT '学历',
  `title` varchar(255) DEFAULT NULL COMMENT '职称',
  `grade` date DEFAULT NULL COMMENT '入职时间',
  `collegeID` int(11) NOT NULL COMMENT '院系',
  PRIMARY KEY (`userID`),
  KEY `collegeID` (`collegeID`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`collegeID`) REFERENCES `college` (`collegeID`)
) ENGINE=InnoDB AUTO_INCREMENT=1035 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1001', '王老师', '男', '1992-03-02', '博士', '普通讲师', '2019-02-01', '4');
INSERT INTO `teacher` VALUES ('1002', '曹老师', '男', '1996-09-02', '本科', '高级讲师', '2015-09-02', '1');
INSERT INTO `teacher` VALUES ('1003', '汤老师', '男', '1996-09-02', '硕士', '专业讲师', '2017-07-07', '3');
INSERT INTO `teacher` VALUES ('1004', '赵老师', '女', '1990-08-09', '博士', '特级讲师', '2017-09-09', '2');
INSERT INTO `teacher` VALUES ('1005', '刘老师', '男', '1996-09-02', '硕士', '专业讲师', '2017-07-07', '6');
INSERT INTO `teacher` VALUES ('1006', '刑老师', '男', '1986-01-01', '博士', '普通讲师', '1992-01-01', '1');
INSERT INTO `teacher` VALUES ('1007', '王老师', '男', '1986-01-01', '本科', '普通讲师', '2000-01-01', '1');
INSERT INTO `teacher` VALUES ('1008', '陈老师', '女', '1982-02-04', '博士', '高级讲师', '1999-04-01', '2');
INSERT INTO `teacher` VALUES ('1009', '杨老师', '男', '1984-05-01', '本科', '高级讲师', '2000-01-01', '1');
INSERT INTO `teacher` VALUES ('1010', '姜老师', '男', '2000-01-01', '本科', '专业讲师', '1998-01-01', '1');
INSERT INTO `teacher` VALUES ('1011', '张老师', '男', '1996-09-02', '硕士', '讲师', '2015-09-02', '2');
INSERT INTO `teacher` VALUES ('1012', '李老师', '男', '1986-03-04', '硕士', '高级讲师', '2019-03-27', '2');
INSERT INTO `teacher` VALUES ('1013', '莫老师', '男', '1984-04-03', '本科', '高级讲师', '2019-03-31', '1');
INSERT INTO `teacher` VALUES ('1034', '1', '男', '2000-01-01', '本科', '1', '2000-01-01', '1');

-- ----------------------------
-- Table structure for `userlogin`
-- ----------------------------
DROP TABLE IF EXISTS `userlogin`;
CREATE TABLE `userlogin` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `role` int(11) NOT NULL DEFAULT '2' COMMENT '角色权限',
  PRIMARY KEY (`userID`),
  KEY `role` (`role`),
  CONSTRAINT `userlogin_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userlogin
-- ----------------------------
INSERT INTO `userlogin` VALUES ('1', 'admin', '123', '0');
INSERT INTO `userlogin` VALUES ('8', '10001', '123', '2');
INSERT INTO `userlogin` VALUES ('9', '10002', '123', '2');
INSERT INTO `userlogin` VALUES ('10', '10003', '123', '2');
INSERT INTO `userlogin` VALUES ('11', '10005', '123', '2');
INSERT INTO `userlogin` VALUES ('12', '10004', '123', '2');
INSERT INTO `userlogin` VALUES ('13', '10006', '123', '2');
INSERT INTO `userlogin` VALUES ('14', '1001', '123', '1');
INSERT INTO `userlogin` VALUES ('15', '1002', '123', '1');
INSERT INTO `userlogin` VALUES ('16', '1003', '123', '1');
INSERT INTO `userlogin` VALUES ('19', '11', '123', '2');
INSERT INTO `userlogin` VALUES ('25', '12', '123', '2');
INSERT INTO `userlogin` VALUES ('26', '123', '123', '2');
INSERT INTO `userlogin` VALUES ('27', '2312312', '123', '2');
INSERT INTO `userlogin` VALUES ('28', '1212', '123', '2');
INSERT INTO `userlogin` VALUES ('29', '111111', '1234', '2');
INSERT INTO `userlogin` VALUES ('33', '100010', '123', '2');
INSERT INTO `userlogin` VALUES ('34', '100011', '123', '2');
INSERT INTO `userlogin` VALUES ('35', '100012', '123', '2');
INSERT INTO `userlogin` VALUES ('41', '10009', '123', '2');
INSERT INTO `userlogin` VALUES ('42', '10009', '123', '2');
INSERT INTO `userlogin` VALUES ('43', '10009', '123', '2');
INSERT INTO `userlogin` VALUES ('59', '10010', '123', '2');
INSERT INTO `userlogin` VALUES ('60', '1', '123', '2');
INSERT INTO `userlogin` VALUES ('61', '12', '123', '2');
INSERT INTO `userlogin` VALUES ('62', '14', '123', '2');
INSERT INTO `userlogin` VALUES ('63', '133', '123', '2');
INSERT INTO `userlogin` VALUES ('64', '132', '123', '2');
INSERT INTO `userlogin` VALUES ('65', '145', '123', '2');
INSERT INTO `userlogin` VALUES ('66', '146', '123', '2');
INSERT INTO `userlogin` VALUES ('67', '1232', '123', '2');
INSERT INTO `userlogin` VALUES ('68', '2323', '123', '2');
INSERT INTO `userlogin` VALUES ('69', '10011', '123', '2');
INSERT INTO `userlogin` VALUES ('70', '1006', '123', '1');
INSERT INTO `userlogin` VALUES ('71', '1', '123', '1');
INSERT INTO `userlogin` VALUES ('72', '2', '123', '1');
INSERT INTO `userlogin` VALUES ('73', '1007', '123', '1');
INSERT INTO `userlogin` VALUES ('75', '1009', '123', '1');
INSERT INTO `userlogin` VALUES ('76', '1010', '123', '1');
INSERT INTO `userlogin` VALUES ('77', 'tjd', '123', '2');
INSERT INTO `userlogin` VALUES ('78', 'tjd', '123', '2');
INSERT INTO `userlogin` VALUES ('79', 'yang', '12', '2');
INSERT INTO `userlogin` VALUES ('82', '123qeqweqw', '123', '2');
INSERT INTO `userlogin` VALUES ('84', 'ert', '123', '2');
INSERT INTO `userlogin` VALUES ('85', 'ert', '123', '2');
INSERT INTO `userlogin` VALUES ('86', 'ewr', '123', '2');
INSERT INTO `userlogin` VALUES ('87', 'dfg', '123', '2');
INSERT INTO `userlogin` VALUES ('88', 'qetewqt', '123', '2');
INSERT INTO `userlogin` VALUES ('90', '10012', '123', '2');
INSERT INTO `userlogin` VALUES ('93', '1034', '123', '1');
INSERT INTO `userlogin` VALUES ('94', '10012', '123', '2');
INSERT INTO `userlogin` VALUES ('95', '1012', '123', '1');
INSERT INTO `userlogin` VALUES ('96', '1004', '123', '2');
INSERT INTO `userlogin` VALUES ('97', '10013', '123', '2');
INSERT INTO `userlogin` VALUES ('99', '1013', '123', '1');
