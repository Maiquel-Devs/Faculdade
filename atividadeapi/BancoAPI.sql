CREATE DATABASE BancoAPI;

USE BancoAPI;

show tables;

Select * From categoria;

Select * From filme;

Select * From categoria, filme;


DELETE FROM categoria
WHERE id = 1;

DELETE FROM filme
WHERE id = 1;

DELETE FROM filme
WHERE categoria_id = 1;
