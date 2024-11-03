--- FUNÇÔES ---------------

--- Calcula a idade
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

---

--- Verifica se o cpf ja esta cadastrado no sistema
CREATE OR REPLACE FUNCTION verificar_cpf_existente(cpf VARCHAR(11))
RETURNS BOOLEAN AS $$
DECLARE
    resultado BOOLEAN;
BEGIN
    SELECT EXISTS(SELECT 1 FROM pessoa WHERE cpfpes = cpf) INTO resultado;
    RETURN resultado;
END;
$$ LANGUAGE plpgsql;

select calcular_idade_por_nome ('Carlos Silva') as idade;
