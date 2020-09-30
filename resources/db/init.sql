  CREATE TABLE `carsales`.`member` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

insert into member (id, name, email) values (1,  'Tom', 'tom@gmail.com');
insert into member (id, name, email) values (2,  'Bob', 'bob@gmail.com');