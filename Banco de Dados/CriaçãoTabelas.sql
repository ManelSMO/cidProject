-- Tabela pais
CREATE TABLE pais (
    idpais SERIAL NOT NULL,
    nompais VARCHAR(50) NOT NULL,
    PRIMARY KEY (idpais)
);
COMMENT ON COLUMN pais.idpais IS 'Id do pais';
COMMENT ON COLUMN pais.nompais IS 'Nome do pais';

-- Tabela tipo de envolvimento
CREATE TABLE tipo_envolvimento (
    tipenv VARCHAR(80) NOT NULL,
    desctipenv TEXT NOT NULL,
    PRIMARY KEY (tipenv)
);
COMMENT ON COLUMN tipo_envolvimento.tipenv IS 'Tipo do envolvimento';
COMMENT ON COLUMN tipo_envolvimento.desctipenv IS 'Descrição do tipo do envolvimento';

CREATE TABLE tipo_ocorrencia (
    idtipoco SERIAL NOT NULL,
    desctipoco text NOT NULL,
    PRIMARY KEY (idtipoco)
);
COMMENT ON COLUMN tipo_ocorrencia.idtipoco IS 'Codigo do tipo de ocorrencia';
COMMENT ON COLUMN tipo_ocorrencia.desctipoco IS 'Descricao do acontecimento';

CREATE TABLE tipo_violencia (
    idtipvio VARCHAR(40) NOT NULL,
    desctipvio text,
    PRIMARY KEY (idtipvio)
);
COMMENT ON TABLE tipo_violencia IS 'Tabela dos tipos de violencia';
COMMENT ON COLUMN tipo_violencia.desctipvio IS 'descrição da violencia';

CREATE TABLE usuario (
    idusu SERIAL NOT NULL,                  
    cpfusu VARCHAR(13) UNIQUE,              
    senusu VARCHAR(30),                     
    senhafun VARCHAR(30),                  
    matfun VARCHAR(10) UNIQUE,              
    PRIMARY KEY (idusu)
);
COMMENT ON COLUMN usuario.cpfusu IS 'cpf do Usuario';
COMMENT ON COLUMN usuario.senusu IS 'Senha do usuario';

CREATE TABLE estado (
    idest SERIAL NOT NULL,
    siglaest CHAR(2) NOT NULL,
    nomest VARCHAR(80) NOT NULL,
    paisidpais INT4 NOT NULL,
    PRIMARY KEY (idest),
    FOREIGN KEY (paisidpais) REFERENCES pais (idpais)
);
COMMENT ON COLUMN estado.siglaest IS 'Sigla do estado';
COMMENT ON COLUMN estado.nomest IS 'Nome do Estado';

CREATE TABLE cidade (
    idcid SERIAL NOT NULL,
    nomcid VARCHAR(80) NOT NULL,
    estadoidest INT4 NOT NULL,
    PRIMARY KEY (idcid),
    FOREIGN KEY (estadoidest) REFERENCES estado (idest)
);
COMMENT ON TABLE cidade IS 'Tabela de cidades';
COMMENT ON COLUMN cidade.nomcid IS 'Nome da cidade';

CREATE TABLE bairro (
    idbai SERIAL NOT NULL,
    nombai VARCHAR(80) NOT NULL,
    estadoidest INT4 NOT NULL,
    PRIMARY KEY (idbai),
    FOREIGN KEY (estadoidest) REFERENCES estado (idest)
);
COMMENT ON TABLE bairro IS 'Tabela de Bairros';
COMMENT ON COLUMN bairro.nombai IS 'Nome do bairro';

