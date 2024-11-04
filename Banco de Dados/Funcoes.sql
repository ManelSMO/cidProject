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


