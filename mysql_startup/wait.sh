#!/bin/bash

echo "Waiting for mysql"
until mysql -hccrisql -P3306 -uroot -p"$MYSQL_ROOT_PASSWORD" &> /dev/null
do
  printf "."
  sleep 3
done

echo -e "\nmysql ready"