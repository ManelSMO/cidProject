
-- Inserção de dados na tabela departamento_policia
INSERT INTO departamento_policia (cod_dp, distrito_dp) VALUES
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9), (10, 10);

-- Inserção de dados na tabela estado
INSERT INTO estado (cod_estado, cod_ibge_estado, nom_estado) VALUES
(1, 11, 'São Paulo'), (2, 12, 'Rio de Janeiro'), (3, 13, 'Minas Gerais'), (4, 14, 'Espírito Santo'), (5, 15, 'Paraná'),
(6, 16, 'Santa Catarina'), (7, 17, 'Rio Grande do Sul'), (8, 18, 'Bahia'), (9, 19, 'Sergipe'), (10, 20, 'Alagoas');

-- Inserção de dados na tabela cidade
INSERT INTO cidade (cod_cidade, uf_cidade, cod_ibge_cidade, nom_cidade, cod_estado) VALUES
(1, 'SP', 3550308, 'São Paulo', 1), (2, 'RJ', 3304557, 'Rio de Janeiro', 2), (3, 'MG', 3106200, 'Belo Horizonte', 3),
(4, 'ES', 3205309, 'Vitória', 4), (5, 'PR', 4106902, 'Curitiba', 5), (6, 'SC', 4205407, 'Florianópolis', 6),
(7, 'RS', 4314902, 'Porto Alegre', 7), (8, 'BA', 2927408, 'Salvador', 8), (9, 'SE', 2800308, 'Aracaju', 9),
(10, 'AL', 2704302, 'Maceió', 10);

-- Inserção de dados na tabela tipo_ocorrencia
INSERT INTO tipo_ocorrencia (cod_tipo_ocorrencia, desc_ocorrencia) VALUES
(1, 'Furto'), (2, 'Roubo'), (3, 'Assalto'), (4, 'Homicídio'), (5, 'Tráfico de drogas'),
(6, 'Estelionato'), (7, 'Sequestro'), (8, 'Agressão'), (9, 'Vandalismo'), (10, 'Desaparecimento');

-- Inserção de dados na tabela pessoa
INSERT INTO pessoa (cod_pessoa, nom_pessoa, est_civil_pessoa, data_nasc_pessoa, cpf_pessoa, num_tele_pessoa, email_pessoa) VALUES
(1, 'Pedro Silva', 'Casado', '1980-01-01', 12345678901, 5511998765432, 'pedro.silva@example.com'),
(2, 'Maria Oliveira', 'Solteira', '1990-02-01', 12345678902, 5511998765433, 'maria.oliveira@example.com'),
(3, 'João Souza', 'Divorciado', '1975-03-03', 12345678903, 5511998765434, 'joao.souza@example.com'),
(4, 'Ana Costa', 'Casada', '1985-04-04', 12345678904, 5511998765435, 'ana.costa@example.com'),
(5, 'Carlos Pereira', 'Solteiro', '1992-05-05', 12345678905, 5511998765436, 'carlos.pereira@example.com'),
(6, 'Fernanda Lima', 'Casada', '1988-06-06', 12345678906, 5511998765437, 'fernanda.lima@example.com'),
(7, 'Rafael Alves', 'Solteiro', '1993-07-07', 12345678907, 5511998765438, 'rafael.alves@example.com'),
(8, 'Juliana Martins', 'Divorciada', '1982-08-08', 12345678908, 5511998765439, 'juliana.martins@example.com'),
(9, 'Ricardo Ferreira', 'Casado', '1978-09-09', 12345678909, 5511998765440, 'ricardo.ferreira@example.com'),
(10, 'Larissa Rodrigues', 'Solteira', '1995-10-10', 12345678910, 5511998765441, 'larissa.rodrigues@example.com');

-- Inserção de dados na tabela funcionario
INSERT INTO funcionario (cod_fun, cargo_fun, cod_dp, dp_atuacao_fun, cod_pessoa) VALUES
(1, 1, 1, 1, 1),
(2, 2, 2, 2, 2),
(3, 3, 3, 3, 3),
(4, 4, 4, 4, 4),
(5, 5, 5, 5, 5),
(6, 6, 6, 6, 6),
(7, 7, 7, 7, 7),
(8, 8, 8, 8, 8),
(9, 9, 9, 9, 9),
(10, 10, 10, 10, 10);

