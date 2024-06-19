-- Inserts na tabela estado
INSERT INTO estado (cod_estado, cod_ibge_estado, nom_estado) VALUES
(1, 42, 'Santa Catarina'),
(2, 43, 'Rio Grande do Sul'),
(3, 44, 'Paraná');

-- Inserts na tabela cidade
INSERT INTO cidade (cod_cidade, uf_cidade, cod_ibge_cidade, nom_cidade, cod_estado) VALUES
(1, 'SC', 4216909, 'São Miguel do Oeste', 1),
(2, 'SC', 4206504, 'Guaraciaba', 1),
(3, 'SC', 4204202, 'Descanso', 1),
(4, 'SC', 4210609, 'Maravilha', 1),
(5, 'SC', 4208302, 'Itapiranga', 1);

--Inserts na tabela bairro
INSERT INTO bairro (cod_bairro, nom_bairro, cod_cidade) VALUES
(1, 'Centro', 1),  -- Bairro Centro em São Miguel do Oeste
(2, 'Centro', 2),  -- Bairro Centro em Guaraciaba
(3, 'Centro', 3),  -- Bairro Centro em Descanso
(4, 'Centro', 4),  -- Bairro Centro em Maravilha
(5, 'Centro', 5);  -- Bairro Centro em Itapiranga

-- Inserts na tabela departamento_policia
INSERT INTO departamento_policia (cod_dp, distrito_dp, numero_enderecodp, codbairrodp) VALUES
(1, 1, 101, 1),  -- Departamento de polícia em São Miguel do Oeste
(2, 2, 102, 2),  -- Departamento de polícia em Guaraciaba
(3, 3, 103, 3),  -- Departamento de polícia em Descanso
(4, 4, 104, 4),  -- Departamento de polícia em Maravilha
(5, 5, 105, 5);  -- Departamento de polícia em Itapiranga

--Inserts de pessoas que são funcionarios
INSERT into pessoa (cod_pessoa, generopessoa, idadepessoa, nom_pessoa, est_civil_pessoa, data_nasc_pessoa, cpf_pessoa, num_tele_pessoa, email_pessoa, numero_enderecop, codbairrop) VALUES
(1, 'F', 20, 'Ana Souza', 'Casada', '1985-03-25', 45678901234, 47987654321, 'ana.souza@example.com', 101, 1),
(2, 'M', 30, 'Bruno Lima', 'Solteiro', '1992-07-15', 56789012345, 47987654322, 'bruno.lima@example.com', 102, 2),
(3, 'F', 40, 'Carla Mota', 'Divorciada', '1978-09-30', 67890123456, 47987654323, 'carla.mota@example.com', 103, 3),
(4, 'M', 50, 'Daniel Rocha', 'Viúvo', '1965-12-20', 78901234567, 47987654324, 'daniel.rocha@example.com', 104, 4),
(5, 'F', 50, 'Elaine Costa', 'Casada', '1990-04-10', 89012345678, 47987654325, 'elaine.costa@example.com', 105, 5);

-- inserts dos funcionarios
INSERT INTO funcionario (cod_fun, cargo_fun, cod_dp, dp_atuacao_fun, cod_pessoa) VALUES
(1, 'Delegado', 1, 1, 1),
(2, 'Investigador', 2, 2, 2),
(3, 'Escrivão', 3, 3, 3),
(4, 'Perito Criminal', 4, 4, 4),
(5, 'Agente', 5, 5, 5);

-- insert de pessoas que não são funcionarios
INSERT INTO pessoa (cod_pessoa, generopessoa, idadepessoa, nom_pessoa, est_civil_pessoa, data_nasc_pessoa, cpf_pessoa, num_tele_pessoa, email_pessoa, numero_enderecop, codbairrop) VALUES
(6, 'M', 28, 'Felipe Silva', 'Solteiro', '1995-01-14', 90123456789, 47987654326, 'felipe.silva@example.com', 106, 1),
(7, 'F', 29, 'Gabriela Mendes', 'Casada', '1994-05-25', 91234567890, 47987654327, 'gabriela.mendes@example.com', 107, 2),
(8, 'M', 40, 'Henrique Almeida', 'Divorciado', '1983-11-22', 92345678901, 47987654328, 'henrique.almeida@example.com', 108, 3),
(9, 'F', 52, 'Isabela Ferreira', 'Viúva', '1972-03-19', 93456789012, 47987654329, 'isabela.ferreira@example.com', 109, 4),
(10, 'M', 25, 'João Oliveira', 'Casado', '1999-07-30', 94567890123, 47987654330, 'joao.oliveira@example.com', 110, 5);

