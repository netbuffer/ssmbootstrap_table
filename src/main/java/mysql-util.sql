-- 查询select次数
show  GLOBAL status like 'com_select';
-- 查询insert次数
show  GLOBAL status like 'com_insert';
-- 查询update次数
show  GLOBAL status like 'com_update';
-- 查询delete次数
show  GLOBAL status like 'com_delete';
-- 查询活跃连接
show status like 'connections';
-- 查询慢查询次数
show  global status like 'slow_queries';
-- 查询慢查询设置
show variables like 'long_query_time';
-- 锁表/解锁使用http://www.111cn.net/database/mysql/55482.htm
-- 锁定数据表，避免在备份过程中，表被更新,这个时候insert update delete等操作会阻塞，直到UNLOCK TABLES;其它线程可以读不可写
LOCK TABLES user READ;
-- 当前线程拥有读写权限，其它线程不能进行读写
LOCK TABLES user WRITE;
-- 解锁表，释放当前线程加的锁
UNLOCK TABLES;