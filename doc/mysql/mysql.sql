SHOW DATABASES;
DROP DATABASE test;
CREATE DATABASE test;
USE test;

DROP USER fishep;
SELECT * FROM mysql.user WHERE User = 'fishep';
CREATE USER fishep IDENTIFIED BY 'fishep';

SET PASSWORD FOR root@localhost = PASSWORD ('root');
SET PASSWORD FOR root@localhost= PASSWORD ('');
SET PASSWORD FOR 'fishep'@'%' = PASSWORD ('fishep');
SET PASSWORD FOR 'fishep'@'%' = PASSWORD ('');
ALTER USER 'fishep'@'%' IDENTIFIED BY 'fishep';
ALTER USER 'fishep'@'%' IDENTIFIED BY '';

REVOKE ALL ON test.* FROM 'fishep'@'%';
SHOW GRANTS FOR fishep;
GRANT ALL ON test.* TO 'fishep'@'%';
GRANT ALL ON *.* TO fishep WITH GRANT OPTION;
FLUSH PRIVILEGES;

SELECT * FROM mysql.user;
UPDATE mysql.user SET Host = 'localhost' WHERE User = 'fishep';
UPDATE mysql.user SET Host = '%' WHERE User = 'fishep';

###################################################################

ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
ALTER USER 'root'@'localhost' IDENTIFIED BY '';
UPDATE mysql.user SET Host = 'localhost' WHERE User = 'root';
UPDATE mysql.user SET Host = '%' WHERE User = 'root';

ALTER USER 'fishep'@'%' IDENTIFIED BY 'fishep';
ALTER USER 'fishep'@'%' IDENTIFIED BY '';
UPDATE mysql.user SET Host = '%' WHERE User = 'fishep';