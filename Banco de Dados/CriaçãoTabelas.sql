
-- Tabela de países
CREATE TABLE pais (
    idpais SERIAL NOT NULL, 
    nompais VARCHAR(50) NOT NULL, 
    PRIMARY KEY (idpais)
);
COMMENT ON COLUMN pais.idpais IS 'ID do país';
COMMENT ON COLUMN pais.nompais IS 'Nome do país';

-- Tabela de tipos de ocorrência
CREATE TABLE tipo_ocorrencia (
    idtipoco SERIAL NOT NULL, 
    desctipoco VARCHAR(255) NOT NULL, 
    PRIMARY KEY (idtipoco)
);
COMMENT ON COLUMN tipo_ocorrencia.idtipoco IS 'Código do tipo de ocorrência';
COMMENT ON COLUMN tipo_ocorrencia.desctipoco IS 'Descrição do acontecimento';

-- Tabela de usuários
CREATE TABLE usuario (
    idusu SERIAL NOT NULL, 
    cpfusu VARCHAR(13) NOT NULL UNIQUE, 
    senusu VARCHAR(30) NOT NULL, 
    PRIMARY KEY (idusu)
);
COMMENT ON COLUMN usuario.cpfusu IS 'CPF do usuário';
COMMENT ON COLUMN usuario.senusu IS 'Senha do usuário';

-- Tabelas com chaves estrangeiras

-- Tabela de estados
CREATE TABLE estado (
    idest SERIAL NOT NULL, 
    siglaest CHAR(2) NOT NULL, 
    nomest VARCHAR(80) NOT NULL, 
    paisidpais INT4 NOT NULL, 
    PRIMARY KEY (idest),
    CONSTRAINT FKestado409842 FOREIGN KEY (paisidpais) REFERENCES pais (idpais)
);
COMMENT ON COLUMN estado.siglaest IS 'Sigla do estado';
COMMENT ON COLUMN estado.nomest IS 'Nome do estado';

-- Tabela de cidades
CREATE TABLE cidade (
    idcid SERIAL NOT NULL, 
    nomcid VARCHAR(80) NOT NULL, 
    estadoidest INT4 NOT NULL, 
    PRIMARY KEY (idcid),
    CONSTRAINT FKcidade432616 FOREIGN KEY (estadoidest) REFERENCES estado (idest)
);
COMMENT ON TABLE cidade IS 'Tabela de cidades';
COMMENT ON COLUMN cidade.nomcid IS 'Nome da cidade';

-- Tabela de bairros
CREATE TABLE bairro (
    idbai SERIAL NOT NULL, 
    nombai VARCHAR(80) NOT NULL, 
    estadoidest INT4 NOT NULL, 
    PRIMARY KEY (idbai),
    CONSTRAINT FKbairro284235 FOREIGN KEY (estadoidest) REFERENCES estado (idest)
);
COMMENT ON TABLE bairro IS 'Tabela de Bairros';
COMMENT ON COLUMN bairro.nombai IS 'Nome do bairro';

-- Tabela de endereços de pessoas
CREATE TABLE endereco_pessoa (
    idendpes SERIAL NOT NULL, 
    logrpes VARCHAR(80) NOT NULL, 
    numendpes INT4 NOT NULL, 
    comendpes VARCHAR(255), 
    bairroidbai INT4 NOT NULL, 
    cidadeidcid INT4 NOT NULL, 
    PRIMARY KEY (idendpes),
    CONSTRAINT FKendereco_p368365 FOREIGN KEY (bairroidbai) REFERENCES bairro (idbai),
    CONSTRAINT FKendereco_p287486 FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid)
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
    CONSTRAINT FKendereco_d754178 FOREIGN KEY (bairroidbai) REFERENCES bairro (idbai),
    CONSTRAINT FKendereco_d901672 FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid)
);
COMMENT ON TABLE endereco_departamento IS 'Tabela de endereços dos departamentos de polícia';
COMMENT ON COLUMN endereco_departamento.logrdp IS 'Rua ou avenida';
COMMENT ON COLUMN endereco_departamento.numenddp IS 'Número do imóvel do departamento de polícia';

-- Tabela de departamentos policiais
CREATE TABLE departamento_policial (
    iddep SERIAL NOT NULL, 
    disdep VARCHAR(255) NOT NULL, 
    emaildep VARCHAR(40) NOT NULL, 
    telatedep NUMERIC(14, 0) NOT NULL, 
    enddp INT4 NOT NULL, 
    PRIMARY KEY (iddep),
    CONSTRAINT FKdepartamen408914 FOREIGN KEY (enddp) REFERENCES endereco_departamento (idenddp)
);
COMMENT ON COLUMN departamento_policial.iddep IS 'Código do departamento de polícia';
COMMENT ON COLUMN departamento_policial.disdep IS 'Distrito do DP';
COMMENT ON COLUMN departamento_policial.emaildep IS 'Email do DP';
COMMENT ON COLUMN departamento_policial.telatedep IS 'Telefone de atendimento do DP';
COMMENT ON COLUMN departamento_policial.enddp IS 'Endereço do departamento';