CREATE TABLE departamento_policial (
    iddep SERIAL NOT NULL,
    disdep VARCHAR(80) NOT NULL,
    emaildep VARCHAR(40) NOT NULL,
    telatedep NUMERIC(14, 0) NOT NULL,
    logrdp Varchar(255) NOT NULL,
    numdp INT4 NOT NULL,
    bairroidbai INT4 NOT NULL,
    cidadeidcid INT4 NOT NULL,
    PRIMARY KEY (iddep),
    FOREIGN KEY (bairroidbai) REFERENCES bairro (idbai) ON DELETE CASCADE,
    FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid) ON DELETE CASCADE
);
COMMENT ON COLUMN departamento_policial.iddep IS 'Código do departamento de policia';
COMMENT ON COLUMN departamento_policial.disdep IS 'Distrito do DP';
COMMENT ON COLUMN departamento_policial.emaildep IS 'Email da Dp';
COMMENT ON COLUMN departamento_policial.telatedep IS 'Telefone de atendimento da Dp';
COMMENT ON COLUMN departamento_policial.logrdp IS 'Logradouro do departamento';
COMMENT ON COLUMN departamento_policial.numdp IS 'Numero da residencia do departamento';

CREATE TABLE pessoa (
    idpes SERIAL NOT NULL,
    nompes VARCHAR(80) NOT NULL,
    estcivpes VARCHAR(40) NOT NULL,
    datnaspes DATE NOT NULL CHECK (datnaspes <= CURRENT_DATE),
    numtelpes NUMERIC(14, 0) NOT NULL CHECK (numtelpes > 0),
    emailpes VARCHAR(80) NOT NULL CHECK (emailpes LIKE '%@%'),
    genpes VARCHAR(15) NOT NULL CHECK (genpes IN ('Masculino', 'Feminino', 'Outro')),
    usuarioidusu INT4 NOT NULL,
    Fotpes BYTEA,
    PRIMARY KEY (idpes),
    FOREIGN KEY (usuarioidusu) REFERENCES usuario (idusu)
);
COMMENT ON TABLE pessoa IS 'Tabela de pessoas para registrar o BO';
COMMENT ON COLUMN pessoa.idpes IS 'Codigo Pessoa';
COMMENT ON COLUMN pessoa.nompes IS 'Nome da pessoa';
COMMENT ON COLUMN pessoa.estcivpes IS 'Estado civil da Pessoa';
COMMENT ON COLUMN pessoa.datnaspes IS 'Data de nascimento da pessoa';
COMMENT ON COLUMN pessoa.numtelpes IS 'numero de telefone da pessoa';
COMMENT ON COLUMN pessoa.emailpes IS 'E-mail da pessoa';
COMMENT ON COLUMN pessoa.genpes IS 'Genêro da pessoa';
COMMENT ON COLUMN pessoa.Fotpes IS 'Foto da Pessoa';

CREATE TABLE funcionario (
    pessoaidpes INT4 NOT NULL,
    carfun VARCHAR(80) NOT NULL,
    departamento_policiaiddep INT4 NOT NULL,
    PRIMARY KEY (pessoaidpes),
    FOREIGN KEY (pessoaidpes) REFERENCES pessoa (idpes),
    FOREIGN KEY (departamento_policiaiddep) REFERENCES departamento_policial (iddep)
);
COMMENT ON COLUMN funcionario.carfun IS 'Cargo do funcionario';

CREATE TABLE endereco_pessoa (
    idendpes SERIAL NOT NULL,
    logrpes VARCHAR(255) NOT NULL,
    numendpes INT4 NOT NULL,
    comendpes VARCHAR(255),
    bairroidbai INT4 NOT NULL,
    cidadeidcid INT4 NOT NULL,
    pessoaidpes INT4 NOT NULL,
    PRIMARY KEY (idendpes),
    FOREIGN KEY (pessoaidpes) REFERENCES pessoa (idpes),
    FOREIGN KEY (bairroidbai) REFERENCES bairro (idbai) ON DELETE CASCADE,
    FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid) ON DELETE CASCADE
);
COMMENT ON COLUMN endereco_pessoa.logrpes IS 'Rua ou avenida';
COMMENT ON COLUMN endereco_pessoa.numendpes IS 'Numero do imovel da pessoa';
COMMENT ON COLUMN endereco_pessoa.comendpes IS 'Complemento';

