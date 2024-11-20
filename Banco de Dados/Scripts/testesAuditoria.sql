 ---------Teste Usuario ------------------------
  INSERT INTO usuario (tipo_usu, status) 
VALUES 
  ('Cidadão', TRUE);  -- Cidadão ativo
  
  select * from usuario ;
 delete usuario where idusu = 12;

DELETE FROM usuario
WHERE idusu = 12;

-------------------- Teste Pessoa --------------------------------
-- Inserção de pessoas
INSERT INTO pessoa (nompes, estcivpes, datnaspes, numtelpes, emailpes, genpes, usuarioidusu) 
VALUES 
  ('João Silveira', 'Solteiro', '1990-05-15', '11987654322', 'joao.silva@email.com', 'Masculino', 15);


DELETE FROM pessoa 
WHERE idpes = 11;
-------------------    Teste Funcionario -------------------------
INSERT INTO funcionario (pessoaidpes, carfun, departamento_policiaiddep) 
VALUES 
  (12, 'Investigador', 1)
  
DELETE FROM funcionario  
WHERE pessoaidpes = 12;
-------------------------  Teste Ocorrecia ---------------------

   INSERT INTO ocorrencia 
(descoco, datoco, lococo, staoco, cidadeidcid, funcionariopessoaidpes, tipo_violenciaidtipooco, tipo_ocorrenciaidtipoco, validaoco)
VALUES 
('Furto de celular em um restaurante local', '2024-11-19 08:30:00', 'Restaurante da Praça, Centro', 'Em andamento', 1, 2, 'PATRIMONIAL', 1, TRUE);

delete from ocorrencia 
where idoco = 8;
------------------------- Select auditoria ----------------------------------

select * from auditoria a ;