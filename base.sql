
CREATE TABLE IF NOT EXISTS `clientes` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `Usuario` varchar(50) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `Tipo` varchar(20) DEFAULT NULL,
  `Estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idCliente`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


INSERT INTO `clientes` (`Usuario`, `Password`, `Tipo`) VALUES
('admin', 'admin123', 'Administrador'),
('Victor', 'Victor123', 'Cliente'),
('Facundo', 'Facundo123', 'Cliente'),
('Juan', 'Juan123', 'Cliente'),
('Walter', 'Walter123', 'Cliente'),
('Nazareno', 'Nazareno123', 'Cliente'),
('Luciana', 'Luciana123', 'Cliente');


CREATE TABLE IF NOT EXISTS `libros` (
  `codLibro` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Genero` varchar(50) DEFAULT NULL,
  `Precio` double(8,2) NOT NULL,
  `Year` int(11) DEFAULT NULL,
  `Autor` varchar(50) DEFAULT NULL,
  `Estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codLibro`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


INSERT INTO `libros` (`Nombre`, `Genero`, `Precio`, `Year`, `Autor`) VALUES
('Lo Pasado Pensado', 'Historia', 70.00, 2005, 'Felipe Pigna'),
('Los Mitos de la Historia Argentina', 'Historia', 85.00, 2008, 'Felipe Pigna'),
('Caudillos de la Argentina', 'Historia', 65.00, 2002, 'Fernando Sabsay'),
('Malvinas la Trama Secreta', 'Historia', 68.00, 1992, 'Oscar Cardoso'),
('Don Quijote de la Mancha', 'Aventura', 42.00, 1605, 'Manuel Cervantes'),
('Romeo y Julieta', 'Tragedia', 32.00, 1637, 'William Shakespeare'),
('El Retrato de Dorian Gray', 'Novela', 38.00, 1890, 'Oscar Wilde'),
('Los Tres Mosqueteros', 'Novela', 47.00, 1844, 'Alexander Dumas'),
('Soy Roca', 'Historia', 65.00, 2005, 'Felix Luna'),
('Cartas Marcadas', 'Novela', 109.00, 2012, 'Alejandro Dolina'),
('Bar del Infiero', 'Novela', 59.00, 2009, 'Alejandro Dolina'),
('Caballo de Fuego', 'Novela', 139.00, 2011, 'Florencia Bonelli'),
('Evita, Jirones de su Vida', 'Biografias', 99.00, 2012, 'Felipe Pigna'),
('Encuentros', 'Autoayuda', 99.00, 2012, 'Gabriel Rolon'),
('El Juego Manda', 'Rugby', 89.00, 2012, 'Agustin Pichot'),
('La Encrucidaja Argentina', 'Sociologia', 99.00, 2012, 'Carlos Gabetta'),
('Relato Oculto', 'Periodismo', 89.00, 2012, 'Leonardo Haberkorn'),
('Argentuits', 'Politica', 93.00, 2012, 'Diego Rojas'),
('Matar a Borges', 'Novela', 89.00, 2012, 'Francisco Cappelotti'),
('2012, Las Profecias Definitivas', 'Adivinacion', 99.00, 2012, 'Luis Carlos Schweitzer');


CREATE TABLE IF NOT EXISTS `lineaventa` (
  `codVenta` int(11) NOT NULL,
  `codLibro` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `valorLibro` double(8,2) NOT NULL,
  `subTotal` double(8,2) NOT NULL,
  PRIMARY KEY (`codVenta`,`codLibro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `lineaventa` (`codVenta`, `codLibro`, `Cantidad`, `valorLibro`, `subTotal`) VALUES
(1, 1, 2, 70.00, 140.00),
(1, 2, 1, 85.00, 85.00),
(1, 4, 1, 68.00, 68.00),
(2, 1, 1, 70.00, 70.00),
(2, 2, 1, 85.00, 85.00),
(2, 10, 1, 109.00, 109.00),
(2, 19, 1, 89.00, 89.00);


CREATE TABLE IF NOT EXISTS `ventas` (
  `codVenta` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente` int(11) NOT NULL,
  `Total` double(8,2) DEFAULT NULL,
  `Fecha` varchar(50) NOT NULL,
  PRIMARY KEY (`codVenta`,`idCliente`,`Fecha`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


INSERT INTO `ventas` (`idCliente`, `Total`, `Fecha`) VALUES
(6, 293.00, '2012-10-08 21:11:59'),
(6, 353.00, '2012-10-08 21:42:57');