CREATE TABLE ocorrencia (
    idoco SERIAL NOT NULL,
    descoco TEXT,
    datoco TIMESTAMP,
    lococo VARCHAR(255) NOT NULL,
    staoco Varchar(40) NOT NULL,
    cidadeidcid INT4 NOT NULL,
    funcionariopessoaidpes INT4 NOT NULL,
    tipo_violenciaidtipooco VARCHAR(40) NOT NULL,
    tipo_ocorrenciaidtipoco INT4 NOT NULL,
    validaoco BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (idoco),
    FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid),
    FOREIGN KEY (funcionariopessoaidpes) REFERENCES funcionario (pessoaidpes),
    FOREIGN KEY (tipo_violenciaidtipooco) REFERENCES tipo_violencia (idtipvio),
    FOREIGN KEY (tipo_ocorrenciaidtipoco) REFERENCES tipo_ocorrencia (idtipoco)
);
COMMENT ON TABLE ocorrencia IS 'Tabela de Ocorrências';
COMMENT ON COLUMN ocorrencia.idoco IS 'Codigo da ocorrencia';
COMMENT ON COLUMN ocorrencia.descoco IS 'Descrição da ocorrencia';
COMMENT ON COLUMN ocorrencia.datoco IS 'Data que aconteceu da ocorrencia';
COMMENT ON COLUMN ocorrencia.lococo IS 'Local da ocorrencia';
COMMENT ON COLUMN ocorrencia.staoco IS 'Status da ocorrencia';

CREATE TABLE pessoa_envolvida (
    idpesenv SERIAL NOT NULL,
    nompesenv VARCHAR(80),
    descpesenv text,
    cpfenv VARCHAR(11),
    telenv NUMERIC(14, 0) CHECK (telenv > 0),
    datnasenv DATE CHECK (datnasenv <= CURRENT_DATE),
    emailenv varchar(80),
    ocorrenciaidoco INT4 NOT NULL,
    tipo_envolvimentotipenv VARCHAR(255) NOT NULL,
    PRIMARY KEY (idpesenv),
    FOREIGN KEY (ocorrenciaidoco) REFERENCES ocorrencia (idoco) ON DELETE CASCADE,
    FOREIGN KEY (tipo_envolvimentotipenv) REFERENCES tipo_envolvimento (tipenv) ON DELETE CASCADE
);
COMMENT ON TABLE pessoa_envolvida IS 'Tabela de pessoas envolvidas';
COMMENT ON COLUMN pessoa_envolvida.nompesenv IS 'Nome da pessoa envolvida';
COMMENT ON COLUMN pessoa_envolvida.descpesenv IS 'Descrição da pessoa envolvida';
COMMENT ON COLUMN pessoa_envolvida.cpfenv IS 'Cpf da pessoa envolvida';
COMMENT ON COLUMN pessoa_envolvida.telenv IS 'Telefone da pessoa envolvida';
COMMENT ON COLUMN pessoa_envolvida.datnasenv IS 'Data de nascimento da pessoa envolvida';
COMMENT ON COLUMN pessoa_envolvida.emailenv IS 'Email da pessoa envolvida';

CREATE TABLE anexo (
    idanexo SERIAL NOT NULL,
    camane VARCHAR(255) NOT NULL,
    tipoanexo VARCHAR(255) NOT NULL CHECK (tipoanexo IN ('imagem', 'video', 'documento')),
    descanexo TEXT,
    datanexo TIMESTAMP NOT NULL,
    ocorrenciaidoco INT4 NOT NULL,
    PRIMARY KEY (idanexo),
    FOREIGN KEY (ocorrenciaidoco) REFERENCES ocorrencia (idoco)
);
COMMENT ON COLUMN anexo.camane IS 'Caminho do arquivo anexado';
COMMENT ON COLUMN anexo.tipoanexo IS 'Tipo do anexo (video, imagem, documento...)';
COMMENT ON COLUMN anexo.descanexo IS 'Descrição do anexo';
COMMENT ON COLUMN anexo.datanexo IS 'Data que o anexo foi carregado';
