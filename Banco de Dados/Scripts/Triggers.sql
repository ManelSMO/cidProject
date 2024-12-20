
-- Triggers e Procedures
--Triggers
-- Função que define a data da ocorrência automaticamente caso não seja fornecida no momento da inserção
CREATE OR REPLACE FUNCTION set_data_ocorrencia()
RETURNS TRIGGER AS $$
BEGIN
    -- Define a data da ocorrência para a data atual caso o valor não seja fornecido
    NEW.datoco := COALESCE(NEW.datoco, CURRENT_TIMESTAMP);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger que chama a função antes de inserir um registro na tabela ocorrencia
CREATE TRIGGER trg_set_data_ocorrencia
BEFORE INSERT ON ocorrencia
FOR EACH ROW
EXECUTE FUNCTION set_data_ocorrencia();
-------------------------------

-- Trigger para atualizar o timestamp de atualizado_em automaticamente
CREATE OR REPLACE FUNCTION atualizar_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.atualizado_em = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_atualizar_usuario
BEFORE UPDATE ON usuario
FOR EACH ROW EXECUTE FUNCTION atualizar_timestamp();

-------------------------------
-- Validação de tipo no login de usuário (Trigger)
CREATE OR REPLACE FUNCTION validar_tipo_usuario_login()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_TABLE_NAME = 'acesso_cidadao' AND EXISTS (
        SELECT 1 
        FROM acesso_policial 
        WHERE usuarioidusu = NEW.usuarioidusu)) THEN
        RAISE EXCEPTION 'O mesmo usuário não pode estar em acesso_cidadao e acesso_policial.';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_validar_cidadao
BEFORE INSERT OR UPDATE ON acesso_cidadao
FOR EACH ROW EXECUTE FUNCTION validar_tipo_usuario_login();

CREATE TRIGGER trigger_validar_policial
BEFORE INSERT OR UPDATE ON acesso_policial
FOR EACH ROW EXECUTE FUNCTION validar_tipo_usuario_login();
-----------------

CREATE OR REPLACE FUNCTION validar_tipo_usuario()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_TABLE_NAME = 'acesso_cidadao' AND 
        (SELECT tipo_usu FROM usuario WHERE idusu = NEW.usuarioidusu) != 'Cidadão') THEN
        RAISE EXCEPTION 'Usuário não é do tipo Cidadão';
    ELSIF (TG_TABLE_NAME = 'acesso_policial' AND 
           (SELECT tipo_usu FROM usuario WHERE idusu = NEW.usuarioidusu) != 'Policial') THEN
        RAISE EXCEPTION 'Usuário não é do tipo Policial';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Associar o trigger às tabelas de acesso
CREATE TRIGGER trigger_validar_acesso_cidadao
BEFORE INSERT OR UPDATE ON acesso_cidadao
FOR EACH ROW EXECUTE FUNCTION validar_tipo_usuario();

CREATE TRIGGER trigger_validar_acesso_policial
BEFORE INSERT OR UPDATE ON acesso_policial
FOR EACH ROW EXECUTE FUNCTION validar_tipo_usuario();

------------------------------------------------------------

---------------------------------------------------------------


-- procedures
CREATE OR REPLACE PROCEDURE inserir_ocorrencia(
    _descoco TEXT,
    _datoco TIMESTAMP,
    _lococo VARCHAR(255),
    _staoco VARCHAR(40),
    _cidadeidcid INT,
    _funcionariopessoaidpes INT,
    _tipo_violenciaidtipooco VARCHAR(40),
    _tipo_ocorrenciaidtipoco INT
) LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO ocorrencia (descoco, datoco, lococo, staoco, cidadeidcid, 
                            funcionariopessoaidpes, tipo_violenciaidtipooco, tipo_ocorrenciaidtipoco, validaoco)
    VALUES (_descoco, _datoco, _lococo, _staoco, _cidadeidcid, _funcionariopessoaidpes, 
            _tipo_violenciaidtipooco, _tipo_ocorrenciaidtipoco, FALSE);
END;
$$;
-----------------------------------------------------
CREATE OR REPLACE PROCEDURE atualizar_status_ocorrencia(
    _idoco INT,
    _novo_staoco VARCHAR(40)
) LANGUAGE plpgsql AS $$
BEGIN
    UPDATE ocorrencia
    SET staoco = _novo_staoco
    WHERE idoco = _idoco;
END;
$$;
----------------------------------------------------------

CREATE OR REPLACE PROCEDURE inserir_pessoa_envolvida(
    _nompesenv VARCHAR(80),
    _descenv TEXT,
    _cpfenv VARCHAR(11),
    _telenv NUMERIC(14, 0),
    _datnasenv DATE,
    _emailenv VARCHAR(80),
    _ocorrenciaidoco INT,
    _tipo_envolvimentotipenv VARCHAR(255)
) LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO pessoa_envolvida (nompesenv, descpesenv, cpfenv, telenv, datnasenv, emailenv, ocorrenciaidoco, tipo_envolvimentotipenv)
    VALUES (_nompesenv, _descenv, _cpfenv, _telenv, _datnasenv, _emailenv, _ocorrenciaidoco, _tipo_envolvimentotipenv);
END;
$$;
-----------------------------------------------------------



