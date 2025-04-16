-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 19, 2016 at 02:50 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.5.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `autopecas`
--

-- --------------------------------------------------------

--
-- Table structure for table `agenda`
--

CREATE TABLE `agenda` (
  `codigoAgendada` int(11) NOT NULL,
  `horaServico` time NOT NULL,
  `dataServico` date NOT NULL,
  `dataAgendada` date NOT NULL,
  `horaAgendada` time NOT NULL,
  `fkCarro` varchar(10) NOT NULL,
  `fkCliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `agenda`
--

INSERT INTO `agenda` (`codigoAgendada`, `horaServico`, `dataServico`, `dataAgendada`, `horaAgendada`, `fkCarro`, `fkCliente`) VALUES
(1, '13:00:00', '2016-07-25', '2016-07-24', '15:05:23', 'AAA-1234', 2),
(2, '15:30:00', '2016-08-19', '2016-08-18', '09:14:03', 'BBB-1234', 2),
(3, '15:30:00', '2016-08-18', '2016-08-18', '17:05:06', 'CCC-1234', 3);

-- --------------------------------------------------------

--
-- Table structure for table `carro`
--

CREATE TABLE `carro` (
  `placa` varchar(10) NOT NULL,
  `fkCliente` int(11) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `carro`
--

INSERT INTO `carro` (`placa`, `fkCliente`, `marca`, `nome`, `modelo`) VALUES
('AAA-1234', 1, 'Chevrolet', 'Fiat', '2015'),
('BBB-1234', 2, 'Volkswagen', 'Gol', '2015'),
('CCC-1234', 3, 'Volkswagen', 'Gol', '2015');

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
--

CREATE TABLE `cliente` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sexo` enum('M','F') DEFAULT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `rg` varchar(15) DEFAULT NULL,
  `cnpj` varchar(25) DEFAULT NULL,
  `telefone` varchar(20) NOT NULL,
  `celular` varchar(20) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `cep` varchar(50) NOT NULL,
  `pessoa` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`codigo`, `nome`, `email`, `sexo`, `cpf`, `rg`, `cnpj`, `telefone`, `celular`, `endereco`, `cidade`, `cep`, `pessoa`) VALUES
(1, 'Rogeria Pereira da Silva', 'rogeriaPepe@gmail.com', 'F', '878.789.798-79', '978798789', '  .   .   /    -  ', '(78) 9789-7987', '(89) 78978-9798', 'Rua Lascada da Pedra', '12.321-456', '78.798-798', 'Física'),
(2, 'Peças Areosvaldo', 'aero@gmail.com', 'M', '   .   .   -  ', '', '87.987.897/8979-87', '(78) 9879-8789', '(89) 78979-8798', 'Rua Osvalldo Cruz ', '89.798-798', '87.987-987', 'Jurídica'),
(3, 'Ricardo Lima Amaral', 'Ricardolima@hotmail.com', 'M', '   .   .   -  ', '', '09.922.032/0202-02', '(61) 9208-6666', '(61) 99299-9999', 'Jesse Freire ', 'Brasilia-DF', '09.000-000', 'Jurídica');

-- --------------------------------------------------------

--
-- Table structure for table `fornecedores`
--

