#ssmbootstrap_table demo
## 技术栈
* spring+springmvc+mybatis
* poi3
* 自定义标签开发
* bootstrap3
* bootstrap-table1.9
* druid
* weui
* log4jdbc/log4jdbc-remix/log4jdbc-log4j2
* hibernate-validator
* [spring-jsonp-support](https://github.com/bhagyas/spring-jsonp-support)
* SUI mobile
* lombok

## How to run
项目采用maven构建，运行前请先执行src/main/java下的sql脚本到你的mysql数据库中，然后修改src/main/resource下的jdbc.properties配置文件中的数据库配置信息，进入项目目录，执行mvn tomcat7:run来运行

git->clone;eclipse->File->Import->Existing Maven projects，导入到eclipse后，等maven依赖下载完，右键项目，run as->maven build->tomcat7:run

#![](src/main/webapp/image/sys1.png)
#![](src/main/webapp/image/sys2.png)