


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

------------------------------------------------------------




