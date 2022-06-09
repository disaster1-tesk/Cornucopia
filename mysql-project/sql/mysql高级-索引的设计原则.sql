#1.创建学生表和课程表
CREATE TABLE `student_info` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`student_id` INT NOT NULL ,
`name` VARCHAR(20) DEFAULT NULL,
`course_id` INT NOT NULL ,
`class_id` INT(11) DEFAULT NULL,
`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `course` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`course_id` INT NOT NULL ,
`course_name` VARCHAR(40) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

#2.创建存储函数（略）

show variables like 'log_bin_trust_function_creators';
set global log_bin_trust_function_creators=1; # 不加global只是当前窗口有效。

#--------          那些情况适合创建索引           ---------#
#1.字段的数值有唯一性的限制
#2.频繁作为WHERE查询条件的字段
#3.经常GROUP BY 和 ORDER BY的列
#4.UPDATE、DELETE 的 WHERE 条件列
#5.DISTINCT 字段需要创建索引
#6.多表 JOIN 连接操作时，创建索引注意事项
#7.使用列的类型小的创建索引
#8.使用字符串前缀创建索引
	# 长度的选择公式：count(distinct left(列名, 索引长度))/count(*)
	# select count(distinct address) / count(*) from shop;
select count(distinct left(name,10)) / count(*) as sub10, -- 截取前10个字符的选择度count(distinct left(address,15)) / count(*) as sub11, -- 截取前15个字符的选择度
count(distinct left(name,20)) / count(*) as sub12, -- 截取前20个字符的选择度
count(distinct left(name,25)) / count(*) as sub13 -- 截取前25个字符的选择度
from user;
#获取的值越趋向于1越好

SHOW CREATE TABLE user;
#9.区分度高(散列性高)的列适合作为索引
#10.使用最频繁的列放到联合索引的左侧
#11.在多个字段都要创建索引的情况下，联合索引优于单值索引

#----------------- 那些情况不适合创建索引   --------------- #
#1.在where中使用不到的字段，不要设置索引
#2.数据量小的表最好不要使用索引
#3.有大量重复数据的列上不要建立索引
#4.避免对经常更新的表创建过多的索引
#5.不建议用无序的值作为索引
#6.删除不再使用或者很少使用的索引
#7.不要定义冗余或重复的索引
#
CREATE TABLE t_without_index(
a INT PRIMARY KEY AUTO_INCREMENT,
b INT
);










