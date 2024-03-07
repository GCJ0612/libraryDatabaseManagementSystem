/*
SQLyog Community v13.1.2 (32 bit)
MySQL - 5.5.41 : Database - db1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db1` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db1`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(255) DEFAULT NULL,
  `noofcopies` int(11) DEFAULT NULL,
  `category` enum('Computer','Electroics','Mechanical') DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `book` */

insert  into `book`(`bookId`,`bookName`,`noofcopies`,`category`) values 
(1,'KGF',5,'Mechanical'),
(3,'MAX',5,'Computer'),
(5,'RRR',7,'Electroics'),
(6,'PS1',7,'Electroics'),
(7,'PS2',6,'Mechanical'),
(8,'PS2',6,'Mechanical'),
(9,'Salar',5,'Computer');

/*Table structure for table `borrowedbooks` */

DROP TABLE IF EXISTS `borrowedbooks`;

CREATE TABLE `borrowedbooks` (
  `bookid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `borrowedDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  KEY `bookid` (`bookid`),
  KEY `sid` (`sid`),
  CONSTRAINT `borrowedbooks_ibfk_1` FOREIGN KEY (`bookid`) REFERENCES `book` (`bookId`),
  CONSTRAINT `borrowedbooks_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `borrowedbooks` */

insert  into `borrowedbooks`(`bookid`,`sid`,`borrowedDate`,`returnDate`) values 
(1,3,'2024-01-12','2024-01-23'),
(7,6,'2024-01-05','2024-01-10'),
(5,8,'2024-11-12','2024-12-01'),
(8,9,'2024-01-15','2024-01-17'),
(9,11,'2024-01-27','2024-02-02');

/*Table structure for table `emp` */

DROP TABLE IF EXISTS `emp`;

CREATE TABLE `emp` (
  `empid` int(11) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `Salary` float DEFAULT NULL,
  `City` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `emp` */

insert  into `emp`(`empid`,`NAME`,`Salary`,`City`) values 
(102,'Ram',50000,'Bangalore'),
(102,'Vinay C',45000,''),
(103,'seenu',56000,'Chennai'),
(105,'Likith',45000,'Bengaluru'),
(107,'Anitha',45000,'Shmg');

/*Table structure for table `players` */

DROP TABLE IF EXISTS `players`;

CREATE TABLE `players` (
  `pid` int(11) NOT NULL,
  `pname` varchar(100) DEFAULT NULL,
  `teamname` varchar(100) DEFAULT NULL,
  `nof30` int(11) DEFAULT NULL,
  `nof50` int(11) DEFAULT NULL,
  `noof100` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `players` */

insert  into `players`(`pid`,`pname`,`teamname`,`nof30`,`nof50`,`noof100`) values 
(0,NULL,NULL,0,0,0),
(101,'Virat Kohli','RCB',12,15,80),
(102,'Rohit Sharma','KKR',23,45,55),
(103,NULL,NULL,0,0,0),
(104,'Pandya','LCG',4,5,6),
(112,'Dhoni','CSK',23,34,45);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `studentName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`sid`,`studentName`,`email`,`password`) values 
(3,'Goutham C','goutham@gmail.com','123456'),
(4,'Vinay','vinay@gmail.com','Vinay@2345'),
(5,'Likith','Likith@gmail.com','Likith@231'),
(6,'Shruthi B','Shru@gmail.com','kjsdfsbd'),
(7,'Seenu','Seenu@gmail.com','Seenu@2345'),
(8,'Santhosh Karthi','Karthi@gmail.com','Karthi'),
(9,'Rohan','rohan@gmail.com','RoHAn'),
(10,'Monika','monika@gmail.com','Monikas'),
(11,'Vinay C','vinay@gmail.com','Vinay@123456');

/*Table structure for table `user1` */

DROP TABLE IF EXISTS `user1`;

CREATE TABLE `user1` (
  `username` varchar(125) DEFAULT NULL,
  `password` varchar(125) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user1` */

insert  into `user1`(`username`,`password`) values 
('Goutham','12345'),
('Vinay','54321'),
('Goutham','12345'),
('Vinay','54321');

/* Function  structure for function  `mul` */

/*!50003 DROP FUNCTION IF EXISTS `mul` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` FUNCTION `mul`(X INT , y INT) RETURNS int(11)
BEGIN
	declare f int;
	set f=X*y;
	return f;
END */$$
DELIMITER ;

/* Procedure structure for procedure `get_emp_heighest_sal` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_emp_heighest_sal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_emp_heighest_sal`(in var1 int)
begin 
select * from emp order by salary desc limit var1;
end */$$
DELIMITER ;

/* Procedure structure for procedure `get_emp_sal` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_emp_sal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_emp_sal`()
begin
	select * from emp where Salary>=10000;
	select count(empid) as total_sal_count from emp where Salary>=40000;
end */$$
DELIMITER ;

/* Procedure structure for procedure `square` */

/*!50003 DROP PROCEDURE IF EXISTS  `square` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `square`(in x int , out f int)
begin
	set f=x*x;
end */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
