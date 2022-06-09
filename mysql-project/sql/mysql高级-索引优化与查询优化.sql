CREATE TABLE `class` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`className` VARCHAR(30) DEFAULT NULL,
`address` VARCHAR(40) DEFAULT NULL,
`monitor` INT NULL ,
PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `student` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`stuno` INT NOT NULL ,
`name` VARCHAR(20) DEFAULT NULL,
`age` INT(3) DEFAULT NULL,
`classId` INT(11) DEFAULT NULL,
PRIMARY KEY (`id`)
#CONSTRAINT `fk_class_id` FOREIGN KEY (`classId`) REFERENCES `t_class` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DESC student;
drop function rand_string;
#随机产生字符串
DELIMITER //
CREATE FUNCTION rand_string(n INT) RETURNS VARCHAR(255)
BEGIN
DECLARE chars_str VARCHAR(100) DEFAULT
'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
DECLARE return_str VARCHAR(255) DEFAULT '';
DECLARE i INT DEFAULT 0;
WHILE i < n DO
SET return_str =CONCAT(return_str,SUBSTRING(chars_str,FLOOR(1+RAND()*52),1));
SET i = i + 1;
END WHILE;
RETURN return_str;
END //
DELIMITER ;

#用于随机产生多少到多少的编号
DELIMITER //
CREATE FUNCTION rand_num (from_num INT ,to_num INT) RETURNS INT(11)
BEGIN
DECLARE i INT DEFAULT 0;
SET i = FLOOR(from_num +RAND()*(to_num - from_num+1)) ;
RETURN i;
END //
DELIMITER ;

#假如要删除
drop function rand_num;

#创建往stu表中插入数据的存储过程
DELIMITER //
CREATE PROCEDURE insert_stu( START INT , max_num INT )
BEGIN
DECLARE i INT DEFAULT 0;
SET autocommit = 0; #设置手动提交事务
REPEAT #循环
SET i = i + 1; #赋值
INSERT INTO student (stuno, name ,age ,classId ) VALUES
((START+i),rand_string(6),rand_num(1,50),rand_num(1,1000));
UNTIL i = max_num
END REPEAT;
COMMIT; #提交事务
END //
DELIMITER ;
#假如要删除
drop PROCEDURE insert_stu;

#执行存储过程，往class表添加随机数据
DELIMITER //
CREATE PROCEDURE `insert_class`( max_num INT )
BEGIN
DECLARE i INT DEFAULT 0;
SET autocommit = 0;
REPEAT
SET i = i + 1;
INSERT INTO class ( classname,address,monitor ) VALUES
(rand_string(8),rand_string(10),rand_num(1,100000));
UNTIL i = max_num
END REPEAT;
COMMIT;
END //
DELIMITER ;
#假如要删除
drop PROCEDURE insert_class;

#执行存储过程，往class表添加1万条数据
CALL insert_class(10000);

#执行存储过程，往stu表添加50万条数据
CALL insert_stu(100000,500000);
CALL insert_stu(600000,1000000);

DELIMITER //
CREATE PROCEDURE `proc_drop_index`(dbname VARCHAR(200),tablename VARCHAR(200))
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE ct INT DEFAULT 0;
    DECLARE _index VARCHAR(200) DEFAULT '';
    DECLARE _cur CURSOR FOR SELECT index_name FROM
    information_schema.STATISTICS WHERE table_schema=dbname AND table_name=tablename AND
    seq_in_index=1 AND index_name <>'PRIMARY' ;
    #每个游标必须使用不同的declare continue handler for not found set done=1来控制游标的结束
    DECLARE CONTINUE HANDLER FOR NOT FOUND set done=2 ;
    #若没有数据返回,程序继续,并将变量done设为2
    OPEN _cur;
    FETCH _cur INTO _index;
    WHILE _index<>'' DO
        SET @str = CONCAT("drop index " , _index , " on " , tablename );
        PREPARE sql_str FROM @str ;
        EXECUTE sql_str;
        DEALLOCATE PREPARE sql_str;
        SET _index='';
        FETCH _cur INTO _index;
    END WHILE;
    CLOSE _cur;
END //
DELIMITER ;

#CALL proc_drop_index("dbname","tablename");

CREATE TABLE person_info(
id INT UNSIGNED NOT NULL AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
birthday DATE NOT NULL,
phone_number CHAR(11) NOT NULL,
country varchar(100) NOT NULL,
PRIMARY KEY (id),
KEY idx_name_birthday_phone_number (name(10), birthday, phone_number)
);

SHOW INDEX FROM student;
#最佳左前缀法则
#计算、函数、类型转换（自动或手动）导致索引失效
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE student.name LIKE 'abc%';

EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE LEFT(student.name,3) = 'abc';

EXPLAIN SELECT id, stuno, name FROM student WHERE SUBSTRING(name, 1,3)='abc';

EXPLAIN SELECT id, stuno, NAME FROM student WHERE NAME LIKE 'abc%';

CREATE INDEX idx_name ON student(NAME);

CREATE INDEX idx_sno ON student(stuno);

EXPLAIN SELECT SQL_NO_CACHE id, stuno, NAME FROM student WHERE stuno+1 = 900001

EXPLAIN SELECT SQL_NO_CACHE id, stuno, NAME FROM student WHERE stuno = 900001-1;

EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE name=123;

EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE name='123';

#范围条件右边的列索引失效
CREATE INDEX idx_age_classid ON student(age,classid);

DROP INDEX idx_name ON student;

EXPLAIN SELECT SQL_NO_CACHE * FROM student
WHERE student.age=30 AND student.classId>20 AND student.name = 'abc' ;

create index idx_age_name_classid on student(age,name,classid);

#不等于(!= 或 <>)索引失效
#is null可以使用索引，is not null无法使用索引
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age IS NULL;
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age IS NOT NULL;

#like以通配符%开头索引失效
CREATE INDEX idx_name ON student(name);
EXPLAIN SELECT id, stuno, NAME FROM student WHERE NAME LIKE '%abc';
EXPLAIN SELECT id, stuno, NAME FROM student WHERE NAME LIKE 'abc%';

#or前后存在非索引的列，索引失效
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 10 OR classid = 100;
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 10 OR name = 'Abel';

# 数据库和表的字符集统一使用utf8mb4
#统一使用utf8mb4( 5.5.3版本以上支持)兼容性更好，统一字符集可以避免由于字符集转换产生的乱码。不$#
#同的 字符集 进行比较前需要进行 转换 会造成索引失效。

