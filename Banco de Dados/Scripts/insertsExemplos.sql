-- PAIS

INSERT INTO public.pais (nompais)
VALUES 
    ('Brasil');
	
-- Estado

INSERT INTO estado (siglaest, nomest, paisidpais) VALUES 
     ('SC', 'Santa Catarina', 1),
     ('RS', 'Rio Grande do Sul', 1),
     ('PR', 'Paraná', 1);


-- Cidade 

-- Inserindo cidades de Santa Catarina
INSERT INTO cidade (nomcid, estadoidest) VALUES 
    ('Princesa', 1),
    ('Maravilha', 1),
    ('São Miguel do Oeste', 1),
    ('Guaraciaba', 1),
    ('São José do Cedro', 1),
    ('Chapecó', 1),
    ('Iraceminha', 1);

-- Inserindo cidades do Rio Grande do Sul 
INSERT INTO cidade (nomcid, estadoidest) VALUES 
    ('Porto Alegre', 2),
    ('Caxias do Sul', 2),
    ('Pelotas', 2),
    ('Santa Maria', 2),
    ('Novo Hamburgo', 2);

-- Inserindo cidades do Paraná 
INSERT INTO cidade (nomcid, estadoidest) VALUES 
    ('Curitiba', 3),
    ('Londrina', 3),
    ('Maringá', 3),
    ('Cascavel', 3),
    ('Ponta Grossa', 3);
	
-- Bairro

 -- Inserindo Bairros
INSERT INTO bairro (nombai, estadoidest) VALUES
('Centro', 1),
('Centro', 2),
('Centro', 3);

-- Tipo ocorrência

INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Furto');
INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Roubo');
INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Acidente de trânsito');
INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Ameaça');
INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Estelionato');
INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Dano ao patrimônio');
INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Violência doméstica');
INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Desaparecimento de pessoa');
INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Posse de drogas');
INSERT INTO tipo_ocorrencia (desctipoco) VALUES ('Tráfico de drogas');

-- Tipo Envolvimento

INSERT INTO public.tipo_envolvimento (tipenv, desctipenv)
VALUES
    ('VÍTIMA', 'Pessoa que sofreu diretamente os efeitos da ocorrência'),
    ('TESTEMUNHA', 'Pessoa que presenciou os fatos relacionados à ocorrência'),
    ('COMUNICANTE', 'Pessoa que relata ou informa sobre a ocorrência às autoridades'),
    ('SUSPEITO', 'Pessoa sob investigação ou considerada possivelmente envolvida no delito, sem provas conclusivas no momento');
	
-- Tipo violência

INSERT INTO public.tipo_violencia (idtipvio, desctipvio)
VALUES
    ('FISICA', 'Agressão física, causando dano ao corpo da vítima'),
    ('PSICOLOGICA', 'Abusos emocionais ou ameaças que causam sofrimento mental'),
    ('SEXUAL', 'Violação ou tentativa de violação da liberdade sexual'),
    ('PATRIMONIAL', 'Dano ou subtração de bens materiais da vítima'),
    ('MORAL', 'Ofensas que atingem a honra ou dignidade da pessoa');
	
-- Usuário

-- Inserção de usuários (Cidadão e Policial)
INSERT INTO usuario (tipo_usu, status) 
VALUES 
  ('Cidadão', TRUE),  -- Cidadão ativo
  ('Policial', TRUE), -- Policial ativo
  ('Cidadão', FALSE), -- Cidadão inativo
  ('Cidadão', TRUE),  -- Cidadão ativo
  ('Cidadão', TRUE), -- Policial ativo
  ('Cidadão', FALSE), -- Cidadão inativo
  ('Cidadão', FALSE), -- Cidadão inativo
  ('Cidadão', TRUE), -- Cidadão inativo
  ('Cidadão', FALSE), -- Cidadão inativo
  ('Cidadão',TRUE); -- Cidadão inativo
  
-- Departamento Policial


INSERT INTO departamento_policial (
    disdep, emaildep, telatedep, logrdp, numdp, bairroidbai, cidadeidcid
) 
VALUES 
    ('Departamento de Polícia de São Miguel', 'saomiguel@policia.com', 12345678901234, 'Rua A, 123', 101, 1, 1),  -- Exemplo para bairro com ID 1 e cidade com ID 1
    ('Departamento de Polícia de Chapecó', 'chapeco@policia.com', 23456789012345, 'Avenida B, 456', 102, 2, 2);  -- Exemplo para bairro com ID 2 e cidade com ID 2
	
	
-- Pessoa

