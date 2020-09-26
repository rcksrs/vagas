CREATE TABLE empresa (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	cnpj text NOT NULL,
	email text NOT NULL,
	bairro text NULL,
	cep text NOT NULL,
	cidade text NULL,
	complemento text NULL,
	estado text NULL,
	logradouro text NULL,
	numero text NULL,
	pais text NULL,
	referencia text NULL,
	nome text NOT NULL,
	natureza text NULL,
	representante text NOT NULL,
	site text NULL,
	telefone text NOT NULL,
	CONSTRAINT empresa_pkey PRIMARY KEY (id)
);

CREATE TABLE perfil (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	resumo text NULL,
	url_imagem text NULL,
	url_lattes text NULL,
	CONSTRAINT perfil_pkey PRIMARY KEY (id)
);

CREATE TABLE status (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	descricao text NOT NULL UNIQUE,
	CONSTRAINT status_pkey PRIMARY KEY (id)
);

CREATE TABLE tipo_curso (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	descricao text NOT NULL UNIQUE,
	CONSTRAINT tipo_curso_pkey PRIMARY KEY (id)
);

CREATE TABLE tipo_experiencia (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	descricao text NOT NULL UNIQUE,
	CONSTRAINT tipo_experiencia_pkey PRIMARY KEY (id)
);

CREATE TABLE experiencia (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	data_fim date NULL,
	data_inicio date NOT NULL,
	descricao text NULL,
	"local" text NOT NULL,
	titulo text NOT NULL,
	url_comprovante text NULL,
	tipo_id int8 NOT NULL,
	perfil_id int8 NOT NULL,
	CONSTRAINT experiencia_pkey PRIMARY KEY (id),
	CONSTRAINT fk_experiencia_perfil_id FOREIGN KEY (perfil_id) REFERENCES perfil(id),
	CONSTRAINT fk_experiencia_tipo_id FOREIGN KEY (tipo_id) REFERENCES tipo_experiencia(id)
);