CREATE TABLE `fornecedores` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `cnpj` varchar(18) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `estado` varchar(2) NOT NULL,
  `cep` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fornecedores`
--

INSERT INTO `fornecedores` (`codigo`, `nome`, `telefone`, `endereco`, `cnpj`, `cidade`, `estado`, `cep`) VALUES
(1, 'etsetst', '(12) 1212-1212', 'testsetsetse', '12.133.123/1234-12', 'tsetsetset', 'DF', '12.123-456'),
(2, 'testestets', '(98) 7987-8978', 'esetstestetts', '87.987.897/8979-87', 'esresrerser', 'MS', '87.987-987'),
(3, 'Peças Elite Car', '(09) 9999-9999', 'AVenida COmercial', '88.989.989/9898-89', 'Brasilia', 'DF', '87.878-787');

-- --------------------------------------------------------

--
-- Table structure for table `funcionario`
--

CREATE TABLE `funcionario` (
  `matricula` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  `sexo` enum('M','F') NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `celular` varchar(15) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `rg` varchar(15) NOT NULL,
  `cep` varchar(15) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `bairro` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `dataAdmissao` date NOT NULL,
  `carteiraTrabalho` varchar(20) NOT NULL,
  `cargo` varchar(25) NOT NULL,
  `salario` decimal(18,2) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `senha` varchar(64) NOT NULL,
  `perfil` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `funcionario`
--

INSERT INTO `funcionario` (`matricula`, `nome`, `dataNascimento`, `sexo`, `telefone`, `celular`, `cpf`, `rg`, `cep`, `endereco`, `bairro`, `cidade`, `dataAdmissao`, `carteiraTrabalho`, `cargo`, `salario`, `usuario`, `senha`, `perfil`) VALUES
(1, 'Jonas Perira', '1990-02-20', 'M', '(12) 1212-1212', '(12) 11212-1212', '123.123.123-12', '12345666', '12.123-123', 'Qnm 12 casa 17', 'Taguatinga', 'Brasilia-DF', '2016-09-20', '1234567', 'Mecânico', '2500.00', 'Nicolai', '123', 1),
(2, 'etstestet', '2016-02-20', 'M', '(78) 9789-7897', '(98) 78978-9789', '879.879.879-87', '89879', '98.789-798', 'testetste', 'etsteste', 'testest', '2016-08-18', '897897', 'Mecânico', '1500.00', 'mota', '123456', 2);

-- --------------------------------------------------------

--
-- Table structure for table `listaservico`
--

CREATE TABLE `listaservico` (
  `idListaServico` int(11) NOT NULL,
  `nomeServico` varchar(100) NOT NULL,
  `valor` decimal(18,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `listaservico`
--

INSERT INTO `listaservico` (`idListaServico`, `nomeServico`, `valor`) VALUES
(1, 'Alinhamento', '70.00'),
(2, 'Amortecedor', '54.00'),
(3, 'Ar-Condicionado', '80.00'),
(4, 'Arrefecimento', '30.00'),
(5, 'Baterias', '370.00'),
(6, 'Balanceamento', '80.00'),
(7, 'Cabos ', '10.00'),
(8, 'Cambagem', '90.00'),
(9, 'Oleo Freio', '100.00'),
(10, 'Direcao Hidraulica', '1400.00'),
(11, 'Extintor', '90.00'),
(12, 'Filtros', '15.00'),
(13, 'Oleo Motor', '100.00'),
(14, 'Ignição', '12.00'),
(15, 'Injeção Eletrônica', '280.00'),
(16, 'Vela', '30.00'),
(17, 'Pastilha de Freio', '230.00'),
(18, 'Suspensão', '300.00'),
(19, 'Troca de Óleo', '55.00'),
(20, 'Reparo Vidro Ele.', '60.00');

-- --------------------------------------------------------

--
-- Table structure for table `listaservico_has_servico`
--

CREATE TABLE `listaservico_has_servico` (
  `fkListaServico` int(11) NOT NULL,
  `fkOs` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `listaservico_has_servico`
--

INSERT INTO `listaservico_has_servico` (`fkListaServico`, `fkOs`) VALUES
(1, 1),
(1, 1),
(1, 2),
(15, 2),
(1, 3),
(4, 3),
(6, 3),
(15, 3);

-- --------------------------------------------------------

--
-- Table structure for table `notafiscal`
--

CREATE TABLE `notafiscal` (
  `codigo` int(11) NOT NULL,
  `nomeCliente` varchar(255) NOT NULL,
  `valor` decimal(18,2) NOT NULL,
  `data` date NOT NULL,
  `vendedor` varchar(255) NOT NULL,
  `tipoPgto` varchar(255) NOT NULL,
  `has_OS` varchar(3) NOT NULL,
  `cliente_codigo` int(11) NOT NULL,
  `OS_codigo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `notafiscal`
--

