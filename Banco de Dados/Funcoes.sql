--- FUNÇÔES ---------------

-- Calcula a idade
CREATE OR REPLACE FUNCTION calcular_idade(cpf_or_id VARCHAR)
RETURNS INT AS $$
DECLARE
    data_nascimento DATE;
BEGIN
    -- Verifica se o parâmetro é CPF ou ID e busca a data de nascimento
    IF LENGTH(cpf_or_id) = 11 THEN
        -- Se for CPF, busca pelo CPF
        SELECT datnaspes INTO data_nascimento
        FROM pessoa
        WHERE cpfusu = cpf_or_id;
    ELSE
        -- Caso contrário, busca pelo ID (fazendo a conversão de tipo)
        SELECT datnaspes INTO data_nascimento
        FROM pessoa
        WHERE idpes = CAST(cpf_or_id AS INT);  -- Converte o parâmetro para INT
    END IF;
    
    -- Retorna a idade
    IF data_nascimento IS NOT NULL THEN
        RETURN EXTRACT(YEAR FROM AGE(data_nascimento));
    ELSE
        RAISE EXCEPTION 'Pessoa não encontrada';
    END IF;
END;
$$ LANGUAGE plpgsql;





-- Verifica se o CPF já está cadastrado no sistema
CREATE OR REPLACE FUNCTION verificar_cpf_existente(cpf VARCHAR(14))
RETURNS BOOLEAN AS $$
DECLARE
    resultado BOOLEAN;
BEGIN
    -- Verifica se o CPF existe na tabela 'acesso_cidadao'
    SELECT EXISTS(SELECT 1 FROM acesso_cidadao WHERE cpfusu = cpf) INTO resultado;
    RETURN resultado;
END;
$$ LANGUAGE plpgsql;


---------------------------------------------------------

CREATE OR REPLACE FUNCTION listar_ocorrencias_validadas()
RETURNS TABLE(idoco INT, descricao TEXT, data TIMESTAMP, local VARCHAR(255), status VARCHAR(40)) AS $$
BEGIN
    -- Retorna as ocorrências validadas
    RETURN QUERY
    SELECT ocorrencia.idoco, ocorrencia.descoco, ocorrencia.datoco, ocorrencia.lococo, ocorrencia.staoco
    FROM ocorrencia
    WHERE ocorrencia.validaoco = TRUE;
END;
$$ LANGUAGE plpgsql;

------------------------------------------------------------





