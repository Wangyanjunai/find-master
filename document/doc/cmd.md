apidoc -i ./src/main/java/com/potato369/find/portal/controller/ -o ./src/main/resources/apidoc/  -v
https://www.cnblogs.com/chiangchou/p/idea-debug.html
http://localhost:8761/
https://potato369.mynatapp.cc/swagger-ui.html
https://www.jianshu.com/p/05be40b9a7a3
https://spring.io/projects/spring-cloud
Lombok使用：
https://blog.csdn.net/motui/article/details/79012846
将极光推送第三方jar安装到本地仓库
mvn install:install-file -Dfile=./document/jar/jpush-client-3.4.8.jar -DgroupId=cn.jpush.api -DartifactId=jpush-sdk-java -Dversion=3.4.8 -Dpackaging=jar
MySQL日期与时间函数
https://www.cnblogs.com/willaty/p/8400141.html
手把手教你如何免费且光荣地使用正版IntelliJ IDEA
http://itmuch.com/other/how-to-use-idea-freely
IntelliJ IDEA For Mac 快捷键
https://www.jianshu.com/p/163a69cdafcc
keytool -genkeypair -alias mytestkey -keyalg RSA -dname "CN=Web Server,OU=Unit,O=Organization,L=City,S=State,C=US" -keypass changeme -keystore server.jks -storepass letmein

解决mysql ### Cause: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Expression #2 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'answer_tmp.aui.grade' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by
        ; bad SQL grammar []; nested exception is com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Expression #2 of SELECT list is not in GROUP BY clause and contains nonaggregated column 'answer_tmp.aui.grade' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by这个错误的两种方案：
网址：https://blog.csdn.net/qq_42175986/article/details/82384160

SET @@GLOBAL.sql_mode='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
SET @@GLOBAL.sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

docker容器相关
https://cloud.tencent.com/developer/article/1381096
https://blog.csdn.net/ShiXinXin_Harbour/article/details/97378507

-- 开发环境
nohup java -jar -server -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC answer-discovery-eureka-peer1.jar > server_eureka_8761.log 2>&1 &

nohup java -jar -server -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC answer-basic-web-8010.jar > client_app_portal_8010.log 2>&1 &

nohup java -jar -server -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC answer-cms-web-8020.jar > client_cms_portal_8020.log 2>&1 &

nohup java -jar -server -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC answer-app-web-8030.jar > client_balance_portal_8030.log 2>&1 &

-- 测试环境
nohup java -jar -server -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=peer3 -Dserver.port=8763 answer-discovery-eureka-peer3.jar > eureka_8763.log 2>&1 &

nohup java -jar -server -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=test -Dserver.port=8013 answer-basic-web-8013.jar > portal_8013.log 2>&1 &

nohup java -jar -server -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=test -Dserver.port=8023 answer-cms-web-8023.jar > client_cms_8023.log 2>&1 &

nohup java -jar -server -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Xms128m -Xmx128m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=test -Dserver.port=8033 answer-app-web-8033.jar > client_balance_8033.log 2>&1 &

-- 正式环境
nohup java -jar -server -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Xms256m -Xmx256m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=peer1 -Dserver.port=8761 answer-discovery-eureka-peer1.jar > eureka_8761.log 2>&1 &

nohup java -jar -server -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Xms256m -Xmx256m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=prod -Dserver.port=8011 answer-basic-web-8011.jar > portal_8011.log 2>&1 &

nohup java -jar -server -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Xms256m -Xmx256m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=prod -Dserver.port=8020 answer-cms-web-8020.jar > client_cms_8020.log 2>&1 &

nohup java -jar -server -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m -Xms256m -Xmx256m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=prod -Dserver.port=8031 answer-app-web-8031.jar > client_balance_8031.log 2>&1 &

Mysql数据库数据备份解决方法博客论文地址：
https://blog.csdn.net/weixin_42404727/article/details/89337549
https://blog.csdn.net/qq_35923749/article/details/79363364
https://blog.csdn.net/weixin_30511107/article/details/94829504

dev 开发环境swagger-UI: http://192.168.31.30:8084/find/swagger-ui.html
dev2开发环境swagger-UI: http://192.168.3.4:8084/find/swagger-ui.html
test测试环境swagger-UI: http://124.71.38.2:8084/find/swagger-ui.html
prod生产环境swagger-UI: http://8.135.36.45:8084/find/swagger-ui.html

mvn -pl com.potato369.find:find-user -am spring-boot:run mvn -pl com.potato369.find:find-message -am spring-boot:run mvn
-pl com.potato369.find:find-dynamic -am spring-boot:run mvn -pl com.potato369.find:find-portal -am spring-boot:run mvn
-pl com.potato369.find:find-admin -am spring-boot:run mvn -pl com.potato369.find:find-order -am spring-boot:run mvn -pl
com.potato369.find:find-log -am spring-boot:run mvn -pl com.potato369.find:find-config -am spring-boot:run