
CREATE ROLE grupo_cidadao;
CREATE ROLE grupo_policia;
create role grupo_admin;

----------Garantindo os acessos do grupo de adsministradores
-- Tabelas
GRANT ALL ON ALL TABLES IN SCHEMA public TO grupo_admin;

-- Sequências
GRANT ALL ON ALL SEQUENCES IN SCHEMA public TO grupo_admin;

-- Funções
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA public TO grupo_admin;

-- Configurar permissões automáticas para novos objetos
-- Novas tabelas
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO grupo_admin;

-- Novas sequências
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO grupo_admin;

-- Novas funções
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT EXECUTE ON FUNCTIONS TO grupo_admin;


---------------Funçoes da tabela GRUPO_POLICIAL---------------------------------------------
CREATE OR REPLACE FUNCTION atualizar_status_ocorrencia(
    id_ocorrencia INT,
    novo_status VARCHAR(40),
    responsavel INT
)
RETURNS VOID AS $$
BEGIN
    -- Verifica se o policial é o responsável pela ocorrência
    IF NOT EXISTS (
        SELECT 1 FROM ocorrencia 
        WHERE idoco = id_ocorrencia 
          AND funcionariopessoaidpes = responsavel
    ) THEN
        RAISE EXCEPTION 'Você não tem permissão para atualizar esta ocorrência.';
    END IF;

    -- Atualiza o status
    UPDATE ocorrencia
    SET staoco = novo_status
    WHERE idoco = id_ocorrencia;
END;
$$ LANGUAGE plpgsql;

-- Garantindo acesso ao grupo policia
GRANT EXECUTE ON FUNCTION atualizar_status_ocorrencia(INT, VARCHAR, INT) TO grupo_policia;
--------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION consultar_detalhes_pessoa(id_pessoa INT)
RETURNS TABLE (
    nome VARCHAR,
    estado_civil VARCHAR,
    data_nascimento DATE,
    telefone NUMERIC,
    email VARCHAR,
    genero VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT nompes, estcivpes, datnaspes, numtelpes, emailpes, genpes
    FROM pessoa
    WHERE idpes = id_pessoa;
END;
$$ LANGUAGE plpgsql;

-- Garantindo acesso ao grupo policia
GRANT EXECUTE ON FUNCTION consultar_detalhes_pessoa(INT) TO grupo_policia;


-------------------------------------------------------------------------------------

---------------------Funções do GRUPO_CIDADAO -------------------     
--------------------------------------------------

CREATE OR REPLACE FUNCTION atualizar_dados_pessoais(
    _idusu INT,
    _novo_email VARCHAR(80),
    _novo_telefone NUMERIC(14, 0)
) RETURNS VOID AS $$
BEGIN
    UPDATE pessoa
    SET emailpes = COALESCE(_novo_email, emailpes),
        numtelpes = COALESCE(_novo_telefone, numtelpes)
    WHERE usuarioidusu = _idusu;
END;
$$ LANGUAGE plpgsql;
GRANT EXECUTE ON FUNCTION atualizar_dados_pessoais TO grupo_cidadao;
-----------------------------------------------------------------

CREATE OR REPLACE FUNCTION criar_bo(
    _descoco TEXT,
    _datoco TIMESTAMP,
    _lococo VARCHAR(255),
    _staoco VARCHAR(40),
    _cidadeidcid INT,
    _tipo_violenciaidtipooco VARCHAR(40),
    _tipo_ocorrenciaidtipoco INT,
    _usuarioidusu INT
) RETURNS VOID AS $$
BEGIN
    INSERT INTO ocorrencia (
        descoco, datoco, lococo, staoco, cidadeidcid, funcionariopessoaidpes, tipo_violenciaidtipooco, tipo_ocorrenciaidtipoco, validaoco
    ) 
    VALUES (
        _descoco, _datoco, _lococo, _staoco, _cidadeidcid, NULL, _tipo_violenciaidtipooco, _tipo_ocorrenciaidtipoco, FALSE
    );

    -- Registro opcional de relação com o usuário
    INSERT INTO pessoa (nompes, usuarioidusu)
    VALUES ('Usuário ' || _usuarioidusu, _usuarioidusu)
    ON CONFLICT DO NOTHING;
END;
$$ LANGUAGE plpgsql;

GRANT EXECUTE ON FUNCTION criar_bo TO grupo_cidadao;
