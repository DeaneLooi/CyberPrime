
delimiter $$

CREATE DATABASE `cyberprime` /*!40100 DEFAULT CHARACTER SET latin1 */$$

CREATE TABLE `client` (
  `imageHash` varchar(255) NOT NULL,
  `imageSize` int(11) NOT NULL,
  `imageExtension` varchar(45) NOT NULL,
  `userId` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `pattern` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `activation` varchar(45) NOT NULL,
  PRIMARY KEY (`imageHash`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


CREATE TABLE `notifications` (
  `sender` varchar(255) NOT NULL,
  `receiver` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `myuser`@`localhost` 
    SQL SECURITY DEFINER
VIEW `activation` AS
    select 
        `client`.`activation` AS `activation`,
        `client`.`token` AS `token`,
        `client`.`userId` AS `userId`
    from
        `client`

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `myuser`@`localhost` 
    SQL SECURITY DEFINER
VIEW `checkuser` AS
    select 
        `client`.`userId` AS `userId`, `client`.`email` AS `email`
    from
        `client`
        
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `myuser`@`localhost` 
    SQL SECURITY DEFINER
VIEW `logininfo` AS
    select 
        `client`.`pattern` AS `pattern`,
        `client`.`userId` AS `userId`,
        `client`.`imageHash` AS `imageHash`
    from
        `client`

CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `myuser`@`localhost` 
    SQL SECURITY DEFINER
VIEW `registration` AS
    select 
        `client`.`imageHash` AS `imageHash`,
        `client`.`imageSize` AS `imageSize`,
        `client`.`imageExtension` AS `imageExtension`,
        `client`.`userId` AS `userId`,
        `client`.`email` AS `email`,
        `client`.`pattern` AS `pattern`,
        `client`.`token` AS `token`,
        `client`.`activation` AS `activation`
    from
        `client`
        
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `myuser`@`localhost` 
    SQL SECURITY DEFINER
VIEW `token` AS
    select 
        `client`.`token` AS `token`, `client`.`userId` AS `userId`
    from
        `client`
        
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `myuser`@`localhost` 
    SQL SECURITY DEFINER
VIEW `updateclients` AS
    select 
        `client`.`imageHash` AS `imageHash`,
        `client`.`imageSize` AS `imageSize`,
        `client`.`imageExtension` AS `imageExtension`,
        `client`.`email` AS `email`,
        `client`.`pattern` AS `pattern`,
        `client`.`userId` AS `userId`
    from
        `client`