#----------------- 关联查询优化 ----------------#
#数据准备
#分类
CREATE TABLE IF NOT EXISTS `type`(
`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
`card` INT(10) UNSIGNED NOT NULL,
PRIMARY KEY ( `id` )
);
#图书
DROP TABLE book;
CREATE TABLE IF NOT EXISTS `book`(
	`bookid` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   `card`INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY (`bookid`)
);

#向分类表中添加20条记录
INSERT INTO type (card) VALUES (FLOOR(1 +(RAND() * 20)));
#向图书表中添加20条记录
INSERT INTO book(card) VALUES (FLOOR(1 +(RAND() * 20)) );
#采用左外链接
EXPLAIN SELECT SQL_NO_CACHE * FROM `type` LEFT JOIN book ON type.card = book.card;

# 添加索引
ALTER TABLE book ADD INDEX Y(card); #【被驱动表】，可以避免全表扫描

ALTER TABLE `type` ADD INDEX X (card); #【驱动表】，无法避免全表扫描

DROP INDEX Y ON book;
EXPLAIN SELECT SQL_NO_CACHE * FROM `type` LEFT JOIN book ON type.card = book.card;

#采用内连接
drop index X on type;
drop index Y on book;#（如果已经删除了可以不用再执行该操作）

EXPLAIN SELECT SQL_NO_CACHE * FROM type INNER JOIN book ON type.card=book.card;

SELECT count(*) FROM book;
SELECT count(*) FROM type;

ALTER TABLE book ADD INDEX Y (card);

#`内连接` 主被驱动表是由优化器决定的。优化器认为哪个成本比较小，就采用哪种作为驱动表。
#如果两张表只有一个有索引，那有索引的表作为`驱动表`
#		原因：驱动表要全查出来。有没有索引你都得全查出来
#两个索引都存在的情况下， 数据量大的 作为`被驱动表`（小表驱动大表）
# 	原因：驱动表要全部查出来，而大表可以通过索引加快查找

#------------------- 子查询优化 ---------------------

#创建班级表中班长的索引
CREATE INDEX idx_monitor ON class ( monitor ) ;
DESC student
#子查询
EXPLAIN SELECT *FROM student stu1
WHERE stu1.stuno IN(
SELECT monitor
FROM class c
WHERE monitor IS NOT NULL);

#多表查询
EXPLAIN SELECT stu1.* FROM student stu1 JOIN class c
ON stu1.stuno = c.monitor
WHERE c.monitor IS NOT NULL;

EXPLAIN SELECT SQL_NO_CACHE a.* FROM student a
WHERE a.stuno NOT IN (
SELECT monitor FROM class b WHERE monitor IS NOT NULL);

EXPLAIN SELECT SQL_NO_CACHE a.*
FROM student a LEFT OUTER JOIN class b ON a. stuno =b.monitor
WHERE b.monitor IS NULL;

#------------------- 排序优化 -------------------
call proc_drop_index( 'practice' , 'student' );
EXPLAIN SELECT SQL_NO_CACHE * FROM student ORDER BY age,classid;

EXPLAIN SELECT SQL_NO_CACHE * FROM student ORDER BY age,classid limit 10;

CREATE INDEX idx_age_classid_name ON student (age,classid, NAME);

#不限制,索引失效--优化器觉得还需要回表会费时间更大,不走索引。
EXPLAIN SELECT SQL_NO_CACHE * FROM student ORDER BY age ,classid ;

EXPLAIN SELECT SQL_NO_CACHE * FROM student ORDER BY age ,classid LIMIT 100;

#order by时顺序错误，索引失效(最左前缀匹配规则)--mysql8支持降序索性和隐藏索引
CREATE INDEX idx_age_classid_stuno ON student (age,classid,stuno) ;
# 不会走，最左前缀原则
EXPLAIN SELECT* FROM student ORDER BY classid LIMIT 10;

# 不会走，最左前缀原则
EXPLAIN SELECT* FROM student ORDER BY classid,NAME LIMIT 10;

# 走
EXPLAIN SELECT* FROM student ORDER BY age,classid, stuno LIMIT 10;
# 走
EXPLAIN SELECT *FROM student ORDER BY age,classid LIMIT 10;
# 走
EXPLAIN SELECT * FROM student ORDER BY age LIMIT 10;

#order by时规则不一致,索引失效（顺序错，不索引; 方向反，不索引)
# age desc 方向反 索引失效
EXPLAIN SELECT * FROM student ORDER BY age DESC, classid ASC LIMIT 10;

# 没有最左前缀 索引失效
EXPLAIN SELECT * FROM student ORDER BY classid DESC, NAME DESC LIMIT 10;

# age asc 没问题 classid desc 降序， 优化器认为，文件排序比较快索引失效
# 方向反了不走索引
EXPLAIN SELECT * FROM student ORDER BY age ASC, classid DESC LIMIT 10;

# Backward index scan 走索引了，，倒着走索引
EXPLAIN SELECT * FROM student ORDER BY age DESC, classid DESC LIMIT 10; 


#无过滤,不索引
EXPLAIN SELECT * FROM student WHERE age=45 ORDER BY classid;

EXPLAIN SELECT * FROM student WHERE age=45 ORDER BY classid , name;

EXPLAIN SELECT *FROM student WHERE classid=45 order by age;

SHOW INDEX FROM student;

EXPLAIN SELECT * FROM student WHERE classid=45 order by age limit 10;

DROP INDEX idx_age ON student;
DROP INDEX idx_age_classid_stuno ON student;
DROP INDEX idx_age_classid_name ON student;
DROP INDEX idx_age_name ON student;

CREATE INDEX idx_age ON student(age);
# sql优化过程
EXPLAIN SELECT SQL_NO_CACHE * FROM student WHERE age = 30 AND stuno <101000 ORDER BY NAME;

#使用上了索引，但是只用到了一部分
CREATE INDEX idx_age_name ON student(age , NAME);

#为了充分使用到索引，加一个字段进行组合索引
create index idx_age_stuno_name on student(age,stuno,name);

#------------------ 分页查询优化 ----------------

EXPLAIN SELECT * FROM student LIMIT 2088800,10;

#优化思路一：跑索引（在索引上完成排序分页操作，最后根据主键关联回原表查询所需要的其他列内容）
EXPLAIN SELECT * FROM student t,
( SELECT id FROM student ORDER BY id LIMIT 2000000,10) a WHERE t.id = a.id;

#优化思路二（几乎没法用）
EXPLAIN SELECT * FROM student WHERE id > 2080880 LIMIT 10;

#覆盖索引：一个索引包含了满足查询结果的数据就叫做覆盖索引

CREATE INDEX idx_age_name ON student (age , NAME);

EXPLAIN SELECT * FROM student WHERE age <>20;

EXPLAIN SELECT id, age , NAME FROM student WHERE age <> 28;

EXPLAIN SELECT id, age , NAME,classid FROM student WHERE age <> 28;

EXPLAIN SELECT *FROM student WHERE NAME LIKE '%abc';

EXPLAIN SELECT id, age ,NAME FROM student WHERE NAME LIKE '%abc ';

EXPLAIN SELECT id, age ,NAME,classid FROM student WHERE NAME LIKE '%abc ';


