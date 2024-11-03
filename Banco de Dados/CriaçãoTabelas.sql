-- Tabela de países
CREATE TABLE pais (
    idpais SERIAL NOT NULL, 
    nompais VARCHAR(50) NOT NULL, 
    PRIMARY KEY (idpais)
);

COMMENT ON COLUMN pais.idpais IS 'Código do país';
COMMENT ON COLUMN pais.nompais IS 'Nome do país';


-- Tabela de tipos de ocorrência
CREATE TABLE tipo_ocorrencia (
    idtipoco SERIAL NOT NULL, 
    desctipoco VARCHAR(255) NOT NULL, 
    PRIMARY KEY (idtipoco)
);

COMMENT ON COLUMN tipo_ocorrencia.idtipoco IS 'Código do tipo de ocorrência';
COMMENT ON COLUMN tipo_ocorrencia.desctipoco IS 'Descrição do tipo de ocorrência';


-- Tabela de estados
CREATE TABLE estado (
    idest SERIAL NOT NULL, 
    siglaest CHAR(2) NOT NULL, 
    nomest VARCHAR(80) NOT NULL, 
    paisidpais INT4 NOT NULL, 
    PRIMARY KEY (idest),
    CONSTRAINT FK_estado_pais FOREIGN KEY (paisidpais) REFERENCES pais (idpais)
);

COMMENT ON COLUMN estado.siglaest IS 'Sigla do estado';
COMMENT ON COLUMN estado.nomest IS 'Nome do estado';

-- Tabela de bairros
CREATE TABLE bairro (
    idbai SERIAL NOT NULL, 
    nombai VARCHAR(80) NOT NULL,
    bairroidest int4 not null,
    PRIMARY KEY (idbai),
    CONSTRAINT FK_bairro_estado FOREIGN KEY (bairroidest) REFERENCES estado (idest)
    
);

COMMENT ON TABLE bairro IS 'Tabela de Bairros';
COMMENT ON COLUMN bairro.nombai IS 'Nome do bairro';


-- Tabela de cidades
CREATE TABLE cidade (
    idcid SERIAL NOT NULL, 
    nomcid VARCHAR(80) NOT NULL, 
    estadoidest INT4 NOT NULL, 
    PRIMARY KEY (idcid),
    CONSTRAINT FK_cidade_estado FOREIGN KEY (estadoidest) REFERENCES estado (idest)
);

COMMENT ON TABLE cidade IS 'Tabela de Cidades';
COMMENT ON COLUMN cidade.nomcid IS 'Nome da cidade';

-- Tabela de endereços de pessoas
CREATE TABLE endereco_pessoa (
    idendpes SERIAL NOT NULL, 
    logrpes VARCHAR(80) NOT NULL, 
    numendpes INT4 NOT NULL, 
    comendpes VARCHAR(255), 
    bairroidbai INT4 NOT NULL, 
    cidadeidcid INT4 NOT NULL, 
    PRIMARY KEY (idendpes),
    CONSTRAINT FK_endereco_pessoa_bairro FOREIGN KEY (bairroidbai) REFERENCES bairro (idbai),
    CONSTRAINT FK_endereco_pessoa_cidade FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid)
);

COMMENT ON COLUMN endereco_pessoa.logrpes IS 'Rua ou avenida';
COMMENT ON COLUMN endereco_pessoa.numendpes IS 'Número do imóvel da pessoa';
COMMENT ON COLUMN endereco_pessoa.comendpes IS 'Complemento';

-- Tabela de endereços dos departamentos policiais
CREATE TABLE endereco_departamento (
    idenddp SERIAL NOT NULL, 
    logrdp VARCHAR(255) NOT NULL, 
    numenddp INT4 NOT NULL, 
    bairroidbai INT4 NOT NULL, 
    cidadeidcid INT4 NOT NULL, 
    PRIMARY KEY (idenddp),
    CONSTRAINT FK_endereco_departamento_bairro FOREIGN KEY (bairroidbai) REFERENCES bairro (idbai),
    CONSTRAINT FK_endereco_departamento_cidade FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid)
);

COMMENT ON TABLE endereco_departamento IS 'Tabela de endereços dos departamentos policiais';
COMMENT ON COLUMN endereco_departamento.logrdp IS 'Rua ou avenida';
COMMENT ON COLUMN endereco_departamento.numenddp IS 'Número do imóvel do departamento de polícia';

-- Tabela de pessoas
CREATE TABLE pessoa (
    idpes SERIAL NOT NULL, 
    nompes VARCHAR(40) NOT NULL, 
    cpfpes VARCHAR(11) NOT NULL UNIQUE, 
    estcivpes VARCHAR(40) NOT NULL, 
    datnaspes DATE NOT NULL, 
    numtelpes NUMERIC(14, 0) NOT NULL, 
    emailpes VARCHAR(255) NOT NULL, 
    genpes VARCHAR(15) NOT NULL, 
    endpes INT4 NOT NULL, 
    PRIMARY KEY (idpes),
    CONSTRAINT FK_pessoa_endereco FOREIGN KEY (endpes) REFERENCES endereco_pessoa (idendpes)
);

