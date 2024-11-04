--- VIEWS -----------------

--- View que mostra informações detalhadas sobre cada ocorrência, incluindo o tipo de ocorrência, o local e o funcionário responsável.
CREATE VIEW vw_ocorrencias_detalhadas AS
SELECT 
    o.idoco AS id_ocorrencia,
    o.datoco AS data_ocorrencia,
    o.lococo AS local_ocorrencia,
    t.desctipoco AS tipo_ocorrencia,
    f.carfun AS cargo_funcionario,
    p.nompes AS nome_funcionario
FROM ocorrencia o
JOIN tipo_ocorrencia t ON o.tipo_ocorrenciaidtipoco = t.idtipoco
JOIN funcionario f ON o.funcionarioidfun = f.idfun
JOIN pessoa p ON f.pessoaidpes = p.idpes;
---

