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

-- Tabela tipo de ocorrência
CREATE TABLE tipo_ocorrencia (
    idtipoco SERIAL NOT NULL,
    desctipoco TEXT NOT NULL,
    PRIMARY KEY (idtipoco)
);
COMMENT ON COLUMN tipo_ocorrencia.idtipoco IS 'Código do tipo de ocorrência';
COMMENT ON COLUMN tipo_ocorrencia.desctipoco IS 'Descrição do acontecimento';

-- Tabela tipo de violência
CREATE TABLE tipo_violencia (
    idtipvio VARCHAR(40) NOT NULL,
    desctipvio TEXT,
    PRIMARY KEY (idtipvio)
);
COMMENT ON TABLE tipo_violencia IS 'Tabela dos tipos de violência';
COMMENT ON COLUMN tipo_violencia.desctipvio IS 'Descrição da violência';

-- Tabela principal de usuários
CREATE TABLE usuario (
    idusu SERIAL PRIMARY KEY,
    tipo_usu VARCHAR(20) NOT NULL CHECK (tipo_usu IN ('Cidadão', 'Policial')),
    status BOOLEAN DEFAULT TRUE, -- Indica se o usuário está ativo
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE usuario IS 'Tabela principal de usuários';
COMMENT ON COLUMN usuario.tipo_usu IS 'Tipo do usuário (Cidadao ou Policial)';
COMMENT ON COLUMN usuario.status IS 'Status do usuário (ativo ou inativo)';


-- Tabela de acesso para cidadãos
CREATE TABLE acesso_cidadao (
    idacessoc SERIAL PRIMARY KEY,
    usuarioidusu INT NOT NULL UNIQUE,
    cpfusu VARCHAR(14) NOT NULL UNIQUE, -- CPF formatado
    senusu VARCHAR(255) NOT NULL,       -- Senha do cidadão
    FOREIGN KEY (usuarioidusu) REFERENCES usuario (idusu) ON DELETE CASCADE
);
COMMENT ON TABLE acesso_cidadao IS 'Tabela de credenciais de acesso para cidadãos';
COMMENT ON COLUMN acesso_cidadao.cpfusu IS 'CPF do cidadão';
COMMENT ON COLUMN acesso_cidadao.senusu IS 'Senha do cidadão';

-- Tabela de acesso para policiais
CREATE TABLE acesso_policial (
    idacessop SERIAL PRIMARY KEY,
    usuarioidusu INT NOT NULL UNIQUE,
    matfun VARCHAR(10) NOT NULL UNIQUE, -- Matrícula do policial
    senhafun VARCHAR(255) NOT NULL,     -- Senha do policial
    FOREIGN KEY (usuarioidusu) REFERENCES usuario (idusu) ON DELETE CASCADE
);
COMMENT ON TABLE acesso_policial IS 'Tabela de credenciais de acesso para policiais';
COMMENT ON COLUMN acesso_policial.matfun IS 'Matrícula do policial';
COMMENT ON COLUMN acesso_policial.senhafun IS 'Senha do policial';



-- Tabela estado
CREATE TABLE estado (
    idest SERIAL NOT NULL,
    siglaest CHAR(2) NOT NULL,
    nomest VARCHAR(80) NOT NULL,
    paisidpais INT4 NOT NULL,
    PRIMARY KEY (idest),
    FOREIGN KEY (paisidpais) REFERENCES pais (idpais)
);
COMMENT ON COLUMN estado.siglaest IS 'Sigla do estado';
COMMENT ON COLUMN estado.nomest IS 'Nome do estado';

-- Tabela cidade
CREATE TABLE cidade (
    idcid SERIAL NOT NULL,
    nomcid VARCHAR(80) NOT NULL,
    estadoidest INT4 NOT NULL,
    PRIMARY KEY (idcid),
    FOREIGN KEY (estadoidest) REFERENCES estado (idest)
);
COMMENT ON TABLE cidade IS 'Tabela de cidades';
COMMENT ON COLUMN cidade.nomcid IS 'Nome da cidade';

-- Tabela bairro
CREATE TABLE bairro (
    idbai SERIAL NOT NULL,
    nombai VARCHAR(80) NOT NULL,
    estadoidest INT4 NOT NULL,
    PRIMARY KEY (idbai),
    FOREIGN KEY (estadoidest) REFERENCES estado (idest)
);
COMMENT ON TABLE bairro IS 'Tabela de bairros';
COMMENT ON COLUMN bairro.nombai IS 'Nome do bairro';

-- Tabela departamento policial
CREATE TABLE departamento_policial (
    iddep SERIAL NOT NULL,
    disdep VARCHAR(80) NOT NULL,
    emaildep VARCHAR(40) NOT NULL,
    telatedep NUMERIC(14, 0) NOT NULL,
    logrdp VARCHAR(255) NOT NULL,
    numdp INT4 NOT NULL,
    bairroidbai INT4 NOT NULL,
    cidadeidcid INT4 NOT NULL,
    PRIMARY KEY (iddep),
    FOREIGN KEY (bairroidbai) REFERENCES bairro (idbai) ON DELETE CASCADE,
    FOREIGN KEY (cidadeidcid) REFERENCES cidade (idcid) ON DELETE CASCADE
);
COMMENT ON COLUMN departamento_policial.disdep IS 'Distrito do departamento policial';
COMMENT ON COLUMN departamento_policial.emaildep IS 'Email do departamento policial';

-- Tabela pessoa
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
    acesso_cidadao int4,
    acesso_policial int4,
    PRIMARY KEY (idpes),
    FOREIGN KEY (acesso_cidadao) REFERENCES acesso_cidadao (idacessoc),
    FOREIGN KEY (acesso_policial) REFERENCES acesso_policial (idacessop)
    
);
COMMENT ON TABLE pessoa IS 'Tabela de pessoas para registro';

-- Tabela funcionário
CREATE TABLE funcionario (
    pessoaidpes INT4 NOT NULL,
    carfun VARCHAR(80) NOT NULL,
    departamento_policiaiddep INT4 NOT NULL,
    PRIMARY KEY (pessoaidpes),
    FOREIGN KEY (pessoaidpes) REFERENCES pessoa (idpes),
    FOREIGN KEY (departamento_policiaiddep) REFERENCES departamento_policial (iddep)
);
COMMENT ON COLUMN funcionario.carfun IS 'Cargo do funcionário';

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

