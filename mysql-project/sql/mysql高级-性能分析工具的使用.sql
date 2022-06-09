#1.数据库的优化步骤
#首先观察服务器状态-->存在周期性的波动--->加缓存更改缓存失效策略
#首先观察服务器状态-->不存在周期性的波动--->开启慢查询--->EXPLAIN/DESCRIBE工具分析sql语句--->等待时间长--->调优服务器参数--->sql查询达到瓶颈--->读写分离、分库分表
#首先观察服务器状态-->不存在周期性的波动--->开启慢查询--->EXPLAIN/DESCRIBE工具分析sql语句--->执行时间长--->sql优化--->sql查询达到瓶颈--->读写分离、分库分表
#硬件--->系统配置--->数据库表结构--->sql及索引
	#从硬件到sql进行的优化效果是递增的，但是成本是递减的，所以我们在保证优化效果前提下尽量从sql/索引层面进行优化


#2.查看系统性能参数
SHOW STATUS LIKE 'connections'; #查看链接mysql服务器的次数
SHOW STATUS LIKE 'uptime'; #msql服务器的上线时间
SHOW STATUS LIKE 'innodb_rows%'; #对表的增删改查的记录
SHOW STATUS LIKE 'slow_queries'; #慢查询数量

#3.统计sql的查询成本--使用场景（它对于比较开销是非常有用的，特别是我们有好几种查询方式可选的时候）
SHOW STATUS LIKE 'last_query_cost';#value表示检索的页的数量
SHOW VARIABLES LIKE 'profiling';
SELECT * FROM user;
SHOW PROFILES;
SHOW PROFILE FOR QUERY 55;
SELECT @@profiling;


#---------- 慢查询 -------#
#1.开启慢查询功能
SELECT @@slow_query_log;  
SHOW VARIABLES LIKE 'slow_query_log';
SET GLOBAL slow_query_log = 'on';

#2.修改long_query_time的阈值
SHOW VARIABLES LIKE '%long_query_time%';
set global long_query_time = 1;

SHOW GLOBAL STATUS LIKE '%slow_queries%';

#------- 案例演示  -------#
DROP TABLE student;
CREATE TABLE `student` (
`id` INT(11) NOT NULL AUTO_INCREMENT,
`stuno` INT NOT NULL ,
`name` VARCHAR(20) DEFAULT NULL,
`age` INT(3) DEFAULT NULL,
`classId` INT(11) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
SHOW GLOBAL VARIABLES LIKE 'log_bin_trust_function_creators';
set global log_bin_trust_function_creators=1; # 不加global只是当前窗口有效。

DELIMITER //
CREATE PROCEDURE insert_stu1( START INT , max_num INT )
BEGIN
DECLARE i INT DEFAULT 0;
SET autocommit = 0; #设置手动提交事务
REPEAT #循环
SET i = i + 1; #赋值
INSERT INTO student (stuno, NAME ,age ,classId ) VALUES
((START+i),rand_string(6),rand_num(10,100),rand_num(10,1000));
UNTIL i = max_num
END REPEAT;
COMMIT; #提交事务
END //
DELIMITER ;

CALL insert_stu1(100001,4000000);

SELECT * from student;

SHOW STATUS LIKE 'last_query_cost';

SHOW GLOBAL STATUS LIKE '%slow_queries%';

#重新生成慢查询的日志文件
mysqladmin -uroot -p flush-logs slow 

#查询冗余索引
SELECT * FROM sys.schema_redundant_indexes;

#查询未使用过的索引
SELECT * FROM sys.schema_unused_indexes;

#查询索引的使用情况
SELECT index_name,rows_selected,rows_inserted,rows_updated,rows_deleted 
FROM sys.schema_index_statistics WHERE table_schema='disaster';

#查询表的访问量
SELECT table_schema,table_name,SUM(io_read_requests+io_write_requests) AS io
FROM sys.schema_table_statistics GROUP BY table_schema,table_name ORDER BY io DESC;

#查询占用bufferpool较多的表
SELECT object_schema,object_name,allocated,DATA
FROM sys.innodb_buffer_stats_by_table ORDER BY allocated LIMIT 10;

#查看表的全表扫描情况
SELECT * FROM sys.statements_with_full_table_scans WHERE db='disaster';

#监控sql执行的频率
SELECT db,exec_count,first_seen,last_seen,QUERY 
FROM sys.statements_with_sorting LIMIT 1;

#监控使用了临时表或者磁盘临时表的SQL
SELECT db,exec_count,tmp_tables,tmp_disk_tables,QUERY
FROM sys.statement_analysis WHERE tmp_tables>0 OR tmp_disk_tables>0
ORDER BY (tmp_tables+tmp_disk_tables) DESC;