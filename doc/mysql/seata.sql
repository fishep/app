SHOW DATABASES;

# DROP DATABASE seata;
CREATE DATABASE seata;

USE seata;

# DROP USER seata;
SELECT * FROM mysql.user where User = 'seata';
CREATE USER 'seata' IDENTIFIED BY 'seata';


# REVOKE ALL ON seata.* from 'seata'@'%';
SHOW GRANTS FOR seata;
GRANT ALL ON seata.* TO seata;

FLUSH PRIVILEGES;

#############################################################

# DROP USER 'seata'@'localhost';
# CREATE USER 'seata'@'localhost' IDENTIFIED BY 'seata';
# GRANT ALL ON seata.* TO 'seata'@'%';
