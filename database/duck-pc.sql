-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 09-12-2020 a las 23:08:23
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `duck-pc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `cli_rut` varchar(12) NOT NULL,
  `cli_nombre` varchar(50) NOT NULL,
  `cli_apellido` varchar(50) NOT NULL,
  `cli_telefono` varchar(20) NOT NULL,
  `cli_correo` varchar(50) NOT NULL,
  `cli_direccion` varchar(100) NOT NULL,
  `cli_descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cli_rut`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cli_rut`, `cli_nombre`, `cli_apellido`, `cli_telefono`, `cli_correo`, `cli_direccion`, `cli_descripcion`) VALUES
('19.923.707-1', 'Diego Ismael', 'Diaz Duran', '+56950685608', 'diegodiazduran10@gmail.com', 'Hualañe', 'cliente habitual'),
('99.999.999-9', 'Usuario sin registro', 'null', '+56999999999', 'example@gmail.com', 'null', 'null');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE IF NOT EXISTS `compra` (
  `com_nofolio` int(11) NOT NULL,
  `com_prov_id` int(11) NOT NULL,
  `com_fecha` date NOT NULL,
  `com_total_bruto` double NOT NULL,
  `com_total_neto` double NOT NULL,
  PRIMARY KEY (`com_nofolio`),
  KEY `com_prov_id_idx` (`com_prov_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`com_nofolio`, `com_prov_id`, `com_fecha`, `com_total_bruto`, `com_total_neto`) VALUES
(484192, 1, '2020-10-21', 1459950, 1737340);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_compra`
--

CREATE TABLE IF NOT EXISTS `detalle_compra` (
  `detcom_id` int(11) NOT NULL AUTO_INCREMENT,
  `detcom_com_nofolio` int(11) NOT NULL,
  `detcom_pro_codigo` int(11) NOT NULL,
  `detcom_cantidad` int(11) NOT NULL,
  `detcom_total` double NOT NULL,
  PRIMARY KEY (`detcom_id`),
  KEY `detcom_com_nofolio_idx` (`detcom_com_nofolio`),
  KEY `detcom_pro_codigo_idx` (`detcom_pro_codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Volcado de datos para la tabla `detalle_compra`
--

INSERT INTO `detalle_compra` (`detcom_id`, `detcom_com_nofolio`, `detcom_pro_codigo`, `detcom_cantidad`, `detcom_total`) VALUES
(9, 484192, 32321, 5, 125000),
(10, 484192, 67312, 5, 100000),
(11, 484192, 76431, 5, 110000),
(12, 484192, 78213, 5, 250000),
(13, 484192, 78321, 5, 375000),
(14, 484192, 78377, 5, 250000),
(15, 484192, 78432, 5, 135000),
(16, 484192, 87372, 5, 114950);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_reparacion`
--

CREATE TABLE IF NOT EXISTS `detalle_reparacion` (
  `detrep_id` int(11) NOT NULL AUTO_INCREMENT,
  `detrep_rep_id` int(11) NOT NULL,
  `detrep_usu_id` int(11) NOT NULL,
  `detrep_pro_codigo` int(11) NOT NULL,
  `detrep_cantidad` int(11) NOT NULL,
  `detrep_total` int(11) NOT NULL,
  PRIMARY KEY (`detrep_id`),
  KEY `detrep_pro_codigo_idx` (`detrep_pro_codigo`),
  KEY `detrep_rep_id_idx` (`detrep_rep_id`),
  KEY `detrep_ven_id_idx` (`detrep_usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

CREATE TABLE IF NOT EXISTS `detalle_venta` (
  `det_id` int(11) NOT NULL AUTO_INCREMENT,
  `det_ven_id` int(11) NOT NULL,
  `det_pro_codigo` int(11) NOT NULL,
  `det_cantidad` int(11) NOT NULL,
  `det_total` int(11) NOT NULL,
  PRIMARY KEY (`det_id`),
  KEY `det_ven_id_idx` (`det_ven_id`),
  KEY `det_pro_codigo_idx` (`det_pro_codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `detalle_venta`
--

INSERT INTO `detalle_venta` (`det_id`, `det_ven_id`, `det_pro_codigo`, `det_cantidad`, `det_total`) VALUES
(1, 1, 32321, 1, 62990),
(2, 1, 78377, 2, 26500),
(3, 2, 78432, 1, 69990),
(4, 2, 32321, 2, 62990),
(5, 3, 87372, 1, 29990),
(6, 3, 67312, 2, 24990);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nivel_usuario`
--

CREATE TABLE IF NOT EXISTS `nivel_usuario` (
  `niv_id` int(3) NOT NULL,
  `niv_tipo` varchar(15) NOT NULL,
  PRIMARY KEY (`niv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nivel_usuario`
--

INSERT INTO `nivel_usuario` (`niv_id`, `niv_tipo`) VALUES
(1, 'Administrador'),
(2, 'Empleado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE IF NOT EXISTS `producto` (
  `pro_codigo` int(5) NOT NULL,
  `pro_descripcion` varchar(100) NOT NULL,
  `pro_precio_costo` int(11) NOT NULL,
  `pro_precio_venta` int(11) NOT NULL,
  `pro_stock` int(11) NOT NULL,
  PRIMARY KEY (`pro_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`pro_codigo`, `pro_descripcion`, `pro_precio_costo`, `pro_precio_venta`, `pro_stock`) VALUES
(32321, 'Teclado Kingston HyperX Alloy FPS Cherry MX red', 50000, 62990, 2),
(67312, 'Mouse Logitech G203 Lightsync Black', 20000, 24990, 3),
(76431, 'Fuente EVGA 600 BQ (110-BQ-0600-K1) (600 WT)', 75000, 89990, 5),
(78213, 'RAM Kingston HyperX Fury Black 8GB', 25000, 30000, 5),
(78321, 'Teclado Redragon KUMARA K552 (Luz Roja)', 27000, 32990, 5),
(78377, 'SSD Crucial BX500 240GB', 22000, 26500, 3),
(78432, 'Monitor Sharkoon TG4 RGB', 50000, 69990, 3),
(87372, 'HDD Western Digital Blue 1 TB (WD10EZRZ)', 22990, 29990, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE IF NOT EXISTS `proveedor` (
  `prov_id` int(11) NOT NULL AUTO_INCREMENT,
  `prov_nombre` varchar(50) NOT NULL,
  `prov_direccion` varchar(100) NOT NULL,
  `prov_telefono` varchar(12) NOT NULL,
  `prov_descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`prov_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`prov_id`, `prov_nombre`, `prov_direccion`, `prov_telefono`, `prov_descripcion`) VALUES
(1, 'Ingram Micro', 'Providencia 1760 P11, Santiago', '+56956789276', 'Muy bueno'),
(2, 'Distec Chile', 'Las condes, 223 Calle falsa, Santiago', '+56978564567', 'Normal');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reparacion`
--

CREATE TABLE IF NOT EXISTS `reparacion` (
  `rep_id` int(11) NOT NULL AUTO_INCREMENT,
  `rep_usu_id` int(11) NOT NULL,
  `rep_cli_rut` varchar(12) NOT NULL,
  `rep_fechaIngreso` date NOT NULL,
  `rep_fechaSalida` date DEFAULT NULL,
  `rep_monto` double DEFAULT NULL,
  `rep_estado` varchar(20) NOT NULL,
  `rep_artefacto` varchar(50) NOT NULL,
  `rep_problema` varchar(100) NOT NULL,
  PRIMARY KEY (`rep_id`),
  KEY `rep_usu_id_idx` (`rep_usu_id`),
  KEY `rep_cli_rut_idx` (`rep_cli_rut`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Volcado de datos para la tabla `reparacion`
--

INSERT INTO `reparacion` (`rep_id`, `rep_usu_id`, `rep_cli_rut`, `rep_fechaIngreso`, `rep_fechaSalida`, `rep_monto`, `rep_estado`, `rep_artefacto`, `rep_problema`) VALUES
(7, 1, '19.923.707-1', '2020-11-21', '2020-11-21', 0, 'Entregado', 'Notebook', 'calló al agua'),
(8, 1, '99.999.999-9', '2020-11-22', '2020-12-03', 5000, 'Entregado', 'Notebook', 'Sufrió un golpe (Diego Diaz)'),
(9, 1, '19.923.707-1', '2020-11-22', NULL, 0, 'Pendiente', 'Impresora', 'Tonner malo jas jas'),
(10, 1, '19.923.707-1', '2020-11-29', '2020-11-29', 10000, 'Entregado', 'Notebook', 'Sufrió un golpe desde una altura considerable');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `usu_id` int(11) NOT NULL AUTO_INCREMENT,
  `niv_id` int(2) NOT NULL,
  `usu_user` varchar(30) NOT NULL,
  `usu_pass` varchar(100) NOT NULL,
  `usu_name` varchar(50) NOT NULL,
  `usu_correo` varchar(50) NOT NULL,
  PRIMARY KEY (`usu_id`),
  KEY `niv_id_idx` (`niv_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`usu_id`, `niv_id`, `usu_user`, `usu_pass`, `usu_name`, `usu_correo`) VALUES
(1, 1, 'Admin', 'FHQeJ25NTao=', 'FELIPE ESTABAN MIRANDA MARABOLÍ', 'felipemirandama@gmail.com'),
(2, 2, 'ddiaz', 'vMkPxpXgXpaPjnFUn3U53g==', 'DIEGO DIAZ DURAN', 'diegodiazduran10@gmail.com'),
(3, 2, 'natalia', 'uc3tiH5Mr74=', 'NATALIA ANDREA ARAVENA MELENDEZ', 'natalia.aravena.melendez@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE IF NOT EXISTS `venta` (
  `ven_id` int(11) NOT NULL AUTO_INCREMENT,
  `ven_cli_rut` varchar(12) NOT NULL,
  `ven_usu_id` int(11) NOT NULL,
  `ven_fecha` date NOT NULL,
  `ven_monto` int(11) NOT NULL,
  PRIMARY KEY (`ven_id`),
  KEY `ven_cli_rut_idx` (`ven_cli_rut`),
  KEY `ven_usu_id_idx` (`ven_usu_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`ven_id`, `ven_cli_rut`, `ven_usu_id`, `ven_fecha`, `ven_monto`) VALUES
(1, '19.923.707-1', 1, '2020-11-18', 115990),
(2, '19.923.707-1', 1, '2020-11-18', 195970),
(3, '19.923.707-1', 1, '2020-11-18', 79970);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `com_prov_id` FOREIGN KEY (`com_prov_id`) REFERENCES `proveedor` (`prov_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalle_compra`
--
ALTER TABLE `detalle_compra`
  ADD CONSTRAINT `detcom_com_nofolio` FOREIGN KEY (`detcom_com_nofolio`) REFERENCES `compra` (`com_nofolio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detcom_pro_codigo` FOREIGN KEY (`detcom_pro_codigo`) REFERENCES `producto` (`pro_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalle_reparacion`
--
ALTER TABLE `detalle_reparacion`
  ADD CONSTRAINT `detrep_pro_codigo` FOREIGN KEY (`detrep_pro_codigo`) REFERENCES `producto` (`pro_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detrep_rep_id` FOREIGN KEY (`detrep_rep_id`) REFERENCES `reparacion` (`rep_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detrep_ven_id` FOREIGN KEY (`detrep_usu_id`) REFERENCES `usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `detalle_venta`
--
ALTER TABLE `detalle_venta`
  ADD CONSTRAINT `det_pro_codigo` FOREIGN KEY (`det_pro_codigo`) REFERENCES `producto` (`pro_codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `det_ven_id` FOREIGN KEY (`det_ven_id`) REFERENCES `venta` (`ven_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `reparacion`
--
ALTER TABLE `reparacion`
  ADD CONSTRAINT `rep_cli_rut` FOREIGN KEY (`rep_cli_rut`) REFERENCES `cliente` (`cli_rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `rep_usu_id` FOREIGN KEY (`rep_usu_id`) REFERENCES `usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `niv_id` FOREIGN KEY (`niv_id`) REFERENCES `nivel_usuario` (`niv_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `ven_cli_rut` FOREIGN KEY (`ven_cli_rut`) REFERENCES `cliente` (`cli_rut`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ven_usu_id` FOREIGN KEY (`ven_usu_id`) REFERENCES `usuario` (`usu_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
