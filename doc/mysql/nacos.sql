SHOW DATABASES;

# DROP DATABASE nacos;
CREATE DATABASE nacos;

USE nacos;

# DROP USER nacos;
SELECT * FROM mysql.user where User = 'nacos';
CREATE USER 'nacos' IDENTIFIED BY 'nacos';


# REVOKE ALL ON nacos.* from 'nacos'@'%';
SHOW GRANTS FOR nacos;
GRANT ALL ON nacos.* TO nacos;

FLUSH PRIVILEGES;

#############################################################

# DROP USER 'nacos'@'localhost';
# CREATE USER 'nacos'@'localhost' IDENTIFIED BY 'nacos';
# GRANT ALL ON nacos.* TO 'nacos'@'%';
