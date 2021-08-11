#!/bin/bash
# shellcheck disable=SC2046
# shellcheck disable=SC2009
kill -9 $(ps -ef | grep java | awk '{print $2}')
cd /d/Users/wangyanjun/workspace/prod/find-master || exit
git pull
mvn clean package
rm -rf ~/jar/*.jar
mv find-admin/target/*.jar ~/jar
mv find-dynamic/target/*.jar ~/jar
mv find-message/target/*.jar ~/jar
mv find-order/target/*.jar ~/jar
mv find-portal/target/*.jar ~/jar
mv find-user/target/*.jar ~/jar
cd ~/jar || exit
nohup java -jar -server -Xmx128m -Xss16m -Xms2m -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dcom.alibaba.nacos.client.naming.ctimeout=5000 -Dspring.profiles.active=dev1 -Dserver.port=8081 find-user-8081.jar >find-user-8081.log 2>&1 &
nohup java -jar -server -Xmx128m -Xss16m -Xms2m -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dcom.alibaba.nacos.client.naming.ctimeout=5000 -Dspring.profiles.active=dev1 -Dserver.port=8082 find-message-8082.jar >find-message-8082.log 2>&1 &
nohup java -jar -server -Xmx128m -Xss16m -Xms2m -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dcom.alibaba.nacos.client.naming.ctimeout=5000 -Dspring.profiles.active=dev1 -Dserver.port=8083 find-dynamic-8083.jar >find-dynamic-8083.log 2>&1 &
nohup java -jar -server -Xmx128m -Xss16m -Xms2m -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dcom.alibaba.nacos.client.naming.ctimeout=5000 -Dspring.profiles.active=dev1 -Dserver.port=8084 find-portal-8084.jar >find-portal-8084.log 2>&1 &
#nohup java -jar -server -Xmx128m -Xss16m -Xms2m -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dcom.alibaba.nacos.client.naming.ctimeout=5000 -Dspring.profiles.active=dev1 -Dserver.port=8085 find-admin-8085.jar >find-admin-8085.log 2>&1 &
#nohup java -jar -server -Xmx128m -Xss16m -Xms2m -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dcom.alibaba.nacos.client.naming.ctimeout=5000 -Dspring.profiles.active=dev1 -Dserver.port=8086 find-order-8086.jar >find-order-8086.log 2>&1 &
exit 0