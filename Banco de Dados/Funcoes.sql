--- FUNÇÔES ---------------

-- Calcula a idade
CREATE OR REPLACE FUNCTION calcular_idade_por_nome(nome_pessoa VARCHAR)
RETURNS INT AS $$
DECLARE
    data_nascimento DATE;
BEGIN
    -- Busca a data de nascimento da pessoa com o nome fornecido
    SELECT datnaspes INTO data_nascimento
    FROM pessoa
    WHERE nompes = nome_pessoa;

    -- Verifica se a pessoa foi encontrada
    IF data_nascimento IS NULL THEN
        RAISE EXCEPTION 'Pessoa com nome % não encontrada', nome_pessoa;
    END IF;

    -- Retorna a idade
    RETURN EXTRACT(YEAR FROM AGE(data_nascimento));
END;
$$ LANGUAGE plpgsql;


-- Verifica se o CPF já está cadastrado no sistema
CREATE OR REPLACE FUNCTION verificar_cpf_existente(cpf VARCHAR(13))
RETURNS BOOLEAN AS $$
DECLARE
    resultado BOOLEAN;
BEGIN
    -- Verifica se o CPF existe na tabela 'usuario'
    SELECT EXISTS(SELECT 1 FROM usuario WHERE cpfusu = cpf) INTO resultado;
    RETURN resultado;
END;
$$ LANGUAGE plpgsql;


---------------------------------------------------------

CREATE OR REPLACE FUNCTION listar_ocorrencias_validadas()
RETURNS TABLE(idoco INT, descricao TEXT, data TIMESTAMP, local VARCHAR(255), status VARCHAR(40)) AS $$
BEGIN
    RETURN QUERY
    SELECT idoco, descoco, datoco, lococo, staoco
    FROM ocorrencia
    WHERE validaoco = TRUE;
END;
$$ LANGUAGE plpgsql;
------------------------------------------------------------
CREATE OR REPLACE FUNCTION consultar_historico_ocorrencias(_cpf VARCHAR(11))
RETURNS TABLE(idoco INT, descricao TEXT, data TIMESTAMP, local VARCHAR(255), status VARCHAR(40)) AS $$
BEGIN
    RETURN QUERY
    SELECT o.idoco, o.descoco, o.datoco, o.lococo, o.staoco
    FROM ocorrencia o
    JOIN pessoa_envolvida pe ON o.idoco = pe.ocorrenciaidoco
    WHERE pe.cpfenv = _cpf;
END;
$$ LANGUAGE plpgsql;




