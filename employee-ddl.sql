SELECT * FROM sakila.employee;CREATE TABLE `employee` (
  `id` int(10) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `photo` longblob,
  `salary` int(10) DEFAULT NULL,
  `working_history` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
