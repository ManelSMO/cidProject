CREATE OR REPLACE FUNCTION registrar_auditoria()
RETURNS TRIGGER AS $$
DECLARE
    dados_antes JSONB;
    dados_depois JSONB;
    registro_id TEXT;
BEGIN
    -- Verifica a operação realizada na tabela
    IF (TG_OP = 'DELETE') THEN
        -- Captura os dados antes da exclusão e define NULL para os novos dados
        dados_antes := TO_JSONB(OLD) - 'senha' - 'senhafun';
        dados_depois := NULL;

        -- Determina o ID do registro baseado na tabela
        CASE TG_TABLE_NAME
            WHEN 'pessoa' THEN
                registro_id := OLD.idpes::TEXT;
            WHEN 'ocorrencia' THEN
                registro_id := OLD.idoco::TEXT;
            WHEN 'funcionario' THEN
                registro_id := OLD.pessoaidpes::TEXT;
            WHEN 'usuario' THEN
                registro_id := OLD.idusu::TEXT; -- Adicionado para a tabela 'usuario'
            ELSE
                registro_id := NULL; -- Caso a tabela não tenha ID reconhecido
        END CASE;

    ELSE
        -- Captura os dados antes e depois da alteração/inserção
        dados_antes := COALESCE(TO_JSONB(OLD), '{}') - 'senha' - 'senhafun'; 
        dados_depois := COALESCE(TO_JSONB(NEW), '{}') - 'senha' - 'senhafun';

        -- Determina o ID do registro baseado na tabela
        CASE TG_TABLE_NAME
            WHEN 'pessoa' THEN
                registro_id := NEW.idpes::TEXT;
            WHEN 'ocorrencia' THEN
                registro_id := NEW.idoco::TEXT;
            WHEN 'funcionario' THEN
                registro_id := NEW.pessoaidpes::TEXT;
            WHEN 'usuario' THEN
                registro_id := NEW.idusu::TEXT; -- Adicionado para a tabela 'usuario'
            ELSE
                registro_id := NULL; -- Caso a tabela não tenha ID reconhecido
        END CASE;
    END IF;

    -- Garante que o registro_id não seja NULL
    IF registro_id IS NULL THEN
        RAISE EXCEPTION 'registro_id não identificado para a tabela %', TG_TABLE_NAME;
    END IF;

    -- Insere o registro na tabela de auditoria
    INSERT INTO public.auditoria (
        tabela_alterada,
        operacao,
        registro_id,
        usuario_responsavel,
        dados_anteriores,
        dados_novos,
        data_hora
    )
    VALUES (
        TG_TABLE_NAME,                   -- Nome da tabela que foi alterada
        TG_OP,                           -- Tipo de operação (INSERT, UPDATE, DELETE)
        registro_id,                     -- ID do registro afetado
        current_user,                    -- Usuário que realizou a operação
        dados_antes,                     -- Dados antes da operação
        dados_depois,                    -- Dados após a operação
        CURRENT_TIMESTAMP                -- Timestamp da auditoria
    );

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;


-- Exemplo de trigger para a tabela "usuario"
CREATE TRIGGER auditoria_usuario_trigger
AFTER INSERT OR UPDATE OR DELETE ON public.usuario
FOR EACH ROW
EXECUTE FUNCTION registrar_auditoria();

CREATE TRIGGER auditoria_ocorrencia
AFTER INSERT OR UPDATE OR DELETE ON public.ocorrencia
FOR EACH ROW
EXECUTE FUNCTION registrar_auditoria();


CREATE TRIGGER auditoria_pessoa
AFTER INSERT OR UPDATE OR DELETE ON public.pessoa
FOR EACH ROW
EXECUTE FUNCTION registrar_auditoria();

CREATE TRIGGER auditoria_funcionario
AFTER INSERT OR UPDATE OR DELETE ON public.funcionario
FOR EACH ROW
EXECUTE FUNCTION registrar_auditoria();