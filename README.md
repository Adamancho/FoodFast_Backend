# FoodFast_Backend


Se tiene que crear la base de datos bajo el siguiente Script

``` sql
CREATE DATABASE `tabla` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `domicilio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_cliente` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) NOT NULL,
  `pedido` varchar(1000) NOT NULL,
  `precio` int NOT NULL,
  `metododepago` varchar(20) NOT NULL,
  `id_estado` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `estados` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`),
  UNIQUE KEY `estado_UNIQUE` (`estado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `historico_estados` (
  `id_domicilio` int NOT NULL,
  `id_estado` varchar(20) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  KEY `id_domicilios_idx` (`id_domicilio`),
  KEY `id_estados_idx` (`id_estado`),
  CONSTRAINT `id_domicilios` FOREIGN KEY (`id_domicilio`) REFERENCES `domicilio` (`id`),
  CONSTRAINT `id_estados` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`estado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


``` 
