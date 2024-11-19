INSERT INTO usuario (cpfusu, senusu, senhafun, matfun)
VALUES ('11111111111', 'senha123', 'senhaFuncionario', 'MAT005');

UPDATE public.usuario
SET senusu = 'senha456'
WHERE cpfusu = '12345678901';

DELETE FROM public.usuario
WHERE cpfusu = '12345678901';

SELECT * FROM auditoria a ;
