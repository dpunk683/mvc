-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.23 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных restaraunt_db
CREATE DATABASE IF NOT EXISTS `restaraunt_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `restaraunt_db`;


-- Дамп структуры для таблица restaraunt_db.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `dateOfBirth` datetime DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(35) DEFAULT NULL,
  `loyalityCardNo` varchar(20) NOT NULL,
  `oldLoyalityCardNo` varchar(20) DEFAULT NULL,
  `spentMoney` varchar(45) DEFAULT NULL,
  `RcpBody_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`RcpBody_id`),
  KEY `fk_Clients_RcpBody1_idx` (`RcpBody_id`),
  CONSTRAINT `fk_Clients_RcpBody1` FOREIGN KEY (`RcpBody_id`) REFERENCES `rcpbody` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы restaraunt_db.clients: ~0 rows (приблизительно)
DELETE FROM `clients`;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;


-- Дамп структуры для таблица restaraunt_db.ips
CREATE TABLE IF NOT EXISTS `ips` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip-address` varchar(50) DEFAULT '0',
  `TableId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы restaraunt_db.ips: ~5 rows (приблизительно)
DELETE FROM `ips`;
/*!40000 ALTER TABLE `ips` DISABLE KEYS */;
INSERT INTO `ips` (`id`, `ip-address`, `TableId`) VALUES
	(1, '10.1.1.5', 5),
	(2, '10.1.1.55', 3),
	(3, '10.1.1.89', 7),
	(4, '0:0:0:0:0:0:0:1', 0),
	(5, '192.168.8.144', 77);
/*!40000 ALTER TABLE `ips` ENABLE KEYS */;


-- Дамп структуры для таблица restaraunt_db.menu
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double DEFAULT NULL,
  `secondPrice` double DEFAULT '0',
  `about` varchar(250) DEFAULT NULL,
  `picture` varchar(50) NOT NULL DEFAULT 'no_photo.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы restaraunt_db.menu: ~3 rows (приблизительно)
DELETE FROM `menu`;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id`, `name`, `price`, `secondPrice`, `about`, `picture`) VALUES
	(1, 'Яичница глазунья', 15000, 10000, NULL, 'jaichnica-glazunja.jpg'),
	(3, 'Жопа с ушами', 45000, NULL, '', 'no_photo.jpg'),
	(4, '50 gramms of Cognac', 25000, 0, 'Высококачественный коньяк неизвестного происхождения. Скорее всего кондитерский, тайно вынесенный с ближайшего завода. В нем могут чуствоваться нотки хлорки, бережно добавленные туда поставщиками при разведении его водопроводной водой.', 'сognac.jpg');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


-- Дамп структуры для таблица restaraunt_db.rcpbody
CREATE TABLE IF NOT EXISTS `rcpbody` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productNo` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `starttime` varchar(50) DEFAULT NULL,
  `remote_ip_id` int(11) DEFAULT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`productNo`),
  KEY `fk_RcpBody_Menu1_idx` (`productNo`),
  KEY `FK_rcpbody_macs` (`remote_ip_id`),
  CONSTRAINT `fk_RcpBody_Menu1` FOREIGN KEY (`productNo`) REFERENCES `menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы restaraunt_db.rcpbody: ~2 rows (приблизительно)
DELETE FROM `rcpbody`;
/*!40000 ALTER TABLE `rcpbody` DISABLE KEYS */;
INSERT INTO `rcpbody` (`id`, `productNo`, `price`, `starttime`, `remote_ip_id`, `status`) VALUES
	(62, 4, 25000, '20160608_121210', 4, 0),
	(63, 1, 10000, '20160803_205323', 4, 0);
/*!40000 ALTER TABLE `rcpbody` ENABLE KEYS */;


-- Дамп структуры для таблица restaraunt_db.rcpheaders
CREATE TABLE IF NOT EXISTS `rcpheaders` (
  `id` int(11) NOT NULL,
  `sum` double NOT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `waiterId` int(11) DEFAULT NULL,
  `number` int(4) DEFAULT NULL,
  `zNumber` int(4) NOT NULL,
  `paymentType` int(5) NOT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`,`zNumber`),
  KEY `fk_RcpHeaders_Zreports1_idx` (`zNumber`),
  KEY `waiters_idx` (`waiterId`),
  KEY `Rcp_body_idx` (`number`),
  CONSTRAINT `fk_RcpHeaders_Zreports1` FOREIGN KEY (`zNumber`) REFERENCES `zreports` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Rcp_body` FOREIGN KEY (`number`) REFERENCES `rcpbody` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `waiters` FOREIGN KEY (`waiterId`) REFERENCES `waiters` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы restaraunt_db.rcpheaders: ~0 rows (приблизительно)
DELETE FROM `rcpheaders`;
/*!40000 ALTER TABLE `rcpheaders` DISABLE KEYS */;
/*!40000 ALTER TABLE `rcpheaders` ENABLE KEYS */;


-- Дамп структуры для таблица restaraunt_db.registrations
CREATE TABLE IF NOT EXISTS `registrations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `unn` int(9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы restaraunt_db.registrations: ~0 rows (приблизительно)
DELETE FROM `registrations`;
/*!40000 ALTER TABLE `registrations` DISABLE KEYS */;
/*!40000 ALTER TABLE `registrations` ENABLE KEYS */;


-- Дамп структуры для таблица restaraunt_db.waiters
CREATE TABLE IF NOT EXISTS `waiters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` int(5) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `cardNum` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы restaraunt_db.waiters: ~3 rows (приблизительно)
DELETE FROM `waiters`;
/*!40000 ALTER TABLE `waiters` DISABLE KEYS */;
INSERT INTO `waiters` (`id`, `login`, `name`, `type`, `pass`, `cardNum`) VALUES
	(1, 'manager', 'My Lord', 1, 'c4ca4238a0b923820dcc509a6f75849b', NULL),
	(2, 'waiter', 'Халдей', 2, 'c4ca4238a0b923820dcc509a6f75849b', NULL),
	(3, 'chief', 'ПОВАР', 3, 'c4ca4238a0b923820dcc509a6f75849b', NULL);
/*!40000 ALTER TABLE `waiters` ENABLE KEYS */;


-- Дамп структуры для таблица restaraunt_db.zreports
CREATE TABLE IF NOT EXISTS `zreports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rcpQuantity` int(4) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `counterStart` double DEFAULT NULL,
  `counterEnd` double DEFAULT NULL,
  `sales` double DEFAULT NULL,
  `fiscalNumber` varchar(45) DEFAULT NULL,
  `registrationInfo` int(5) NOT NULL,
  PRIMARY KEY (`id`,`registrationInfo`),
  KEY `fk_Zreports_Registrations_idx` (`registrationInfo`),
  CONSTRAINT `fk_Zreports_Registrations` FOREIGN KEY (`registrationInfo`) REFERENCES `registrations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы restaraunt_db.zreports: ~0 rows (приблизительно)
DELETE FROM `zreports`;
/*!40000 ALTER TABLE `zreports` DISABLE KEYS */;
/*!40000 ALTER TABLE `zreports` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