-- Inserts na tabela tipo_ocorrencia
INSERT INTO tipo_ocorrencia (cod_tipo_ocorrencia, desc_ocorrencia) VALUES
(1, 'Roubo'),
(2, 'Furto'),
(3, 'Homicídio'),
(4, 'Feminicídio'),
(5, 'Estelionato');

INSERT INTO bo (cod_bo, data_bo, local_bo, sit_bo, cod_cidadebo, cod_fun) VALUES
(1, '2023-01-15', 'Rua A, 123', TRUE, 1, 1),
(2, '2023-02-20', 'Rua B, 456', FALSE, 2, 2),
(3, '2023-03-25', 'Rua C, 789', TRUE, 3, 3),
(4, '2023-04-10', 'Rua D, 101', TRUE, 4, 4),
(5, '2023-05-05', 'Rua E, 102', FALSE, 5, 5),
(6, '2023-06-15', 'Rua F, 103', TRUE, 1, 1),
(7, '2023-07-20', 'Rua G, 104', FALSE, 2, 2),
(8, '2023-08-25', 'Rua H, 105', TRUE, 3, 3),
(9, '2023-09-10', 'Rua I, 106', TRUE, 4, 4),
(10, '2023-10-05', 'Rua J, 107', FALSE, 5, 5),
(11, '2023-11-15', 'Rua K, 108', TRUE, 1, 1),
(12, '2023-12-20', 'Rua L, 109', FALSE, 2, 2),
(13, '2024-01-15', 'Rua M, 123', TRUE, 3, 3),
(14, '2024-02-20', 'Rua N, 456', FALSE, 4, 4),
(15, '2024-03-25', 'Rua O, 789', TRUE, 5, 5),
(16, '2024-04-10', 'Rua P, 101', TRUE, 1, 1),
(17, '2024-05-05', 'Rua Q, 102', FALSE, 2, 2),
(18, '2024-06-15', 'Rua R, 103', TRUE, 3, 3),
(19, '2024-07-20', 'Rua S, 104', FALSE, 4, 4),
(20, '2024-08-25', 'Rua T, 105', TRUE, 5, 5),
(21, '2024-09-10', 'Rua U, 106', TRUE, 1, 1),
(22, '2024-10-05', 'Rua V, 107', FALSE, 2, 2),
(23, '2024-11-15', 'Rua W, 108', TRUE, 3, 3),
(24, '2024-12-20', 'Rua X, 109', FALSE, 4, 4);

INSERT INTO ocorrencia (cod_ocorrencia, data_ocorrencia, local_ocorrencia, num_envolvidos, cod_cidadeo, cod_tipo_ocorrencia, cod_bo) VALUES
(1, '2023-01-15', 'Rua A, 123', 3, 1, 1, 1),
(2, '2023-02-20', 'Rua B, 456', 2, 2, 2, 2),
(3, '2023-03-25', 'Rua C, 789', 4, 3, 3, 3),
(4, '2023-04-10', 'Rua D, 101', 1, 4, 1, 4),
(5, '2023-05-05', 'Rua E, 102', 5, 5, 2, 5),
(6, '2023-06-15', 'Rua F, 103', 2, 1, 3, 6),
(7, '2023-07-20', 'Rua G, 104', 3, 2, 1, 7),
(8, '2023-08-25', 'Rua H, 105', 4, 3, 2, 8),
(9, '2023-09-10', 'Rua I, 106', 1, 4, 3, 9),
(10, '2023-10-05', 'Rua J, 107', 5, 5, 1, 10),
(11, '2023-11-15', 'Rua K, 108', 2, 1, 2, 11),
(12, '2023-12-20', 'Rua L, 109', 3, 2, 3, 12),
(13, '2024-01-15', 'Rua M, 123', 2, 3, 1, 13),
(14, '2024-02-20', 'Rua N, 456', 3, 4, 2, 14),
(15, '2024-03-25', 'Rua O, 789', 4, 5, 3, 15),
(16, '2024-04-10', 'Rua P, 101', 1, 1, 1, 16),
(17, '2024-05-05', 'Rua Q, 102', 5, 2, 2, 17),
(18, '2024-06-15', 'Rua R, 103', 2, 3, 3, 18),
(19, '2024-07-20', 'Rua S, 104', 3, 4, 1, 19),
(20, '2024-08-25', 'Rua T, 105', 4, 5, 2, 20),
(21, '2024-09-10', 'Rua U, 106', 1, 1, 3, 21),
(22, '2024-10-05', 'Rua V, 107', 5, 2, 1, 22),
(23, '2024-11-15', 'Rua W, 108', 2, 3, 2, 23),
(24, '2024-12-20', 'Rua X, 109', 3, 4, 3, 24);