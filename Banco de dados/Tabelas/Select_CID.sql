-- 1) Relacionar todas as pessoas com idades entre 20 e 30 anos e do sexo feminino. Ordenar o relatório pelo nome das pessoas em ordem descendente;
SELECT 
    p.nom_pessoa AS nome, 
    p.idadepessoa AS idade
FROM 
    pessoa p
WHERE 
    p.idadepessoa BETWEEN 20 AND 30 
    AND p.generopessoa = 'F'
ORDER BY 
    p.nom_pessoa DESC;

-- 2) Relacionar as ocorrências registras em meses pares de 2023 e que ocorreram nas cidades de São Miguel do Oeste e Descanso. 
--    Ordene o relatório pelo tipo de ocorrência de forma ascendente;
SELECT 
    tipo.desc_ocorrencia AS tipo_ocorrencia, 
    c.nom_cidade AS cidade, 
    o.data_ocorrencia
FROM 
    ocorrencia o
JOIN 
    cidade c ON o.cod_cidadeo = c.cod_cidade
JOIN 
    tipo_ocorrencia tipo ON o.cod_tipo_ocorrencia = tipo.cod_tipo_ocorrencia
WHERE 
    EXTRACT(MONTH FROM o.data_ocorrencia) IN (2, 4, 6, 8, 10, 12)
    AND EXTRACT(YEAR FROM o.data_ocorrencia) = 2023
    AND c.nom_cidade IN ('São Miguel do Oeste', 'Descanso')
ORDER BY 
    tipo.desc_ocorrencia ASC;

-- 3) Relacionar todos os departamentos de policia das cidades de Maravilha, Descanso, Itapiranga e Guaraciaba que registraram casos de roubo em 2024. 
--    Ordene o relatório do DP com mais ocorrências para o DP com menos ocorrências;
SELECT bo.*, cidade.nom_cidade
FROM bo
INNER JOIN funcionario ON bo.cod_fun = funcionario.cod_fun
INNER JOIN cidade ON bo.cod_cidadebo = cidade.cod_cidade
WHERE cidade.nom_cidade IN ('Maravilha', 'Descanso', 'Itapiranga', 'Guaraciaba')
AND EXTRACT(YEAR FROM bo.data_bo) = 2024
AND bo.cod_bo IN (
    SELECT cod_bo
    FROM ocorrencia
    WHERE cod_tipo_ocorrencia = (
        SELECT cod_tipo_ocorrencia
        FROM tipo_ocorrencia
        WHERE desc_ocorrencia = 'Roubo'
    )
);

-- 4) Relacionar o tipo da ocorrência, cidade e o total de ocorrências por tipo e cidade. Relacionar a cidade com mais ocorrências para cidade com menos ocorrências.
   SELECT +
    tipo.desc_ocorrencia AS tipo_ocorrencia, 
    c.nom_cidade AS cidade, 
    COUNT(o.cod_ocorrencia) AS total_ocorrencias
FROM 
    ocorrencia o
JOIN 
    cidade c ON o.cod_cidadeo = c.cod_cidade
JOIN 
    tipo_ocorrencia tipo ON o.cod_tipo_ocorrencia = tipo.cod_tipo_ocorrencia
GROUP BY 
    tipo.desc_ocorrencia, c.nom_cidade
ORDER BY 
    total_ocorrencias DESC, c.nom_cidade;