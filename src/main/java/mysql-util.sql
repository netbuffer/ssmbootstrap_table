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