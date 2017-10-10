#!/bin/bash

echo "Waiting for mysql"
until mysql -hccrisql -P3306 -u"${datasource.username}" -p"${datasource.password}" -e "select 1;"&> /dev/null
do
  printf "."
  sleep 3
done

echo -e "\nmysql ready"

echo -e "Starting Tomcat"

catalina.sh run
