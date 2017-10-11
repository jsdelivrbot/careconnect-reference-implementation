#!/usr/bin/env bash

#echo "Waiting for mysql"
#MYSQL_USER=$1
#MYSQL_PASSWORD=$2
#echo "Database Username: $MYSQL_USER"
#echo "Database Password: $MYSQL_PASSWORD"
#until mysql -hccrisql -P3306 -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "select 1;"&> /dev/null
#do
#  printf "."
#  sleep 3
#done

#echo -e "\nmysql ready"

#echo -e "Starting Tomcat"

catalina.sh run
