use mysql;
SHOW TABLES;
SHOW GRANTS FOR 'root'@'localhost';
SELECT DATABASE();

#查看用户
SELECT host,user FROM user;

#创建用户
CREATE USER 'disaster_practice'@'localhost' IDENTIFIED by '123456';
INSERT INTO mysql.`user`(user,host) VALUES('disaster_practice','localhost');

#删除用户
DROP USER 'disaster_practice'@'localhost';

DELETE FROM mysql.user WHERE Host='localhost' AND User='disaster_practice';
FLUSH PRIVILEGES;

#修改用户
UPDATE mysql.user SET USER='li4' WHERE USER='wang5';
FLUSH PRIVILEGES;

#设置用户密码
# 修改当前用户的密码：（MySQL5.7测试有效）
SET PASSWORD = PASSWORD('123456');

ALTER USER 'disaster_practice'@'localhost' IDENTIFIED BY 'abc123'

#密码管理
# 1.密码过期管理（可通过my.ini/myisam_test.cnf文件设置永久有效）
ALTER USER 'disaster_practice'@'localhost' PASSWORD EXPIRE;

SET PERSIST default_password_lifetime = 180; # 建立全局策略，设置密码每隔180天过期

#设置kangshifu账号密码每90天过期：
CREATE USER 'disaster_practice'@'localhost' PASSWORD EXPIRE INTERVAL 90 DAY;
ALTER USER 'disaster_practice'@'localhost' PASSWORD EXPIRE INTERVAL 90 DAY;

#设置密码永不过期：
CREATE USER 'disaster_practice'@'localhost' PASSWORD EXPIRE NEVER;
ALTER USER 'disaster_practice'@'localhost' PASSWORD EXPIRE NEVER;

#延用全局密码过期策略：
CREATE USER 'disaster_practice'@'localhost' PASSWORD EXPIRE DEFAULT;
ALTER USER 'disaster_practice'@'localhost' PASSWORD EXPIRE DEFAULT;

#密码重用策略（可通过my.ini/myisam_test.cnf文件设置永久有效）
SET PERSIST password_history = 6; #设置不能选择最近使用过的6个密码
SET PERSIST password_reuse_interval = 365; #设置不能选择最近一年内的密码

#不能使用最近5个密码：
CREATE USER 'disaster_practice'@'localhost' PASSWORD HISTORY 5;
ALTER USER 'disaster_practice'@'localhost' PASSWORD HISTORY 5;

#不能使用最近365天内的密码：
CREATE USER 'disaster_practice'@'localhost' PASSWORD REUSE INTERVAL 365 DAY;
ALTER USER 'disaster_practice'@'localhost' PASSWORD REUSE INTERVAL 365 DAY;

#既不能使用最近5个密码，也不能使用365天内的密码
CREATE USER 'disaster_practice'@'localhost'
PASSWORD HISTORY 5
PASSWORD REUSE INTERVAL 365 DAY;

#------------------- 权限管理 --------------
#查看权限
 show privileges;
SHOW GRANTS FOR 'disaster_practice'@'localhost';
#给用户直接赋予权限
GRANT SELECT,INSERT,DELETE,UPDATE ON *.* TO 'disaster_practice'@'localhost' ;

GRANT ALL PRIVILEGES ON *.* TO 'disaster_practice'@'localhost'  WITH GRANT OPTION;#赋予所有权限

#收回权限
REVOKE ALL PRIVILEGES ON *.* FROM 'disaster_practice'@'localhost' ;


#----------------- 角色管理 -------------------
#创建角色
CREATE ROLE 'd_manager'@'localhost';

#删除角色
DROP ROLE 'd_manager'@'localhost';

SHOW PRIVILEGES\G;#navicat无法使用\G
#给角色赋予权限
GRANT SELECT ON *.* TO 'd_manager'@'localhost';

#查看角色权限
SHOW GRANTS FOR 'd_manager'@'localhost';

#回收角色权限
REVOKE SELECT ON *.*  FROM 'd_manager'@'localhost';

#给用户赋予角色
GRANT 'd_manager'@'localhost' TO 'disaster'@'localhost';
SHOW GRANTS FOR 'disaster'@'localhost';
#激活角色
SET DEFAULT ROLE ALL TO 'd_manager'@'localhost';

SET DEFAULT ROLE ALL TO
'dev1'@'localhost',
'read_user1'@'localhost',
'read_user2'@'localhost',
'rw_user1'@'localhost';

#想通过角色赋值权限给用户，链接数据库时需要开启下面配置
show variables like 'activate_all_roles_on_login';
SET GLOBAL activate_all_roles_on_login=ON;

#撤销用户的角色
REVOKE 'd_manager'@'localhost' FROM 'disaster'@'localhost';