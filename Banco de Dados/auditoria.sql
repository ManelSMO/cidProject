CREATE TABLE public.auditoria (
    id SERIAL PRIMARY KEY,
    tabela_alterada VARCHAR(100), 
    operacao VARCHAR(10),          
    registro_id TEXT,              
    usuario_responsavel VARCHAR(100), 
    dados_anteriores JSONB,        
    dados_novos JSONB,            
    data_hora TIMESTAMP           
);

-- Função de Auditoria 
CREATE OR REPLACE FUNCTION registrar_auditoria()
RETURNS TRIGGER AS $$
DECLARE
    dados_antes JSONB;
    dados_depois JSONB;
BEGIN
    IF (TG_OP = 'DELETE') THEN
        dados_antes := TO_JSONB(OLD) - 'senha' - 'senhafun'; 
        dados_depois := NULL;
    ELSE
        dados_antes := TO_JSONB(OLD) - 'senha' - 'senhafun'; 
        dados_depois := TO_JSONB(NEW) - 'senha' - 'senhafun'; 
    END IF;

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
        TG_TABLE_NAME,                  
        TG_OP,                          
        COALESCE(NEW.idusu::TEXT, OLD.idusu::TEXT), 
        current_user,                   
        dados_antes,                   
        dados_depois,                   
        CURRENT_TIMESTAMP               
    );

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Trigger para Auditoria
CREATE TRIGGER auditoria_trigger
AFTER INSERT OR UPDATE OR DELETE ON public.usuario
FOR EACH ROW
EXECUTE FUNCTION registrar_auditoria();
