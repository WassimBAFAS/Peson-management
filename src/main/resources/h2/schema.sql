CREATE TABLE IF NOT EXISTS employee (
  id bigint NOT NULL AUTO_INCREMENT,
  Nom varchar(25) NOT NULL,
  Prenom varchar(25) NOT NULL,
  email varchar(25) NOT NULL,
  DateNaissance date DEFAULT NULL,
  PRIMARY KEY (id)
);