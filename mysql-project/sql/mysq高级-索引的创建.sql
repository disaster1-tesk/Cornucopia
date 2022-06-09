# 创建普通索引
CREATE TABLE book(
book_id INT ,
book_name VARCHAR(100),
authors VARCHAR(100),
info VARCHAR(100) ,
comment VARCHAR(100),
year_publication YEAR,
INDEX(year_publication)
);


# 修改sql_mode
show VARIABLES LIKE 'sql_mode';
SET SESSION sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

# 创建唯一索引
DROP TABLE test1;
CREATE TABLE test1(
id INT NOT NULL,
name varchar(30) NOT NULL,
UNIQUE INDEX uk_idx_id(id)
);

SHOW INDEX FROM test1;

# 主键索引

CREATE TABLE student (
id INT(10) UNSIGNED AUTO_INCREMENT ,
student_no VARCHAR(200),
student_name VARCHAR(200),
PRIMARY KEY(id)
);

#删除索引 
ALTER TABLE student DROP PRIMARY KEY;

#修改主键索引：必须先删除（DROP）原索引，再新建（Add）索引


#创建单列索引
CREATE TABLE test2(
id INT NOT NULL,
name CHAR(50) NULL,
INDEX single_idx_name(name(20))
);

SHOW INDEX FROM test2\G;

#创建组合索引
CREATE TABLE test3(
id INT(11) NOT NULL,
name CHAR(30) NOT NULL,
age INT(11) NOT NULL,
info VARCHAR(255),
INDEX multi_idx(id,name,age)
);
SHOW INDEX FROM test3 \G

#创建全文索引
CREATE TABLE test4(
id INT NOT NULL,
name CHAR(30) NOT NULL,
age INT NOT NULL,
info VARCHAR(255),
FULLTEXT INDEX futxt_idx_info(info)
) ENGINE=MyISAM;

CREATE TABLE articles (
id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
title VARCHAR (200),
body TEXT,
FULLTEXT index (title, body)
) ENGINE = INNODB ;

SHOW INDEX FROM articles;
SHOW TABLE STATUS LIKE 'articles';


# 不同于like方式的查询,全文索引使用match+against方式查询
SELECT * FROM papers WHERE content LIKE ‘%查询字符串%’;
SELECT * FROM papers WHERE MATCH(title,content) AGAINST (‘查询字符串’);

#-------------上面是创建表的时候创建索引，下面为再已经存在的表上操作索引-----------#
#方式一
ALTER TABLE table_name ADD [UNIQUE | FULLTEXT | SPATIAL] [INDEX | KEY]
[index_name] (col_name[length],...) [ASC | DESC]

ALTER TABLE table_name DROP INDEX index_name;

#方式二
CREATE [UNIQUE | FULLTEXT | SPATIAL] INDEX index_name
ON table_name (col_name[length],...) [ASC | DESC]

DROP INDEX index_name ON table_name;


#---------mysql 8.0索引的新特性------------#
#支持降序排序
CREATE TABLE ts1(a int,b int,index idx_a_b(a,b desc));

DELIMITER //
CREATE PROCEDURE ts_insert()
BEGIN
DECLARE i INT DEFAULT 1;
WHILE i < 800
DO
insert into ts1 select rand()*80000,rand()*80000;
SET i = i + 1;
END WHILE;
commit;
END //
DELIMITER ;
#提示 Using filesort是MySQL中一种速度比较慢的外部排序，能避免是最好的。多数情况下，管理员
#可以通过优化索引来尽量避免出现Using filesort，从而提高数据库执行速度。
#注意 降序索引只对查询中特定的排序顺序有效，如果使用不当，反而查询效率更低。例如，上述
#查询排序条件改为order by a desc, b desc，MySQL 5.7的执行计划要明显好于MySQL 8.0。
CALL ts_insert();
EXPLAIN SELECT * FROM ts1 ORDER BY a,b DESC LIMIT 5;
ALTER TABLE ts1 ALTER INDEX idx_a_b invisible;
SHOW INDEX FROM ts1;


#支持隐藏索引
CREATE TABLE tablename(
propname1 type1[CONSTRAINT1],
propname2 type2[CONSTRAINT2],
……
propnamen typen,
INDEX [indexname](propname1 [(length)]) INVISIBLE
);

CREATE INDEX indexname
ON tablename(propname[(length)]) INVISIBLE;

ALTER TABLE tablename
ADD INDEX indexname (propname [(length)]) INVISIBLE;

ALTER TABLE tablename ALTER INDEX index_name INVISIBLE; #切换成隐藏索引
ALTER TABLE tablename ALTER INDEX index_name VISIBLE; #切换成非隐藏索引

#注意 当索引被隐藏时，它的内容仍然是和正常索引一样实时更新的。如果一个索引需要长期被隐
#藏，那么可以将其删除，因为索引的存在会影响插入、更新和删除的性能。

#使隐藏索引对查询优化器可见
#index_merge=on,index_merge_union=on,index_merge_sort_union=on,index_merge_intersection=on,engine_condition_pushdown=on,index_condition_pushdown=on,mrr=on,mrr_cost_based=on,block_nested_loop=on,batched_key_access=off,materialization=on,semijoin=on,loosescan=on,firstmatch=on,duplicateweedout=on,subquery_materialization_cost_based=on,use_index_extensions=on,condition_fanout_filter=on,derived_merge=on,use_invisible_indexes=off,skip_scan=on,hash_join=on,subquery_to_derived=off,prefer_ordering_index=on,hypergraph_optimizer=off,derived_condition_pushdown=on
SELECT @@optimizer_switch;
set session optimizer_switch="use_invisible_indexes=off";


SHOW VARIABLES WHERE variable_name IN (
 'log_bin_trust_function_creators',
 'transaction_isolation',
 'lower_case_table_names',
 'sql_mode',
 'character_set_server',
 'default_character_set',
 'innodb_large_prefix',
 'max_connections',
 'group_concat_max_len'
);


