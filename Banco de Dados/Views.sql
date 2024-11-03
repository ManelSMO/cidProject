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

--- Vies para visualizar rapidamente o tipo de envolvimento de cada pessoa em uma ocorrência específica.
CREATE VIEW vw_pessoas_envolvidas AS
SELECT 
    pe.idpesenv AS id_envolvimento,
    pe.tipoenv AS tipo_envolvimento,
    pe.descpesenv AS descricao_envolvimento,
    o.idoco AS id_ocorrencia,
    p.nompes AS nome_pessoa,
    t.desctipoco AS tipo_ocorrencia
FROM pessoa_envolvida pe
JOIN ocorrencia o ON pe.ocorrenciaidoco = o.idoco
JOIN pessoa p ON pe.pessoaidpes = p.idpes
JOIN tipo_ocorrencia t ON o.tipo_ocorrenciaidtipoco = t.idtipoco;
---