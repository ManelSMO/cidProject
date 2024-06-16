create database CID;


-- Criação da tabela estado
CREATE TABLE estado (
    cod_estado INT PRIMARY KEY,
    cod_ibge_estado INT NOT NULL,
    nom_estado VARCHAR(50)
);
COMMENT ON TABLE estado IS 'Tabela de estados';
COMMENT ON COLUMN estado.cod_estado IS 'Código do estado';
COMMENT ON COLUMN estado.cod_ibge_estado IS 'Código IBGE do estado';
COMMENT ON COLUMN estado.nom_estado IS 'Nome do estado';

-- Criação da tabela cidade
CREATE TABLE cidade (
    cod_cidade INT PRIMARY KEY,
    uf_cidade VARCHAR(3) NOT NULL,
    cod_ibge_cidade INT NOT NULL,
    nom_cidade VARCHAR(50) NOT NULL,
    cod_estado INT REFERENCES estado(cod_estado)
);
COMMENT ON TABLE cidade IS 'Tabela de cidades';
COMMENT ON COLUMN cidade.cod_cidade IS 'Código da cidade';
COMMENT ON COLUMN cidade.uf_cidade IS 'UF da cidade';
COMMENT ON COLUMN cidade.cod_ibge_cidade IS 'Código do IBGE da cidade';
COMMENT ON COLUMN cidade.nom_cidade IS 'Nome da cidade';

-- Criação da tabela bairro
CREATE TABLE bairro (
    cod_bairro INT PRIMARY KEY,
    nom_bairro VARCHAR(50) NOT NULL,
    cod_cidade INT REFERENCES cidade(cod_cidade)
);
COMMENT ON TABLE bairro IS 'Tabela de bairros';
COMMENT ON COLUMN bairro.cod_bairro IS 'Código do bairro';
COMMENT ON COLUMN bairro.nom_bairro IS 'Nome do bairro';
COMMENT ON COLUMN bairro.cod_cidade IS 'Código da cidade do bairro';

-- Criação da tabela departamento_policia
CREATE TABLE departamento_policia (
    cod_dp INT PRIMARY KEY,
    distrito_dp INT NOT null,
    numero_enderecodp INT NOT NULL,
    codbairrodp INT REFERENCES bairro(cod_bairro)
    
);

COMMENT ON TABLE departamento_policia IS 'Tabela dos departamentos de polícia';
COMMENT ON COLUMN departamento_policia.cod_dp IS 'Código do departamento de polícia';
COMMENT ON COLUMN departamento_policia.distrito_dp IS 'Distrito do departamento de polícia';


-- Criação da tabela tipo_ocorrencia
CREATE TABLE tipo_ocorrencia (
    cod_tipo_ocorrencia INT PRIMARY KEY,
    desc_ocorrencia VARCHAR(255) NOT NULL
);
COMMENT ON TABLE tipo_ocorrencia IS 'Tabela dos tipos de ocorrência';
COMMENT ON COLUMN tipo_ocorrencia.cod_tipo_ocorrencia IS 'Código do tipo de ocorrência';
COMMENT ON COLUMN tipo_ocorrencia.desc_ocorrencia IS 'Descrição do acontecimento';

-- Criação da tabela pessoa
CREATE TABLE pessoa (
    cod_pessoa INT PRIMARY KEY,
    nom_pessoa VARCHAR(40) NOT NULL,
    idadepessoa int not null,
    generopessoa char not null,
    est_civil_pessoa VARCHAR(40) NOT NULL,
    data_nasc_pessoa DATE NOT NULL,
    cpf_pessoa NUMERIC(11,0) UNIQUE,
    num_tele_pessoa NUMERIC(14,0) NOT NULL,
    email_pessoa VARCHAR(40) NOT null,
    numero_enderecop INT NOT NULL,
    codbairrop INT REFERENCES bairro(cod_bairro)
);
COMMENT ON TABLE pessoa IS 'Tabela de pessoas para registrar o BO';
COMMENT ON COLUMN pessoa.cod_pessoa IS 'Código da pessoa';
COMMENT ON COLUMN pessoa.nom_pessoa IS 'Nome da pessoa';
COMMENT ON COLUMN pessoa.cpf_pessoa IS 'CPF da pessoa';
COMMENT ON COLUMN pessoa.est_civil_pessoa IS 'Estado civil da pessoa';
COMMENT ON COLUMN pessoa.data_nasc_pessoa IS 'Data de nascimento da pessoa';
COMMENT ON COLUMN pessoa.num_tele_pessoa IS 'Número de telefone da pessoa';
COMMENT ON COLUMN pessoa.email_pessoa IS 'Email da pessoa';
comment on column pessoa.numero_enderecop is 'Numero da casa da pessoa';
comment on column pessoa.codbairrop is 'codigo do bairro da pessoa';

-- Criação da tabela funcionario
CREATE TABLE funcionario (
    cod_fun INT PRIMARY KEY,
    cargo_fun INT NOT NULL,
    cod_dp INT REFERENCES departamento_policia(cod_dp),
    dp_atuacao_fun INT NOT NULL,
    cod_pessoa INT REFERENCES pessoa(cod_pessoa)
);
COMMENT ON TABLE funcionario IS 'Tabela de funcionários';
COMMENT ON COLUMN funcionario.cod_fun IS 'Código do funcionário';
COMMENT ON COLUMN funcionario.cargo_fun IS 'Cargo do funcionário';
COMMENT ON COLUMN funcionario.dp_atuacao_fun IS 'DP que o funcionário está atuando';

-- Criação da tabela bo
CREATE TABLE bo (
    cod_bo INT PRIMARY KEY,
    data_bo DATE NOT NULL,
    local_bo VARCHAR(50) NOT NULL,
    sit_bo BOOLEAN NOT NULL,
    cod_cidadebo INT REFERENCES cidade(cod_cidade),
    cod_fun INT REFERENCES funcionario(cod_fun)
);
COMMENT ON TABLE bo IS 'Tabela de boletins de ocorrência';
COMMENT ON COLUMN bo.cod_bo IS 'Código do boletim de ocorrência';
COMMENT ON COLUMN bo.data_bo IS 'Data que foi gerado o boletim de ocorrência';
COMMENT ON COLUMN bo.local_bo IS 'Local que foi feito o BO';
COMMENT ON COLUMN bo.sit_bo IS 'Situação do boletim de ocorrência';

-- Criação da tabela ocorrencia
CREATE TABLE ocorrencia (
    cod_ocorrencia INT PRIMARY KEY,
    data_ocorrencia DATE NOT NULL,
    local_ocorrencia VARCHAR(255) NOT NULL,
    num_envolvidos INT NOT NULL,
    cod_cidadeo INT REFERENCES cidade(cod_cidade),
    cod_tipo_ocorrencia INT REFERENCES tipo_ocorrencia(cod_tipo_ocorrencia),
    cod_bo INT REFERENCES bo(cod_bo)
);
COMMENT ON TABLE ocorrencia IS 'Tabela de ocorrências';
COMMENT ON COLUMN ocorrencia.cod_ocorrencia IS 'Código da ocorrência';
COMMENT ON COLUMN ocorrencia.data_ocorrencia IS 'Data que aconteceu o fato';
COMMENT ON COLUMN ocorrencia.local_ocorrencia IS 'Local do fato';
COMMENT ON COLUMN ocorrencia.num_envolvidos IS 'Quantidade de envolvidos no fato';