INSERT INTO `notafiscal` (`codigo`, `nomeCliente`, `valor`, `data`, `vendedor`, `tipoPgto`, `has_OS`, `cliente_codigo`, `OS_codigo`) VALUES
(1, 'Rogeria', '550.00', '2016-08-18', 'Jonas Pereira', 'Dinherio', 'Sim', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `os`
--

CREATE TABLE `os` (
  `codigo` int(11) NOT NULL,
  `dataEntrega` date NOT NULL,
  `valorTotal` decimal(18,2) NOT NULL,
  `horaEntrega` time NOT NULL,
  `fkAgenda` int(11) NOT NULL,
  `funcionario_matricula` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `os`
--

INSERT INTO `os` (`codigo`, `dataEntrega`, `valorTotal`, `horaEntrega`, `fkAgenda`, `funcionario_matricula`) VALUES
(1, '2016-07-26', '550.00', '13:00:00', 1, 1),
(2, '2016-08-19', '550.00', '09:14:27', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `produto`
--

CREATE TABLE `produto` (
  `codigo` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `carrosCompativeis` varchar(255) NOT NULL,
  `fornecedor` varchar(255) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `quantidade` varchar(10) NOT NULL,
  `preco` decimal(18,2) NOT NULL,
  `imagem` varchar(500) NOT NULL,
  `fornecedores_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produto`
--

INSERT INTO `produto` (`codigo`, `nome`, `carrosCompativeis`, `fornecedor`, `marca`, `quantidade`, `preco`, `imagem`, `fornecedores_codigo`) VALUES
(1, 'etsestetst', 'estetset', 'testestets', 'etstestest', '500', '500.00', 'etsetstest', 1),
(2, 'testetsetste', 'Gol', 'COMANDO Auto Peças', 'Volkswagen', '50', '1500.00', 'C:\\Users\\nicol\\Desktop\\Musicas\\aguia-com-asas-de-desenho_91-2147487491.jpg', 2),
(3, 'Pastilhas de Freio', 'Up', 'DKP Distribuidora Auto Peças', 'Chevrolet', '150', '550.00', 'C:\\Users\\nicol\\Desktop\\Produtos\\PastilhaFreios.jpg', 3);

-- --------------------------------------------------------

--
-- Table structure for table `produto_has_notafiscal`
--

CREATE TABLE `produto_has_notafiscal` (
  `produto_codigo` int(11) NOT NULL,
  `notafiscal_codigo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`codigoAgendada`),
  ADD KEY `fkCarro` (`fkCarro`),
  ADD KEY `fkCliente` (`fkCliente`);

--
-- Indexes for table `carro`
--
ALTER TABLE `carro`
  ADD PRIMARY KEY (`placa`),
  ADD KEY `carro_ibfk_1` (`fkCliente`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `fornecedores`
--
ALTER TABLE `fornecedores`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `funcionario`
--
ALTER TABLE `funcionario`
  ADD PRIMARY KEY (`matricula`);

--
-- Indexes for table `listaservico`
--
ALTER TABLE `listaservico`
  ADD PRIMARY KEY (`idListaServico`);

--
-- Indexes for table `listaservico_has_servico`
--
ALTER TABLE `listaservico_has_servico`
  ADD KEY `fkListaServico` (`fkListaServico`),
  ADD KEY `fkOs` (`fkOs`);

--
-- Indexes for table `notafiscal`
--
ALTER TABLE `notafiscal`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `cliente_codigo` (`cliente_codigo`),
  ADD KEY `OS_codigo` (`OS_codigo`);

--
-- Indexes for table `os`
--
ALTER TABLE `os`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `fkAgenda` (`fkAgenda`),
  ADD KEY `funcionario_matricula` (`funcionario_matricula`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `fornecedores_codigo` (`fornecedores_codigo`);

--
-- Indexes for table `produto_has_notafiscal`
--
ALTER TABLE `produto_has_notafiscal`
  ADD PRIMARY KEY (`produto_codigo`),
  ADD KEY `notafiscal_codigo` (`notafiscal_codigo`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `agenda_ibfk_1` FOREIGN KEY (`fkCarro`) REFERENCES `carro` (`placa`),
  ADD CONSTRAINT `agenda_ibfk_2` FOREIGN KEY (`fkCliente`) REFERENCES `cliente` (`codigo`);

--
-- Constraints for table `carro`
--
ALTER TABLE `carro`
  ADD CONSTRAINT `carro_ibfk_1` FOREIGN KEY (`fkCliente`) REFERENCES `cliente` (`codigo`);

--
-- Constraints for table `notafiscal`
--
ALTER TABLE `notafiscal`
  ADD CONSTRAINT `notafiscal_ibfk_1` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`),
  ADD CONSTRAINT `notafiscal_ibfk_2` FOREIGN KEY (`OS_codigo`) REFERENCES `os` (`codigo`);

--
-- Constraints for table `os`
--
ALTER TABLE `os`
  ADD CONSTRAINT `os_ibfk_1` FOREIGN KEY (`fkAgenda`) REFERENCES `agenda` (`codigoAgendada`),
  ADD CONSTRAINT `os_ibfk_2` FOREIGN KEY (`funcionario_matricula`) REFERENCES `funcionario` (`matricula`);

--
-- Constraints for table `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`fornecedores_codigo`) REFERENCES `fornecedores` (`codigo`);

--
-- Constraints for table `produto_has_notafiscal`
--
ALTER TABLE `produto_has_notafiscal`
  ADD CONSTRAINT `produto_has_notafiscal_ibfk_1` FOREIGN KEY (`produto_codigo`) REFERENCES `produto` (`codigo`),
  ADD CONSTRAINT `produto_has_notafiscal_ibfk_2` FOREIGN KEY (`notafiscal_codigo`) REFERENCES `notafiscal` (`codigo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
