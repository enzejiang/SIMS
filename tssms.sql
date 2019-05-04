/*
Navicat MySQL Data Transfer

Source Server         : 192.168.123.189
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : tssms

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-05-02 01:11:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `clazz`
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `gradeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('1', '1班', '1');
INSERT INTO `clazz` VALUES ('2', '2班', '1');
INSERT INTO `clazz` VALUES ('3', '3班', '1');
INSERT INTO `clazz` VALUES ('4', '4班', '1');
INSERT INTO `clazz` VALUES ('5', '1班', '2');
INSERT INTO `clazz` VALUES ('6', '2班', '2');
INSERT INTO `clazz` VALUES ('7', '3班', '2');
INSERT INTO `clazz` VALUES ('8', '4班', '2');
INSERT INTO `clazz` VALUES ('9', '1班', '3');
INSERT INTO `clazz` VALUES ('10', '2班', '3');
INSERT INTO `clazz` VALUES ('11', '3班', '3');
INSERT INTO `clazz` VALUES ('12', '4班', '3');
INSERT INTO `clazz` VALUES ('13', '5班', '3');

-- ----------------------------
-- Table structure for `clazz_course_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `clazz_course_teacher`;
CREATE TABLE `clazz_course_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clazzid` int(11) DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  `gradeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `clazzid_cct_FK` (`clazzid`),
  KEY `tid_cct_FK` (`teacherid`),
  KEY `courseid_cct_FK` (`courseid`),
  CONSTRAINT `clazzid_cct_FK` FOREIGN KEY (`clazzid`) REFERENCES `clazz` (`id`),
  CONSTRAINT `teacherid_cct_FK` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of clazz_course_teacher
-- ----------------------------
INSERT INTO `clazz_course_teacher` VALUES ('14', '1', '1', '15', null);
INSERT INTO `clazz_course_teacher` VALUES ('15', '2', '1', '15', null);
INSERT INTO `clazz_course_teacher` VALUES ('16', '3', '2', '14', null);
INSERT INTO `clazz_course_teacher` VALUES ('17', '1', '2', '14', null);
INSERT INTO `clazz_course_teacher` VALUES ('18', '1', '3', '10', null);
INSERT INTO `clazz_course_teacher` VALUES ('19', '1', '4', '9', null);
INSERT INTO `clazz_course_teacher` VALUES ('20', '1', '5', '8', null);
INSERT INTO `clazz_course_teacher` VALUES ('21', '2', '5', '8', null);
INSERT INTO `clazz_course_teacher` VALUES ('22', '9', '5', '17', '3');
INSERT INTO `clazz_course_teacher` VALUES ('23', '10', '2', '17', '3');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '芭蕾舞');
INSERT INTO `course` VALUES ('2', '民族舞');
INSERT INTO `course` VALUES ('3', '爵士舞');
INSERT INTO `course` VALUES ('4', '恰恰');
INSERT INTO `course` VALUES ('5', '斗牛');
INSERT INTO `course` VALUES ('6', '华尔兹');
INSERT INTO `course` VALUES ('7', '探戈');
INSERT INTO `course` VALUES ('8', '踢踏舞');
INSERT INTO `course` VALUES ('9', '古典舞');
INSERT INTO `course` VALUES ('10', '现代舞');


-- ----------------------------
-- Table structure for `c_course`
-- ----------------------------
DROP TABLE IF EXISTS `c_course`;
CREATE TABLE `c_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_course
-- ----------------------------
INSERT INTO `c_course` VALUES ('1', '现代舞');
INSERT INTO `c_course` VALUES ('2', '古典舞');
INSERT INTO `c_course` VALUES ('3', '踢踏舞');
INSERT INTO `c_course` VALUES ('4', '探戈');
INSERT INTO `c_course` VALUES ('5', '华尔兹');
INSERT INTO `c_course` VALUES ('6', '恰恰舞');
INSERT INTO `c_course` VALUES ('7', '爵士舞');
INSERT INTO `c_course` VALUES ('8', '民族舞');
INSERT INTO `c_course` VALUES ('9', '芭蕾舞');
INSERT INTO `c_course` VALUES ('11', '斗牛');
INSERT INTO `c_course` VALUES ('12', '街舞');




-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1', '2013级');
INSERT INTO `grade` VALUES ('2', '2014级');
INSERT INTO `grade` VALUES ('3', '2015级');

-- ----------------------------
-- Table structure for `grade_course`
-- ----------------------------
DROP TABLE IF EXISTS `grade_course`;
CREATE TABLE `grade_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gradeid` int(11) DEFAULT NULL,
  `courseid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gid_gc_FK` (`gradeid`),
  KEY `cid_gc_FK` (`courseid`),
  CONSTRAINT `courseid_gc_FK` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`),
  CONSTRAINT `gradeid_gc_FK` FOREIGN KEY (`gradeid`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of grade_course
-- ----------------------------
INSERT INTO `grade_course` VALUES ('1', '1', '1');
INSERT INTO `grade_course` VALUES ('2', '1', '2');
INSERT INTO `grade_course` VALUES ('3', '1', '3');
INSERT INTO `grade_course` VALUES ('4', '1', '4');
INSERT INTO `grade_course` VALUES ('5', '1', '5');
INSERT INTO `grade_course` VALUES ('6', '2', '10');
INSERT INTO `grade_course` VALUES ('7', '2', '9');
INSERT INTO `grade_course` VALUES ('8', '2', '8');
INSERT INTO `grade_course` VALUES ('9', '2', '1');
INSERT INTO `grade_course` VALUES ('10', '3', '2');
INSERT INTO `grade_course` VALUES ('11', '3', '5');
INSERT INTO `grade_course` VALUES ('12', '3', '7');
INSERT INTO `grade_course` VALUES ('13', '3', '8');

-- ----------------------------
-- Table structure for `g_grade`
-- ----------------------------
DROP TABLE IF EXISTS `g_grade`;
CREATE TABLE `g_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `createTime` varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '闁圭鍋撻悘鐐靛仜閸曢箖寮?',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of g_grade
-- ----------------------------
INSERT INTO `g_grade` VALUES ('4', '2018', '2018-04');
INSERT INTO `g_grade` VALUES ('5', '2018', '2018-09');
INSERT INTO `g_grade` VALUES ('6', '2019', '2019-04');

-- ----------------------------
-- Table structure for `myschool`
-- ----------------------------
DROP TABLE IF EXISTS `myschool`;
CREATE TABLE `myschool` (
  `id` int(11) NOT NULL,
  `schoolName` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `forbidTeacher` tinyint(2) DEFAULT NULL,
  `forbidStudent` tinyint(2) DEFAULT NULL,
  `noticeTeacher` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `noticeStudent` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of myschool
-- ----------------------------
INSERT INTO `myschool` VALUES ('1', '飞扬舞蹈培训学校', '0', '0', '请各科任教师尽快登记此次期末考试成绩！！xx', '寒假于1月18日开始放假，2月20日开学，2月21日正式行课！！！同学们寒假快乐，新年快乐！！！');

-- ----------------------------
-- Table structure for `s_student`
-- ----------------------------
DROP TABLE IF EXISTS `s_student`;
CREATE TABLE `s_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `parentPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `parentWechat` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `birthday` varchar(40) DEFAULT NULL,
  `rigstTime` varchar(40) DEFAULT NULL,
  `paymentday` varchar(40) DEFAULT NULL,
  `graduationTime` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_student
-- ----------------------------
INSERT INTO `s_student` VALUES ('3', 'X2019050001', '王雨', '女', '15266666666', 'FMWX0001', null, '05/01/2012', '09/03/2018','05/02/2019', '11/30/2019');
INSERT INTO `s_student` VALUES ('4', 'X2019050002', '秦武', '男', '15266666667', 'FMWX0002', null, '06/01/2011', '09/03/2018','05/02/2019', '11/30/2019');
-- ----------------------------
-- Records of student
-- ----------------------------


-- ----------------------------
-- Table structure for `s_school_timetable`
-- ----------------------------
DROP TABLE IF EXISTS `s_school_timetable`;
CREATE TABLE `s_school_timetable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classesId` int(11) DEFAULT NULL,
  `classesName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `courseId` int(11) DEFAULT NULL,
  `courseName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `studentId` int(11) DEFAULT NULL,
  `studentCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `studentName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `studentGender` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `startDate` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `endDate` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_school_timetable
-- ----------------------------

-- ----------------------------
-- Table structure for `t_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `wechat` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo` mediumblob,
  `birthday` varchar(40) DEFAULT NULL,
  `entryday` varchar(40) DEFAULT NULL,
  `basicSalary` float DEFAULT NULL,
  `classfees` float DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `majorcourseid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES ('2', '2019001', '秦彤', '女', '15266666666', 'wxqintong', null, '04/16/2019', '04/01/2019', '9000', '300', '222333', '9');
INSERT INTO `t_teacher` VALUES ('3', '2019002', '欧洋', '男', '18244442222', 'wxouyang', null, '04/17/2019', '04/01/2019', '7000', '250', '111333', '6');



-- ----------------------------
-- Table structure for `c_classes`
-- ----------------------------
DROP TABLE IF EXISTS `c_classes`;
CREATE TABLE `c_classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级主键，班级ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '班级名称',
  `gradeid` int(11) DEFAULT NULL COMMENT '年级ID',
  `teacherid` int(11) DEFAULT NULL COMMENT '教师ID',
  `courseid` int(11) DEFAULT NULL COMMENT '课程ID',
  `starttime` varchar(50) DEFAULT NULL COMMENT '开课时间',
  `endtime` varchar(50) DEFAULT NULL COMMENT '结课时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_classes
-- ----------------------------
INSERT INTO `c_classes` VALUES ('6', '2019恰恰舞01班', '6', '3', '6', '01/01/2019', '09/01/2019');
INSERT INTO `c_classes` VALUES ('7', '2019芭蕾舞01班', '6', '2', '9', '01/02/2019', '09/01/2019');


DROP TABLE IF EXISTS `clazz_student`;
CREATE TABLE `clazz_student` (
 `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '选课 ID',
  `clazzid` int(11)  NOT NULL COMMENT '班级号',
  `studentid` int(11)  NOT NULL COMMENT '学生号',
  `paymentday` varchar(50) DEFAULT NULL COMMENT '下次缴费时间',
  `score` int(11) DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `clazz_student` VALUES ('1','6', '3', '09/01/2019', null);
INSERT INTO `clazz_student` VALUES ('2','7', '3', '09/01/2019', null);

INSERT INTO `clazz_student` VALUES ('3','6', '4', '09/01/2019', null);
INSERT INTO `clazz_student` VALUES ('4','7', '4', '09/01/2019', null);




-- ----------------------------
-- Table structure for `game` 比赛信息表
-- ----------------------------
DROP TABLE IF EXISTS `g_game`;
CREATE TABLE `g_game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gamename` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '赛会名称',
  `gameplace` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '比赛地点',
  `gamedate` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '比赛时间',
  `gamecontent` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '参赛内容',
  `gameaddr` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '比赛详细地址',
  `gameresults` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '比赛成果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `g_game` VALUES ('1', '永州民族舞邀请赛', '湖南省_永州市_东安县','2019-01-15', '芭蕾舞', '建设大道888号8楼炫舞飞扬大礼堂', null);
INSERT INTO `g_game` VALUES ('2', '中港澳国际舞表演赛', '湖南省_长沙市_长沙县','2019-05-04', '爵士舞', '星沙湖南大众传媒学院大礼堂', null);


-- ----------------------------
-- Table structure for `exam`
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` date DEFAULT NULL,
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('1', '芭蕾舞等级考试', '2019-01-15', '芭蕾舞十级水平考试');
INSERT INTO `exam` VALUES ('2', '爵士舞等级考试', '2019-05-15', '爵士舞九级考试');



-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '111111',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` tinyint(1) DEFAULT '2',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_user_FK` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '111111', '管理员', '1');
INSERT INTO `user` VALUES ('2', '2001', '111111', '卡卡西', '3');
INSERT INTO `user` VALUES ('3', '2002', '111111', '卡普', '3');
INSERT INTO `user` VALUES ('4', '2003', '111111', '战国', '3');
INSERT INTO `user` VALUES ('5', '2004', '111111', '青雉', '3');
INSERT INTO `user` VALUES ('6', '2005', '111111', '爱德华纽盖特', '3');
INSERT INTO `user` VALUES ('7', '2006', '111111', '香克斯', '3');
INSERT INTO `user` VALUES ('8', '2007', '111111', '波风水门', '3');
INSERT INTO `user` VALUES ('9', '2008', '111111', '纲手', '3');
INSERT INTO `user` VALUES ('10', '2009', '111111', '大筒木辉夜', '3');
INSERT INTO `user` VALUES ('11', '2010', '111111', '漩涡玖辛奈', '3');
INSERT INTO `user` VALUES ('15', '2011', '111111', '夕日红', '3');
INSERT INTO `user` VALUES ('16', '2012', '111111', '鹰眼米霍克', '3');
INSERT INTO `user` VALUES ('17', '201301001', '111111', '蒙奇D路飞', '2');
INSERT INTO `user` VALUES ('18', '201301002', '111111', '妮可罗宾', '2');
INSERT INTO `user` VALUES ('19', '201301003', '111111', '罗罗诺亚卓洛', '2');
INSERT INTO `user` VALUES ('20', '201301004', '111111', '托尼托尼乔巴', '2');
INSERT INTO `user` VALUES ('21', '201301005', '111111', '娜美', '2');
INSERT INTO `user` VALUES ('22', '201301006', '111111', '山治', '2');
INSERT INTO `user` VALUES ('23', '201301007', '111111', '布鲁克', '2');
INSERT INTO `user` VALUES ('24', '201301008', '111111', '乌索普', '2');
INSERT INTO `user` VALUES ('25', '201301009', '111111', '弗兰奇', '2');
INSERT INTO `user` VALUES ('26', '201301010', '111111', '娜菲鲁塔利薇薇', '2');
INSERT INTO `user` VALUES ('27', '201301011', '111111', '小鱿', '2');
INSERT INTO `user` VALUES ('28', '201301012', '111111', '梅里号', '2');
INSERT INTO `user` VALUES ('29', '201301013', '111111', '阳光号', '2');
INSERT INTO `user` VALUES ('30', '201302001', '111111', '马歇尔蒂奇', '2');
INSERT INTO `user` VALUES ('31', '201302002', '111111', '范奥卡', '2');
INSERT INTO `user` VALUES ('32', '201302003', '111111', '基萨斯巴加斯', '2');
INSERT INTO `user` VALUES ('33', '201302004', '111111', '毒Q', '2');
INSERT INTO `user` VALUES ('34', '201302005', '111111', '雨之希留', '2');
INSERT INTO `user` VALUES ('35', '201302006', '111111', '卡特琳娜', '2');
INSERT INTO `user` VALUES ('36', '201302007', '111111', '圣胡安恶狼', '2');
INSERT INTO `user` VALUES ('37', '201302008', '111111', '巴克斯乔特', '2');
INSERT INTO `user` VALUES ('38', '201302009', '111111', '阿巴罗', '2');
INSERT INTO `user` VALUES ('39', '201303001', '111111', '汉库克', '2');
INSERT INTO `user` VALUES ('40', '201303002', '111111', '桑达索尼娅', '2');
INSERT INTO `user` VALUES ('41', '201303003', '111111', '玛丽哥鲁德', '2');
INSERT INTO `user` VALUES ('42', '201303004', '111111', '玛格丽特', '2');
INSERT INTO `user` VALUES ('43', '201303005', '111111', '艾弗兰德拉', '2');
INSERT INTO `user` VALUES ('44', '201303006', '111111', '贝拉董娜', '2');
INSERT INTO `user` VALUES ('45', '201304001', '111111', '白胡子', '2');
INSERT INTO `user` VALUES ('46', '201304002', '111111', '马尔高', '2');
INSERT INTO `user` VALUES ('47', '201304003', '111111', '艾斯', '2');
INSERT INTO `user` VALUES ('48', '201304004', '111111', '乔兹', '2');
INSERT INTO `user` VALUES ('49', '201304005', '111111', '萨奇', '2');
INSERT INTO `user` VALUES ('50', '201304006', '111111', '比斯塔', '2');
INSERT INTO `user` VALUES ('51', '201304007', '111111', '布拉曼克', '2');
INSERT INTO `user` VALUES ('52', '201304008', '111111', '拉克约', '2');
INSERT INTO `user` VALUES ('53', '201304009', '111111', '那谬尔', '2');
INSERT INTO `user` VALUES ('54', '201304010', '111111', '布伦海姆', '2');
INSERT INTO `user` VALUES ('55', '201304011', '111111', '库利艾尔', '2');
INSERT INTO `user` VALUES ('56', '201304012', '111111', '金古多', '2');
INSERT INTO `user` VALUES ('57', '201304013', '111111', '佛萨', '2');
INSERT INTO `user` VALUES ('58', '201304014', '111111', '斯比多基尔', '2');
INSERT INTO `user` VALUES ('59', '201401001', '111111', '日向雏田', '2');
INSERT INTO `user` VALUES ('60', '201401002', '111111', '李洛克', '2');
INSERT INTO `user` VALUES ('61', '201401003', '111111', '日向花火', '2');
INSERT INTO `user` VALUES ('62', '201401004', '111111', '奈良鹿丸', '2');
INSERT INTO `user` VALUES ('63', '201401005', '111111', '日向宁次', '2');
INSERT INTO `user` VALUES ('64', '201401006', '111111', '佐井', '2');
INSERT INTO `user` VALUES ('65', '201401007', '111111', '山中井野', '2');
INSERT INTO `user` VALUES ('66', '201401008', '111111', '秋道丁次', '2');
INSERT INTO `user` VALUES ('67', '201401009', '111111', '犬冢牙', '2');
INSERT INTO `user` VALUES ('68', '201401010', '111111', '野原琳', '2');
INSERT INTO `user` VALUES ('69', '201401011', '111111', '天天', '2');
INSERT INTO `user` VALUES ('70', '201401012', '111111', '木叶丸', '2');
INSERT INTO `user` VALUES ('71', '201401013', '111111', '赤丸', '2');
INSERT INTO `user` VALUES ('72', '201401014', '111111', '漩涡鸣人', '2');
INSERT INTO `user` VALUES ('73', '201401015', '111111', '佐助', '2');
INSERT INTO `user` VALUES ('74', '201401016', '111111', '春野樱', '2');
INSERT INTO `user` VALUES ('75', '201401017', '111111', '油女志乃', '2');
INSERT INTO `user` VALUES ('76', '201402001', '111111', '宇智波带土', '2');
INSERT INTO `user` VALUES ('77', '201402002', '111111', '长门', '2');
INSERT INTO `user` VALUES ('78', '201402003', '111111', '绝', '2');
INSERT INTO `user` VALUES ('79', '201402004', '111111', '角都', '2');
INSERT INTO `user` VALUES ('80', '201402005', '111111', '迪达拉', '2');
INSERT INTO `user` VALUES ('81', '201402006', '111111', '小南', '2');
INSERT INTO `user` VALUES ('82', '201402007', '111111', '大蛇丸', '2');
INSERT INTO `user` VALUES ('83', '201402008', '111111', '飞段', '2');
INSERT INTO `user` VALUES ('84', '201402009', '111111', '蝎', '2');
INSERT INTO `user` VALUES ('85', '201402010', '111111', '弥彦', '2');
INSERT INTO `user` VALUES ('86', '201402011', '111111', '千柿鬼鲛', '2');
INSERT INTO `user` VALUES ('87', 'cs001', '111111', '测试老师001', '3');
INSERT INTO `user` VALUES ('88', 'test', '111111', '开发员', '4');
