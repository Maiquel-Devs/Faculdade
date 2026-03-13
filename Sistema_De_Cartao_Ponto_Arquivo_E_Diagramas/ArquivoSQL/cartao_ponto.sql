CREATE DATABASE cartao_ponto CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE cartao_ponto;

/*Tabela Departamento*/
CREATE TABLE departamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

/*Tabela Funcionario*/
CREATE TABLE funcionario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    nome_completo VARCHAR(150) NOT NULL,
    id_departamento INT NOT NULL,
    FOREIGN KEY (id_departamento) REFERENCES departamento(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

/*Tabela Registro de Ponto*/
CREATE TABLE registro_ponto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_funcionario INT NOT NULL,
    data_hora DATETIME NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Inserindo um departamento
INSERT INTO departamento (nome) VALUES ('Gestão de Pessoas');

/*James Gosling*/

INSERT INTO funcionario (matricula, nome_completo, id_departamento) VALUES ('12345', 'James Gosling', 1);

INSERT INTO registro_ponto (id_funcionario, data_hora) VALUES 
(1, '2026-03-10 08:02:00'),
(1, '2026-03-10 12:01:00'),
(1, '2026-03-10 13:05:00'),
(1, '2026-03-10 17:58:00');

/*Linus Torvalds*/

INSERT INTO funcionario (matricula, nome_completo, id_departamento) 
VALUES ('22222', 'Linus Torvalds', 1);

INSERT INTO registro_ponto (id_funcionario, data_hora) VALUES 
(2, '2026-03-10 09:00:00'), 
(2, '2026-03-10 12:30:00'), 
(2, '2026-03-10 14:00:00'), 
(2, '2026-03-10 18:30:00');

/*Barbara Liskov*/

INSERT INTO funcionario (id, matricula, nome_completo, id_departamento) 
VALUES (3, '33333', 'Barbara Liskov', 1);

INSERT INTO registro_ponto (id_funcionario, data_hora) VALUES 
(3, '2026-03-10 08:00:00'), 
(3, '2026-03-10 12:00:00'),
(3, '2026-03-10 13:30:00'), 
(3, '2026-03-10 17:30:00'); 

/*Visualizar tabelas*/
SELECT * FROM departamento;

SELECT * FROM funcionario;

SELECT * FROM registro_ponto;   /*James , Departamento Gestão de Pessoas*/
