FROM mysql:8.0.32
COPY ./docker/mysql/nacos-mysql-schema.sql /docker-entrypoint-initdb.d/nacos-mysql.sql
COPY ./docker/mysql/user-mysql-schema.sql /docker-entrypoint-initdb.d/user-mysql.sql
RUN chown -R mysql:mysql /docker-entrypoint-initdb.d/nacos-mysql.sql
RUN chown -R mysql:mysql /docker-entrypoint-initdb.d/user-mysql.sql
EXPOSE 3306
CMD ["mysqld", "--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]