-- Inserção de dados na tabela bo
INSERT INTO bo (cod_bo, data_bo, local_bo, sit_bo, cod_fun) VALUES
(1, '2024-01-01', 'Local 1', TRUE, 1),
(2, '2024-02-01', 'Local 2', FALSE, 2),
(3, '2024-03-01', 'Local 3', TRUE, 3),
(4, '2024-04-01', 'Local 4', FALSE, 4),
(5, '2024-05-01', 'Local 5', TRUE, 5),
(6, '2024-06-01', 'Local 6', FALSE, 6),
(7, '2024-07-01', 'Local 7', TRUE, 7),
(8, '2024-08-01', 'Local 8', FALSE, 8),
(9, '2024-09-01', 'Local 9', TRUE, 9),
(10, '2024-10-01', 'Local 10', FALSE, 10);

-- Inserção de dados na tabela ocorrencia
INSERT INTO ocorrencia (cod_ocorrencia, data_ocorrencia, local_ocorrencia, num_envolvidos, cod_tipo_ocorrencia, cod_bo) VALUES
(1, '2024-01-01', 'Local 1', 2, 1, 1),
(2, '2024-02-01', 'Local 2', 3, 2, 2),
(3, '2024-03-01', 'Local 3', 1, 3, 3),
(4, '2024-04-01', 'Local 4', 4, 4, 4),
(5, '2024-05-01', 'Local 5', 2, 5, 5),
(6, '2024-06-01', 'Local 6', 3, 6, 6),
(7, '2024-07-01', 'Local 7', 1, 7, 7),
(8, '2024-08-01', 'Local 8', 4, 8, 8),
(9, '2024-09-01', 'Local 9', 2, 9, 9),
(10, '2024-10-01', 'Local 10', 3, 10, 10);

-- Inserção de dados na tabela endereco_pessoa
INSERT INTO endereco_pessoa (rua_endereco, bairro_endereco, comp_endereco, num_endereco, cod_pessoa, cod_cidade) VALUES
('Rua 1', 'Bairro 1', 'Comp 1', 101, 1, 1),
('Rua 2', 'Bairro 2', 'Comp 2', 102, 2, 2),
('Rua 3', 'Bairro 3', 'Comp 3', 103, 3, 3),
('Rua 4', 'Bairro 4', 'Comp 4', 104, 4, 4),
('Rua 5', 'Bairro 5', 'Comp 5', 105, 5, 5),
('Rua 6', 'Bairro 6', 'Comp 6', 106, 6, 6),
('Rua 7', 'Bairro 7', 'Comp 7', 107, 7, 7),
('Rua 8', 'Bairro 8', 'Comp 8', 108, 8, 8),
('Rua 9', 'Bairro 9', 'Comp 9', 109, 9, 9),
('Rua 10', 'Bairro 10', 'Comp 10', 110, 10, 10);

-- Inserção de dados na tabela endereco_dp
INSERT INTO endereco_dp (rua_endereco, bairro_endereco, comp_endereco, num_endereco, cod_dp, cod_cidade) VALUES
('Rua 11', 'Bairro 11', 'Comp 11', 111, 1, 1),
('Rua 12', 'Bairro 12', 'Comp 12', 112, 2, 2),
('Rua 13', 'Bairro 13', 'Comp 13', 113, 3, 3),
('Rua 14', 'Bairro 14', 'Comp 14', 114, 4, 4),
('Rua 15', 'Bairro 15', 'Comp 15', 115, 5, 5),
('Rua 16', 'Bairro 16', 'Comp 16', 116, 6, 6),
('Rua 17', 'Bairro 17', 'Comp 17', 117, 7, 7),
('Rua 18', 'Bairro 18', 'Comp 18', 118, 8, 8),
('Rua 19', 'Bairro 19', 'Comp 19', 119, 9, 9),
('Rua 20', 'Bairro 20', 'Comp 20', 120, 10, 10);