-- Inserindo pessoas
INSERT INTO public.pessoa 
(nompes, estcivpes, datnaspes, numtelpes, emailpes, genpes, usuarioidusu, Fotpes)
VALUES 
    ('João Silva', 'Solteiro', '1990-05-15', '1234567890', 'joao.silva@example.com', 'Masculino', 1, NULL),
    ('Maria Oliveira', 'Casada', '1985-11-20', '9876543210', 'maria.oliveira@example.com', 'Feminino', 2, NULL),
    ('Carlos Souza', 'Divorciado', '1980-02-10', '1122334455', 'carlos.souza@example.com', 'Masculino', 3, NULL),
		('João Zangali', 'Solteiro', '1990-05-15', '11987654321', 'joao.zangali@email.com', 'Masculino', 4, NULL),
	  ('Maria Lima', 'Casada', '1985-07-20', '21987654321', 'maria.oliveira@email.com', 'Feminino', 5, NULL),
	  ('Carlos Pereira', 'Divorciado', '1978-12-03', '31987654321', 'carlos.pereira@email.com', 'Masculino', 6, NULL),
	  ('Ana Souza', 'Viúva', '1992-09-14', '41987654321', 'ana.souza@email.com', 'Feminino', 7, NULL),
	  ('Bruno Rocha', 'Solteiro', '1988-03-11', '51987654321', 'bruno.rocha@email.com', 'Masculino', 8, NULL),
	  ('Fernanda Lima', 'Casada', '1995-11-22', '61987654321', 'fernanda.lima@email.com', 'Feminino', 9, NULL),
	  ('Paulo Mendes', 'Solteiro', '2000-01-09', '71987654321', 'paulo.mendes@email.com', 'Masculino', 10,NULL);
	  
-- Endereço pessoa

INSERT INTO public.endereco_pessoa 
(logrpes, numendpes, comendpes, bairroidbai, cidadeidcid, pessoaidpes)
VALUES 
    ('Rua das Palmeiras', 123, 'Apartamento 101', 1, 1, 1),  -- João Silva
    ('Avenida Brasil', 456, 'Casa 202', 2, 2, 2),              -- Maria Oliveira
    ('Rua das Flores', 789, 'Comércio 303', 3, 3, 3); 


-- Funcionario

-- Inserção de funcionários
INSERT INTO funcionario (pessoaidpes, carfun, departamento_policiaiddep) 
VALUES 
  (2, 'Investigador', 1)  -- Funcionário 1 (Policial)
  
-- Ocorrencia

INSERT INTO ocorrencia 
(descoco, datoco, lococo, staoco, cidadeidcid, funcionariopessoaidpes, tipo_violenciaidtipooco, tipo_ocorrenciaidtipoco, validaoco)
VALUES 
('Furto de celular em um restaurante local', '2024-11-19 08:30:00', 'Restaurante da Praça, Centro', 'Em andamento', 1, 2, 'PATRIMONIAL', 1, TRUE),
('Agressão física entre vizinhos', '2024-11-19 10:15:00', 'Rua dos Jasmins, Bairro Feliz', 'Resolvido', 2, 2, 'FISICA', 2, TRUE);

-- Pessoa envolvida

INSERT INTO pessoa_envolvida (nompesenv, descpesenv, cpfenv, telenv, datnasenv, emailenv, ocorrenciaidoco, tipo_envolvimentotipenv)
VALUES
('Carlos Silva', 'Vítima de roubo no centro da cidade', '12345678901', 11987654321, '1985-04-15', 'carlos.silva@email.com', 3, 'VÍTIMA'),
('Ana Pereira', 'Testemunha do acidente de trânsito', '98765432100', 11976543210, '1992-07-30', 'ana.pereira@email.com', 4, 'TESTEMUNHA');


-- Inserção de acesso para cidadãos

INSERT INTO acesso_cidadao (usuarioidusu, cpfusu, senusu) 
VALUES 
	(7, '12345678900', 'senha_cidadao_1'),  
  (9, '98765432100', 'senha_cidadao_2'),
  (10, '98765432101', 'senha_cidadao_3'),  
  (11, '98765432102', 'senha_cidadao_4'),  
  (12, '98765432103', 'senha_cidadao_5'),  
  (13, '98765432104', 'senha_cidadao_6'),  
  (14, '98765432105', 'senha_cidadao_7'),  
  (15, '98765432106', 'senha_cidadao_8'),  
  (16, '98765432107', 'senha_cidadao_9');  
	   
-- Inserção de acesso para policiais

INSERT INTO acesso_policial (usuarioidusu, matfun, senhafun) 
VALUES 
  (8, 'POL1234', 'senha_policial_1');  -- Acesso do policial 1