COMMENT ON TABLE pessoa IS 'Tabela de pessoas para registrar o BO';
COMMENT ON COLUMN pessoa.idpes IS 'Código da pessoa';
COMMENT ON COLUMN pessoa.nompes IS 'Nome da pessoa';
COMMENT ON COLUMN pessoa.cpfpes IS 'CPF da pessoa';
COMMENT ON COLUMN pessoa.estcivpes IS 'Estado civil da pessoa';
COMMENT ON COLUMN pessoa.datnaspes IS 'Data de nascimento da pessoa';
COMMENT ON COLUMN pessoa.numtelpes IS 'Número de telefone da pessoa';
COMMENT ON COLUMN pessoa.emailpes IS 'E-mail da pessoa';
COMMENT ON COLUMN pessoa.genpes IS 'Gênero da pessoa';
COMMENT ON COLUMN pessoa.endpes IS 'Endereço da pessoa';

CREATE TABLE departamento_policial (
	iddep SERIAL NOT NULL,
	disdep varchar(255) NOT NULL,
	emaildep varchar(40) NOT NULL,
	telatedep numeric(14, 0) NOT NULL,
	enddp int4 NOT NULL, PRIMARY KEY (iddep)
);
COMMENT ON COLUMN departamento_policial.iddep IS 'Código do departamento de policia';
COMMENT ON COLUMN departamento_policial.disdep IS 'Distrito do DP';
COMMENT ON COLUMN departamento_policial.emaildep IS 'Email da Dp';
COMMENT ON COLUMN departamento_policial.telatedep IS 'Telefone de atendimento da Dp';
COMMENT ON COLUMN departamento_policial.enddp IS 'Endereço do departamento';

-- Tabela de funcionários
CREATE TABLE funcionario (
    idfun SERIAL NOT NULL, 
    carfun VARCHAR(255) NOT NULL, 
    delfun INT4 NOT NULL, 
    departamento_policiaiddep INT4 NOT NULL, 
    pessoaidpes INT4 NOT NULL, 
    PRIMARY KEY (idfun),
    CONSTRAINT FK_funcionario_departamento FOREIGN KEY (departamento_policiaiddep) REFERENCES departamento_policial (iddep),
    CONSTRAINT FK_funcionario_pessoa FOREIGN KEY (pessoaidpes) REFERENCES pessoa (idpes)
);

COMMENT ON COLUMN funcionario.idfun IS 'Código do funcionário';
COMMENT ON COLUMN funcionario.carfun IS 'Cargo do funcionário';
COMMENT ON COLUMN funcionario.delfun IS 'Departamento de polícia onde o funcionário atua';

-- Tabela de ocorrências
CREATE TABLE ocorrencia (
    idoco SERIAL NOT NULL, 
    datoco DATE, 
    lococo VARCHAR(255) NOT NULL, 
    cidadeidcid INT4 NOT NULL, 
    tipo_ocorrenciaidtipoco INT4 NOT NULL, 
    funcionarioidfun INT4 NOT NULL, 
    tipvio BOOLEAN NOT NULL, 
    PRIMARY KEY (idoco),
    CONSTRAINT FK_ocorrencia_cidade FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid),
    CONSTRAINT FK_ocorrencia_tipo_ocorrencia FOREIGN KEY (tipo_ocorrenciaidtipoco) REFERENCES tipo_ocorrencia (idtipoco),
    CONSTRAINT FK_ocorrencia_funcionario FOREIGN KEY (funcionarioidfun) REFERENCES funcionario (idfun)
);

COMMENT ON TABLE ocorrencia IS 'Tabela de ocorrências';
COMMENT ON COLUMN ocorrencia.idoco IS 'Código da ocorrência';
COMMENT ON COLUMN ocorrencia.datoco IS 'Data da ocorrência';
COMMENT ON COLUMN ocorrencia.lococo IS 'Local da ocorrência';
COMMENT ON COLUMN ocorrencia.tipvio IS 'Indica se houve violência na ocorrência';


-- Tabela de pessoas envolvidas em ocorrências
CREATE TABLE pessoa_envolvida (
    idpesenv SERIAL NOT NULL, 
    tipoenv VARCHAR(255) NOT NULL, 
    descpesenv VARCHAR(255), 
    ocorrenciaidoco INT4 NOT NULL, 
    pessoaidpes INT4 NOT NULL, 
    PRIMARY KEY (idpesenv),
    CONSTRAINT FK_pessoa_envolvida_ocorrencia FOREIGN KEY (ocorrenciaidoco) REFERENCES ocorrencia (idoco),
    CONSTRAINT FK_pessoa_envolvida_pessoa FOREIGN KEY (pessoaidpes) REFERENCES pessoa (idpes)
);

COMMENT ON TABLE pessoa_envolvida IS 'Tabela de pessoas envolvidas em ocorrências';
COMMENT ON COLUMN pessoa_envolvida.tipoenv IS 'Tipo de envolvimento da pessoa na ocorrência';
COMMENT ON COLUMN pessoa_envolvida.descpesenv IS 'Descrição da pessoa envolvida';

-- Tabela de anexos
CREATE TABLE anexo (
    idanexo SERIAL NOT NULL, 
    camane VARCHAR(255) NOT NULL, 
    tipoanexo VARCHAR(255) NOT NULL, 
    descanexo TEXT, 
    datanexo DATE NOT NULL, 
    ocorrenciaidoco INT4 NOT NULL, 
    PRIMARY KEY (idanexo),
    CONSTRAINT FK_anexo_ocorrencia FOREIGN KEY (ocorrenciaidoco) REFERENCES ocorrencia (idoco)
);

COMMENT ON COLUMN anexo.camane IS 'Caminho do arquivo anexado';
COMMENT ON COLUMN anexo.tipoanexo IS 'Tipo do anexo (vídeo, imagem, documento...)';
COMMENT ON COLUMN anexo.descanexo IS 'Descrição do anexo';