CREATE TABLE formacao (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	data_fim date NULL,
	data_inicio date NOT NULL,
	"local" text NOT NULL,
	tipo_id int8 NOT NULL,
	perfil_id int8 NOT NULL,
	CONSTRAINT formacao_pkey PRIMARY KEY (id),
	CONSTRAINT fk_formacao_tipo_id FOREIGN KEY (tipo_id) REFERENCES tipo_curso(id),
	CONSTRAINT fk_formacao_perfil_id FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

CREATE TABLE idioma (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	idioma text NOT NULL,
	nivel text NOT NULL,
	perfil_id int8 NOT NULL,
	CONSTRAINT idioma_pkey PRIMARY KEY (id),
	CONSTRAINT fk_idioma_perfil_id FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

CREATE TABLE usuario (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	cpf text NOT NULL UNIQUE,
	email text NOT NULL UNIQUE,
	email_confirmado bool NULL,
	senha text NOT NULL,
	telefone text NULL,
	telefone_confirmado bool NULL,
	perfil_id int8 NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id),
	CONSTRAINT fk_usuario_perfil_id FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);

CREATE TABLE vaga (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	data_limite date NOT NULL,
	descricao text NOT NULL,
	titulo text NOT NULL,
	vagas int4 NULL,
	empresa_id int8 NULL,
	tipo_id int8 NOT NULL,
	CONSTRAINT vaga_pkey PRIMARY KEY (id),
	CONSTRAINT fk_vaga_empresa_id FOREIGN KEY (empresa_id) REFERENCES empresa(id),
	CONSTRAINT fk_vaga_tipo_id FOREIGN KEY (tipo_id) REFERENCES tipo_experiencia(id)
);

CREATE TABLE curso (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	ch_total int4 NULL,
	nome text NOT NULL UNIQUE,
	modalidade text NOT NULL,
	semestres int4 NOT NULL,
	empresa_id int8 NULL,
	tipo_id int8 NOT NULL,
	CONSTRAINT curso_pkey PRIMARY KEY (id),
	CONSTRAINT fk_curso_tipo_id FOREIGN KEY (tipo_id) REFERENCES tipo_curso(id),
	CONSTRAINT fk_curso_empresa_id FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

CREATE TABLE vaga_curso (
	vaga_id int8 NOT NULL,
	curso_id int8 NOT NULL,
	CONSTRAINT vaga_curso_pkey PRIMARY KEY (vaga_id, curso_id),
	CONSTRAINT fk_vaga_curso_vaga_id FOREIGN KEY (vaga_id) REFERENCES vaga(id),
	CONSTRAINT fk_vaga_curso_curso_id FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE funcionario (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	cargo text NOT NULL,
	data_nascimento date NULL,
	bairro text NULL,
	cep text NOT NULL,
	cidade text NULL,
	complemento text NULL,
	estado text NULL,
	logradouro text NULL,
	numero text NULL,
	pais text NULL,
	referencia text NULL,
	matricula text NULL,
	nome text NOT NULL,
	empresa_id int8 NOT NULL,
	usuario_id int8 NOT NULL UNIQUE,
	CONSTRAINT funcionario_pkey PRIMARY KEY (id),
	CONSTRAINT fk_funcionario_empresa_id FOREIGN KEY (empresa_id) REFERENCES empresa(id),
	CONSTRAINT fk_funcionario_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE historico_vaga (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	descricao text NULL,
	status_id int8 NOT NULL,
	vaga_id int8 NOT NULL,
	CONSTRAINT historico_vaga_pkey PRIMARY KEY (id),
	CONSTRAINT fk_historico_vaga_vaga_id FOREIGN KEY (vaga_id) REFERENCES vaga(id),
	CONSTRAINT fk_historico_vaga_status_id FOREIGN KEY (status_id) REFERENCES status(id)
);

CREATE TABLE aluno (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	data_ingresso date NOT NULL,
	data_nascimento date NOT NULL,
	bairro text NULL,
	cep text NOT NULL,
	cidade text NULL,
	complemento text NULL,
	estado text NULL,
	logradouro text NULL,
	numero text NULL,
	pais text NULL,
	referencia text NULL,
	matricula text NOT NULL,
	nome text NOT NULL,
	curso_id int8 NOT NULL,
	usuario_id int8 NOT NULL UNIQUE,
	CONSTRAINT aluno_pkey PRIMARY KEY (id),
	CONSTRAINT fk_aluno_curso_id FOREIGN KEY (curso_id) REFERENCES curso(id),
	CONSTRAINT fk_aluno_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE aluno_vaga (
	aluno_id int8 NOT NULL,
	vaga_id int8 NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	pontuacao int4 NULL,
	status_id int8 NOT NULL,
	CONSTRAINT aluno_vaga_pkey PRIMARY KEY (aluno_id, vaga_id),
	CONSTRAINT fk_aluno_vaga_aluno_id FOREIGN KEY (aluno_id) REFERENCES aluno(id),
	CONSTRAINT fk_aluno_vaga_vaga_id FOREIGN KEY (vaga_id) REFERENCES vaga(id),
	CONSTRAINT fk_aluno_vaga_status_id FOREIGN KEY (status_id) REFERENCES status(id)
);

CREATE TABLE vinculo_estagio (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	data_fim date NOT NULL,
	data_inicio date NOT NULL,
	aluno_id int8 NOT NULL,
	empresa_id int8 NOT NULL,
	CONSTRAINT vinculo_estagio_pkey PRIMARY KEY (id),
	CONSTRAINT fk_vinculo_estagio_aluno_id FOREIGN KEY (aluno_id) REFERENCES aluno(id),
	CONSTRAINT fk_vinculo_estagio_empresa_id FOREIGN KEY (empresa_id) REFERENCES empresa(id)
);

CREATE TABLE aditivo (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	atualizado_em timestamp NULL,
	criado_em timestamp NOT NULL,
	data_fim date NOT NULL,
	data_inicio date NOT NULL,
	observacao text NULL,
	termo text NOT NULL,
	vinculo_estagio_id int8 NOT NULL,
	CONSTRAINT aditivo_pkey PRIMARY KEY (id),
	CONSTRAINT fk_aditivo_vinculo_estagio_id FOREIGN KEY (vinculo_estagio_id) REFERENCES vinculo_estagio(id)
);