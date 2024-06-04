CREATE DATABASE  IF NOT EXISTS `shoppear` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shoppear`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: shoppear
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'hogar'),(2,'hombres'),(3,'mujeres'),(4,'ninos'),(5,'mascotas'),(6,'tecnologia');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mediosdepago`
--

DROP TABLE IF EXISTS `mediosdepago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mediosdepago` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mediosdepago`
--

LOCK TABLES `mediosdepago` WRITE;
/*!40000 ALTER TABLE `mediosdepago` DISABLE KEYS */;
INSERT INTO `mediosdepago` VALUES (1,'credito','1324 6547 7894 6541'),(2,'debito','6574 1768 1465 9728'),(3,'credito','7298 3478 4891 3214'),(4,'debito','1326 1978 1645 3126'),(5,'credito','3124 5679 9874 4564');
/*!40000 ALTER TABLE `mediosdepago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden`
--

DROP TABLE IF EXISTS `orden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUsuario` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `idMedioDePago` int DEFAULT NULL,
  `direccionFactura` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden`
--

LOCK TABLES `orden` WRITE;
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
INSERT INTO `orden` VALUES (1,1,'2024-05-25',1,'Av Uade 234'),(2,2,'2024-05-25',2,'Juan el de la buena vista 725'),(3,3,'2024-05-25',3,'Malabia 1600'),(4,4,'2024-05-25',4,'Av Medrano 626'),(5,1,'2024-05-25',5,'Av Uade 234');
/*!40000 ALTER TABLE `orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenproducto`
--

DROP TABLE IF EXISTS `ordenproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordenproducto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idProducto` int DEFAULT NULL,
  `idOrden` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precioPagado` float DEFAULT NULL,
  `dctoAplicado` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenproducto`
--

LOCK TABLES `ordenproducto` WRITE;
/*!40000 ALTER TABLE `ordenproducto` DISABLE KEYS */;
INSERT INTO `ordenproducto` VALUES (1,1,1,2,24000,0),(2,7,1,1,28700,0),(3,10,1,3,73950,0),(4,15,2,2,4000,0),(5,22,2,1,30354,0),(6,28,2,1,197990,0),(7,3,3,1,72000,0),(8,12,3,2,1600,0),(9,20,3,1,17000,0),(10,8,4,1,32000,0),(11,16,4,2,45900,0),(12,23,4,1,18827,0),(13,4,5,1,350000,0),(14,14,5,1,9600,0),(15,27,5,2,14000,0);
/*!40000 ALTER TABLE `ordenproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `img` blob,
  `stock` int DEFAULT NULL,
  `idCategoria` int DEFAULT NULL,
  `descuento` float DEFAULT NULL,
  `idUsuario` int DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Almohada Oregon','Almohada comprimida de recuperación rápida 50x70cm',12000,NULL,0,1,0,1,1),(2,'Microondas ATMA','Microondas ATMA 700W de potencia con display digital. Medidas 44x25.9x35.2cm3',200000,NULL,2,1,0,2,1),(3,'Camara Dual Lens WIFI Exterior OWC-07 2 MP','Camara Dual Lens con doble rango de vision y vision nocturna full color.',72000,NULL,20,1,0,3,1),(4,'Silla Gamer Genotype X-Tron 1D','Silla gamer con asiento y respaldo de ecocuero y pana de calidad. Respaldo regulable de 90 a 180 grados.',350000,NULL,20,1,0,4,1),(5,'Sillon Saarinen','Sillon tapizado en tela, espuma de alta densidad. Altura regulable 82cm a 90cm.',288000,NULL,20,1,0,1,1),(6,'Pantalon Jean Hombre Lavado Jujuy Skinny','Pantalon de Jean etiqueta negra modelo N7 Lavado. Color negro',110000,NULL,20,2,0,2,1),(7,'Bermuda Elastico Negra','Bermuda de gabardina. Cintura con elastico y calce recto. Tiene dos bolsillos laterales y dos bolsillos traseros.',28700,NULL,20,2,0,3,1),(8,'Bolso Medialuna','Bolso con forma de medialuna. Dos formas de usar: en el hombro como cartera, o cruzado como riñonera. Tela liviana y suave color negro. Tira regulable. Bolsillos internos.',32000,NULL,20,2,0,4,1),(9,'Remera Boy Verde','Remera 100% Algodón. Molde clásico de hombre. Mangas grandes, larga.',21000,NULL,20,2,0,1,1),(10,'Bucito Panal Militar','Bucito de panal manga larga. Modelo clásico de hombre, oversized de mujer.',24650,NULL,20,2,0,2,1),(11,'Heybly Cat Tree - Torre grande para gatos de interior','Múltiples gatitos curiosos pueden acostarse en el suave lugar alto al mismo tiempo para observar el paisaje fuera de la ventana',225100,NULL,20,5,0,3,1),(12,'Rascador para Gato Fancy Pets','Poste rascador, ideal para que el gato se ejercite y pase horas de juego. Hecho de materiales no tóxico. Ideal para gatos pequeños y grandes. Ensamble las piezas y colóquelo cerca de un lugar amplio donde el gato tenga acceso fácil al poste.',800,NULL,20,5,0,4,1),(13,'Correa de paseo reforzada','Recomendamos esta correa para perros grandes y fuertes, adiestradores y paseadores. Es una correa altamente apreciada por quienes tienen perros grandes.',7177,NULL,20,5,0,1,1),(14,'Kit Combo Juguetes De Goma Perros Grandes Mascotas','Kit de juguetes para perros. Incluye hueso, pelota y aro. Ideal para masticar o lanzar. No toxico. Colores Surtidos.',9600,NULL,20,5,0,2,1),(15,'Barra de puño de mono de colores surtidos','Seguras y no tóxicas: hechas de hilos de algodón y poliéster de alta calidad de América del Norte, estas bolas de cuerda para perros Mammoth son juguetes seguros, fuertes y buenos para perros que mastican.',2000,NULL,20,5,0,3,1),(16,'Pollera Mujer Jean Negra Talle XS','Pollera de jean negro, estilo rota 47 Street. Talle XS.',22950,NULL,20,3,0,4,1),(17,'Crop Top Blusa','Crop Top Blusa Sexy Sin Manga Entallado Antro Fiesta',181,NULL,20,3,0,1,1),(18,'Remera Cuadrada Marron','Remera 100% Algodón. Mangas grandes, largo al botón del pantalón. Viene en talles S y M.',18500,NULL,20,3,0,2,1),(19,'Jean Algodón Mujer Recto Medio Newboat','No dejar en remojo. Lavar en agua fría separadamente. No usar blanqueador. No retorcer. Secar a la sombra. Plancha tibia.',99990,NULL,20,3,0,3,1),(20,'Crop Morley Blanco','Crop de morley color blanco. Elastizado, lavar solo con ropa blanca.',17000,NULL,20,3,0,4,1),(21,'Camiseta de viaje para niñas, Negro, S','Colores lisos: 100% algodón; Gris brezo: 90% algodón, 10% poliéster; Todos los demás: 50% algodón, 50% poliéster',7500,NULL,20,4,0,1,1),(22,'Campera BB de Piel','Campera para niña bebe de piel rosa marca Gepetto.',30354,NULL,20,4,0,2,1),(23,'Pantalón jean bb nena wide leg','Pantalón largo lila. Corte ancho. Marca Gepetto para niñas.',18827,NULL,20,4,0,3,1),(24,'Buzo gris para niño pelota.','Buzo cerrado manga larga color gris AIM FOR YOUR GOALS PLAY GREAT. 58% algodon, 42% polyester.',24990,NULL,20,4,0,4,1),(25,'Remera Bebe Beba Manga Larga Algodón Estampada','Remera Bebe Manga Larga Algodón. Colores disponibles: Verde - Gris',10000,NULL,20,4,0,1,1),(26,'Auriculares VI 8D Surround KINGSTAR','KINGSTAR-auriculares TWS inalámbricos por Bluetooth, cascos deportivos estéreo 8D con micrófono para Xiaomi, Huawei y iphone',8658,NULL,20,6,0,2,1),(27,'Cargador Original Tipo C Para iPhone 15 Pro De 20 W + Cable','Carga rapida, producto 100% original en caja sellada. Compatible con IOS.',7000,NULL,20,6,0,3,1),(28,'Audífonos Inalámbricos Wh-ch520 Blanco','El diseño over-ear genera una comodidad insuperable gracias a sus suaves almohadillas. Al mismo tiempo, su sonido envolvente del más alto nivel se convierte en el protagonista de la escena.',197990,NULL,20,6,0,4,1),(29,'Auriculares Deportivos Xiaomi','Auricular Deportivo Inalambrico Xiaomi Negro 12 horas de bateria. Compatible con cualquier dispositivo Bluetooth excepto cualquier producto de Apple.',178243,NULL,20,6,0,1,1),(30,'CARGADOR DE NETBOOK ORIGINAL GOBIERNO','Cargador para Netbook Gobierno 2017 2018 2019 G6 G7 de 11,6 12v 2a 24w pin 3,5 x 1,35mm Adaptado Características: - Voltaje de entrada: 100-240v',3652,NULL,20,6,0,2,1);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Carolina','Guevara','caritoG@gmail.com','cguevara','827ccb0eea8a706c4c34a16891f84e7b','Av Uade 234'),(2,'Joaco','Jawer','joacojota@gmail.com','jjawerbaum','215d7d08f19a9dbf49988823348d783b','Juan el de la buena vista 725'),(3,'Maru','Roitman','marur@gmail.com','maroitman','892a9944cf14665375630c06a1902152','Malabia 1600'),(4,'Marti','Fede','Martilamascapa@gmail.com','mfede','fceeb9b9d469401fe558062c4bd25954','Av Medrano 626');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-04 14:56:31
