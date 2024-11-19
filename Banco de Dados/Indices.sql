----- INDICES ------------------------


-- Índices para a tabela `anexo`
CREATE INDEX idx_anexo_ocorrencia ON anexo (ocorrenciaidoco);

-- Índices para a tabela `bairro`
CREATE INDEX idx_bairro_estado ON bairro (estadoidest);

-- Índices para a tabela `cidade`
CREATE INDEX idx_cidade_estado ON cidade (estadoidest);

-- Índices para a tabela `departamento_policial`
CREATE INDEX idx_departamento_cidade ON departamento_policial (cidadeidcid);
CREATE INDEX idx_departamento_bairro ON departamento_policial (bairroidbai);

-- Índices para a tabela `endereco_pessoa`
CREATE INDEX idx_endereco_pessoa_bairro ON endereco_pessoa (bairroidbai);
CREATE INDEX idx_endereco_pessoa_cidade ON endereco_pessoa (cidadeidcid);
CREATE INDEX idx_endereco_pessoa_pessoa ON endereco_pessoa (pessoaidpes);

-- Índices para a tabela `estado`
CREATE INDEX idx_estado_pais ON estado (paisidpais);

-- Índices para a tabela `funcionario`
CREATE INDEX idx_funcionario_departamento ON funcionario (departamento_policiaiddep);
CREATE INDEX idx_funcionario_pessoa ON funcionario (pessoaidpes);

-- Índices para a tabela `ocorrencia`
CREATE INDEX idx_ocorrencia_tipo_ocorrencia ON ocorrencia (tipo_ocorrenciaidtipoco);
CREATE INDEX idx_ocorrencia_funcionario ON ocorrencia (funcionariopessoaidpes);
CREATE INDEX idx_ocorrencia_tipo_violencia ON ocorrencia (tipo_violenciaidtipooco);
CREATE INDEX idx_ocorrencia_cidade ON ocorrencia (cidadeidcid);
CREATE INDEX idx_ocorrencia_status ON ocorrencia (staoco);
CREATE INDEX idx_ocorrencia_valida ON ocorrencia(validaoco);

-- Índices para a tabela `pessoa`
CREATE INDEX idx_pessoa_usuario ON pessoa (usuarioidusu);
CREATE INDEX idx_pessoa_email ON pessoa (emailpes);
CREATE INDEX idx_pessoa_telefone ON pessoa (numtelpes);

-- Índices para a tabela `pessoa_envolvida`
CREATE INDEX idx_pessoa_envolvida_ocorrencia ON pessoa_envolvida (ocorrenciaidoco);
CREATE INDEX idx_pessoa_envolvida_tipo_envolvimento ON pessoa_envolvida (tipo_envolvimentotipenv);

-- Índices para a tabela `usuario`
CREATE INDEX idx_usuario_cpf ON usuario (cpfusu);
