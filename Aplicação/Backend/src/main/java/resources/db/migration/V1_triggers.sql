-- Função que define a data da ocorrência automaticamente caso não seja fornecida
CREATE OR REPLACE FUNCTION set_data_ocorrencia()
RETURNS TRIGGER AS $$
BEGIN
    NEW.datoco := COALESCE(NEW.datoco, CURRENT_TIMESTAMP);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger que chama a função antes de inserir um registro na tabela ocorrencia
CREATE TRIGGER trg_set_data_ocorrencia
    BEFORE INSERT ON ocorrencia
    FOR EACH ROW
    EXECUTE FUNCTION set_data_ocorrencia();
