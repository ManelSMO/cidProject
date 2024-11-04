


--Função que, caso não seja fornecida no momento da inserção, defina a data da ocorrencia automaticamente
CREATE OR REPLACE FUNCTION set_data_ocorrencia()
RETURNS TRIGGER AS $$
BEGIN
    NEW.datoco := COALESCE(NEW.datoco, CURRENT_DATE);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_set_data_ocorrencia
BEFORE INSERT ON ocorrencia
FOR EACH ROW
EXECUTE FUNCTION set_data_ocorrencia();

------------------------------------------------------------