-- Tabela de pessoas
CREATE TABLE pessoa (
    idpes SERIAL NOT NULL, 
    nompes VARCHAR(80) NOT NULL, 
    estcivpes VARCHAR(40) NOT NULL, 
    datnaspes DATE NOT NULL, 
    numtelpes NUMERIC(14, 0) NOT NULL, 
    emailpes VARCHAR(255) NOT NULL, 
    genpes VARCHAR(15) NOT NULL, 
    endpes INT4 NOT NULL, 
    usuarioidusu INT4 NOT NULL, 
    PRIMARY KEY (idpes),
    CONSTRAINT FKpessoa888829 FOREIGN KEY (endpes) REFERENCES endereco_pessoa (idendpes),
    CONSTRAINT FKpessoa913091 FOREIGN KEY (usuarioidusu) REFERENCES usuario (idusu)
);
COMMENT ON TABLE pessoa IS 'Tabela de pessoas para registrar o BO';
COMMENT ON COLUMN pessoa.idpes IS 'Código da pessoa';
COMMENT ON COLUMN pessoa.nompes IS 'Nome da pessoa';
COMMENT ON COLUMN pessoa.estcivpes IS 'Estado civil da pessoa';
COMMENT ON COLUMN pessoa.datnaspes IS 'Data de nascimento da pessoa';
COMMENT ON COLUMN pessoa.numtelpes IS 'Número de telefone da pessoa';
COMMENT ON COLUMN pessoa.emailpes IS 'E-mail da pessoa';
COMMENT ON COLUMN pessoa.genpes IS 'Gênero da pessoa';
COMMENT ON COLUMN pessoa.endpes IS 'Endereço da pessoa';

-- Tabela de funcionários
CREATE TABLE funcionario (
    idfun SERIAL NOT NULL, 
    carfun VARCHAR(255) NOT NULL, 
    delfun INT4 NOT NULL, 
    departamento_policiaiddep INT4 NOT NULL, 
    pessoaidpes INT4 NOT NULL, 
    PRIMARY KEY (idfun),
    CONSTRAINT FKfuncionari593755 FOREIGN KEY (departamento_policiaiddep) REFERENCES departamento_policial (iddep),
    CONSTRAINT FKfuncionari693404 FOREIGN KEY (pessoaidpes) REFERENCES pessoa (idpes)
);
COMMENT ON COLUMN funcionario.idfun IS 'Código do funcionário';
COMMENT ON COLUMN funcionario.carfun IS 'Cargo do funcionário';
COMMENT ON COLUMN funcionario.delfun IS 'DP onde o funcionário atua';

-- Tabela de ocorrências
CREATE TABLE ocorrencia (
    idoco SERIAL NOT NULL, 
    datoco DATE, 
    vio BOOLEAN, 
    lococo VARCHAR(255) NOT NULL, 
    cidadeidcid INT4 NOT NULL, 
    tipo_ocorrenciaidtipoco INT4 NOT NULL, 
    funcionarioidfun INT4 NOT NULL, 
    PRIMARY KEY (idoco),
    CONSTRAINT FKocorrencia989834 FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid),
    CONSTRAINT FKocorrencia750208 FOREIGN KEY (tipo_ocorrenciaidtipoco) REFERENCES tipo_ocorrencia (idtipoco),
    CONSTRAINT FKocorrencia32044 FOREIGN KEY (funcionarioidfun) REFERENCES funcionario (idfun)
);
COMMENT ON TABLE ocorrencia IS 'Tabela de ocorrências';
COMMENT ON COLUMN ocorrencia.idoco IS 'Código da ocorrência';
COMMENT ON COLUMN ocorrencia.datoco IS 'Data da ocorrência';
COMMENT ON COLUMN ocorrencia.vio IS 'Indica se houve violência na ocorrência';
COMMENT ON COLUMN ocorrencia.lococo IS 'Local da ocorrência';

-- Tabela de anexos
CREATE TABLE anexo (
    idanexo SERIAL NOT NULL, 
    camane VARCHAR(255) NOT NULL, 
    tipoanexo VARCHAR(255) NOT NULL, 
    descanexo TEXT, 
    datanexo DATE NOT NULL, 
    ocorrenciaidoco INT4 NOT NULL, 
    PRIMARY KEY (idanexo),
    CONSTRAINT FKanexo376378 FOREIGN KEY (ocorrenciaidoco) REFERENCES ocorrencia (idoco)
);
COMMENT ON COLUMN anexo.camane IS 'Caminho do arquivo anexado';
COMMENT ON COLUMN anexo.tipoanexo IS 'Tipo do anexo (vídeo, imagem, documento...)';
COMMENT ON COLUMN anexo.descanexo IS 'Descrição do anexo';
COMMENT ON COLUMN anexo.datanexo IS 'Data que o anexo foi adicionado';

-- Tabela de pessoas envolvidas em ocorrências
CREATE TABLE pessoa_envolvida (
    idpesenv SERIAL NOT NULL, 
    nompesenv VARCHAR(80), 
    tipoenv VARCHAR(255) NOT NULL, 
    descpesenv VARCHAR(255), 
    ocorrenciaidoco INT4 NOT NULL, 
    PRIMARY KEY (idpesenv),
    CONSTRAINT FKpessoa_env508213 FOREIGN KEY (ocorrenciaidoco) REFERENCES ocorrencia (idoco)
);
COMMENT ON TABLE pessoa_envolvida IS 'Tabela de pessoas envolvidas';
COMMENT ON COLUMN pessoa_envolvida.nompesenv IS 'Nome da pessoa envolvida';
COMMENT ON COLUMN pessoa_envolvida.tipoenv IS 'Tipo de envolvimento';
COMMENT ON COLUMN pessoa_envolvida.descpesenv IS 'Descrição da pessoa envolvida';
