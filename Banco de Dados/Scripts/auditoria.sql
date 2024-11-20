-- Criação da tabela de auditoria
CREATE TABLE public.auditoria (
    id SERIAL PRIMARY KEY,
    tabela_alterada VARCHAR(100) NOT NULL, -- Nome da tabela
    operacao VARCHAR(10) NOT NULL,        -- Tipo de operação (INSERT, UPDATE, DELETE)
    registro_id TEXT NOT NULL,            -- ID do registro afetado
    usuario_responsavel VARCHAR(100) DEFAULT current_user, -- Usuário responsável
    dados_anteriores JSONB,               -- Dados antes da operação
    dados_novos JSONB,                    -- Dados após a operação
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Data e hora do evento
);
COMMENT ON TABLE public.auditoria IS 'Tabela de auditoria para registrar alterações nas tabelas do sistema';
COMMENT ON COLUMN public.auditoria.tabela_alterada IS 'Nome da tabela alterada';
COMMENT ON COLUMN public.auditoria.operacao IS 'Tipo de operação realizada';
COMMENT ON COLUMN public.auditoria.registro_id IS 'ID do registro afetado';
COMMENT ON COLUMN public.auditoria.usuario_responsavel IS 'Usuário que realizou a operação';
COMMENT ON COLUMN public.auditoria.dados_anteriores IS 'Dados do registro antes da alteração';
COMMENT ON COLUMN public.auditoria.dados_novos IS 'Dados do registro após a alteração';
COMMENT ON COLUMN public.auditoria.data_hora IS 'Data e hora do evento de auditoria';

CREATE OR REPLACE FUNCTION registrar_auditoria()
RETURNS TRIGGER AS $$
DECLARE
    dados_antes JSONB;
    dados_depois JSONB;
    registro_id TEXT;
BEGIN
    -- Identifica o registro_id com base nos dados disponíveis (OLD ou NEW)
    IF (TG_OP = 'DELETE') THEN
        dados_antes := TO_JSONB(OLD) - ARRAY['senha', 'senhafun'];
        dados_depois := NULL;
        registro_id := OLD.idusu::TEXT;
    ELSE
        dados_antes := TO_JSONB(OLD) - ARRAY['senha', 'senhafun'];
        dados_depois := TO_JSONB(NEW) - ARRAY['senha', 'senhafun'];
        registro_id := COALESCE(NEW.idusu::TEXT, OLD.idusu::TEXT);
    END IF;

    -- Insere o registro de auditoria
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
        TG_TABLE_NAME,    -- Nome da tabela
        TG_OP,            -- Operação (INSERT, UPDATE, DELETE)
        registro_id,      -- ID do registro afetado
        current_user,     -- Usuário responsável
        dados_antes,      -- Dados antes da operação
        dados_depois,     -- Dados depois da operação
        CURRENT_TIMESTAMP -- Data e hora do evento
    );

    RETURN NULL; -- Trigger AFTER não altera o fluxo principal
END;
$$ LANGUAGE plpgsql;


-- Exemplo de trigger para a tabela "usuario"
CREATE TRIGGER auditoria_usuario_trigger
AFTER INSERT OR UPDATE OR DELETE ON public.usuario
FOR EACH ROW
EXECUTE FUNCTION registrar_auditoria();



