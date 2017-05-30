-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 29-05-2017 a las 22:09:46
-- Versión del servidor: 5.1.37
-- Versión de PHP: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `herramientas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargos`
--

CREATE TABLE IF NOT EXISTS `cargos` (
  `idCargo` int(11) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(20) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idCargo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `cargos`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descargos`
--

CREATE TABLE IF NOT EXISTS `descargos` (
  `idDescargo` int(11) NOT NULL AUTO_INCREMENT,
  `idPrestamo` int(11) DEFAULT NULL,
  `idEmpleado` int(11) NOT NULL,
  `idDetallePrestamo` int(11) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idDescargo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `descargos`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleprestamos`
--

CREATE TABLE IF NOT EXISTS `detalleprestamos` (
  `idDetallePrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `idPrestamos` int(11) DEFAULT NULL,
  `idHerramienta` int(11) DEFAULT NULL,
  `estadoHerramienta` int(2) NOT NULL,
  `cantidad` int(3) DEFAULT NULL,
  PRIMARY KEY (`idDetallePrestamo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `detalleprestamos`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleadocargo`
--

CREATE TABLE IF NOT EXISTS `empleadocargo` (
  `idEmpleadoCargo` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpleado` int(11) DEFAULT NULL,
  `idCargo` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idEmpleadoCargo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `empleadocargo`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE IF NOT EXISTS `empleados` (
  `idEmpleado` int(11) NOT NULL AUTO_INCREMENT,
  `pNombre` varchar(20) DEFAULT NULL,
  `sNombre` varchar(20) DEFAULT NULL,
  `pApellido` varchar(20) DEFAULT NULL,
  `sApellido` varchar(20) DEFAULT NULL,
  `dui` varchar(10) DEFAULT NULL,
  `nit` varchar(20) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `estado` int(1) DEFAULT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idEmpleado`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Volcar la base de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`idEmpleado`, `pNombre`, `sNombre`, `pApellido`, `sApellido`, `dui`, `nit`, `direccion`, `telefono`, `estado`, `fecha`) VALUES
(1, 'Julio', 'Alberto', 'Hernández', 'Castillo', '12345678', '1217-2222-111-3', 'col. blablabla', '5555-5555', 1, '2017-05-29'),
(2, 'periquita', 'maria', 'robles', 'montesco', '23452378', '1111-234567-222-3', 'col buenos aires', '2222-3333', 1, '2017-03-02'),
(3, 'sol', 'fermina', 'flores', 'perez', '21333378', '1221-236667-211-4', 'col curruncha', '4444-7777', 2, '2017-03-18'),
(4, 'rubia', 'sofia', 'marquez', 'pineda', '7554233', '5555-2334347-2634-8', 'col pacifica', '2567-3444', 1, '2017-06-13');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleadousuarios`
--

CREATE TABLE IF NOT EXISTS `empleadousuarios` (
  `idEmpleadoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpleado` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idEmpleadoUsuario`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `empleadousuarios`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `herramientas`
--

CREATE TABLE IF NOT EXISTS `herramientas` (
  `idHerramienta` int(11) NOT NULL AUTO_INCREMENT,
  `herramienta` varchar(50) DEFAULT NULL,
  `descripcion` varchar(100) NOT NULL,
  `stock` int(2) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idHerramienta`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `herramientas`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE IF NOT EXISTS `prestamos` (
  `idPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `idEmpleado` int(11) DEFAULT NULL,
  `idBodeguero` int(11) DEFAULT NULL,
  `estadoPrestamo` int(2) DEFAULT NULL,
  `fechaPrestamo` date DEFAULT NULL,
  PRIMARY KEY (`idPrestamo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `prestamos`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcar la base de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `usuario`, `password`, `tipo`, `fecha`) VALUES
(1, 'jc', 'jc', NULL, NULL),
(2, 'luis', '502ff82f7f1f8218dd41201fe4353687', NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
