CREATE DATABASE IF NOT EXISTS auditoria; use auditoria;

CREATE TABLE IF NOT EXISTS usuario (
  id_usu varchar(36) NOT NULL,
  rol_usu varchar(50) NOT NULL,
  nombres_usu varchar(50) NOT NULL,
  apellidos_usu varchar(50) NOT NULL,
  correo_usu varchar(50) NOT NULL,
  celular_usu varchar(10) NOT NULL,
  password_usu varchar(36) NOT NULL,
  PRIMARY KEY (id_usu),
  UNIQUE KEY id_usu (id_usu)
);
CREATE TABLE IF NOT EXISTS cuestionario (
  id_cuest int NOT NULL AUTO_INCREMENT,
  empresa_cuest varchar(50) NOT NULL,
  dominio_cuest varchar(50) NOT NULL,
  proceso_cuest varchar(50) NOT NULL,
  objetivo_cuest varchar(50) NOT NULL,
  codigo_cuest varchar(50) NOT NULL,
  PRIMARY KEY (id_cuest),
  UNIQUE KEY id_cuest (id_cuest)
);
CREATE TABLE IF NOT EXISTS pregunta (
  id_preg int NOT NULL AUTO_INCREMENT,
  descripcion_preg varchar(255) NOT NULL,
  id_cuest int NOT NULL,
  PRIMARY KEY (id_preg),
  UNIQUE KEY id_preg (id_preg),
  FOREIGN KEY (id_cuest) 
  REFERENCES cuestionario(id_cuest)
  ON DELETE CASCADE 
);
CREATE TABLE IF NOT EXISTS resultado (
  id_res int NOT NULL AUTO_INCREMENT,
  respuesta_res varchar(10) NOT NULL,
  id_preg int NOT NULL,
  id_usu varchar(36) NOT NULL,
  PRIMARY KEY (id_res),
  UNIQUE KEY id_res (id_res),
  FOREIGN KEY (id_preg) 
  REFERENCES pregunta(id_preg)
  ON DELETE CASCADE ,
  FOREIGN KEY (id_usu) 
  REFERENCES usuario(id_usu)
  ON DELETE CASCADE
);

INSERT INTO usuario
VALUES ('eava01', 'Auditor', 'Edwin', 'Vesga', 'eava01@hotmail.com', '3112957916', '123');
INSERT INTO usuario
VALUES ('inter01', 'Interventor', 'Juan', 'Rodriguez', 'juanro01@hotmail.com', '3113457916', '123');
INSERT INTO cuestionario (empresa_cuest,dominio_cuest,proceso_cuest,objetivo_cuest,codigo_cuest)
VALUES ('UIS', 'Manejo de informacion y elementos', 'Seguridad de Informacion', 'Seguridad de Informacion', 'Cuestionario1');
INSERT INTO pregunta (descripcion_preg,id_cuest)
VALUES ('Existen metodologias de respaldo de informacion?', '1');
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Se realizan respaldos de informacion periodicamente?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Existe un administrador de sistemas que controle las cuentas de
los usuarios?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Existe algun estandar para la creacion de contrasenas?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Las contrasenas cuentan con letras, numeros y simbolos?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Se obliga, cada cierto tiempo a cambiar la contrasena?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('La organizacion cuenta con un proceso para dar mantenimiento
preventivo al software?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('La organizacion cuenta con un proceso para dar mantenimiento
correctivo al software?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Se tienen software antivirus instalados en los equipos de
computo?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Cuentan con antivirus actualizado?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Se tienen instalados anti malware en los equipos de computo?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Cuenta con licencias de software?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Existe un proceso para mantener las licencias actualizadas?', 1);
INSERT INTO pregunta (descripcion_preg, id_cuest)
VALUES ('Se sanciona al integrante del departamento si instala software no
permitido?', 1);
