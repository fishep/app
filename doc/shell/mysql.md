```shell
cd pwd/

mysql < test.sql
mysql –uroot –proot < test.sql
mysql –uroot –proot -Dtest < test.sql

cat nacos.sql nacos-mysql.sql | mysql
cat seata.sql seata-mysql.sql | mysql
```