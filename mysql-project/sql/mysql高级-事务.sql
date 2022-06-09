#显式事务---关闭隐式事务对DML有效，DDL语句mysql默认就会提交事务
SHOW VARIABLES LIKE 'autocommit';

SET autocommit = off;

CREATE TABLE trx_table1(id INT PRIMARY KEY);

INSERT INTO trx_table1(id) VALUES(1),(2);

SELECT * FROM trx_table1;


BEGIN;
INSERT INTO trx_table1(id) VALUES(3);
ROLLBACK
COMMIT;


BEGIN;
INSERT INTO trx_table1(id) VALUES(5);
INSERT INTO trx_table1(id) VALUES(6);
SAVEPOINT x;
INSERT INTO trx_table1(id) VALUES(7);
ROLLBACK TO x;

#隐式事务
SET autocommit = on;

INSERT INTO trx_table1(id) VALUES(4);

ROLLBACK

#
SELECT * FROM trx_table1;


#------------ 事务隔离级别  ---------------#
#查看事务隔离级别
SHOW SESSION VARIABLES LIKE 'transaction_isolation';

SELECT @@GLOBAL.transaction_isolation;

SELECT @@session.transaction_isolation;

#设置事务隔离级别
SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
  
SET GLOBAL TRANSACTION_ISOLATION = 'READ-UNCOMMITTED';
SET SESSION TRANSACTION_ISOLATION = 'READ-UNCOMMITTED';



SHOW VARIABLES LIKE 'innodb_flush_log_at_trx_commit';

#redo log buffer 大小，默认 16M ，最大值是4096M，最小值为1M。
show variables like '%innodb_log_buffer_size%';

#redo log file 大小，默认512M，最大可以512G
SHOW VARIABLES LIKE '%innodb_log_file_size%';

#redo log file日志组，对应的就是mysql文件下的ib_logfile_0和ib_logfile_1
SHOW VARIABLES LIKE '%innodb_log_files_in_group%';
