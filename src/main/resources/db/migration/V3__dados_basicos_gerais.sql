INSERT INTO perfil (ativo, atualizado_em, criado_em, resumo, url_imagem, url_lattes) VALUES 
    (true,NULL,'2020-10-05 00:00:00.000','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas rutrum mauris et elit sagittis viverra. Cras vehicula, metus eget tempor ornare, erat magna venenatis mauris, non feugiat nunc magna et odio.',NULL,NULL)
;

INSERT INTO experiencia (ativo, atualizado_em, criado_em, data_fim, data_inicio, descricao, "local", titulo, url_comprovante, tipo_id, perfil_id) VALUES 
    (true,NULL,'2020-10-05 00:00:00.000',NULL,'2020-01-01','Suspendisse potenti. Ut egestas interdum odio hendrerit pharetra. Proin a elementum nisl, ac posuere eros. Suspendisse auctor arcu diam, ac rutrum lorem facilisis id. Mauris ipsum nisi, luctus a urna in, aliquam fermentum nisi. Suspendisse consequat felis id est elementum dignissim. In vestibulum neque non nisl scelerisque pharetra. Cras viverra lorem ac urna congue vulputate.','Duis maximus','Etiam neque dolor',NULL,1,1)
;

INSERT INTO usuario (ativo, atualizado_em, criado_em, cpf, email, email_confirmado, senha, telefone, telefone_confirmado, perfil_id) VALUES 
    (true,NULL,'2020-10-05 00:00:00.000','36977063068','connie.newman@example.com',true,'123',NULL,false,1)
;

INSERT INTO funcionario (ativo, atualizado_em, criado_em, cargo, data_nascimento, bairro, cep, cidade, complemento, estado, logradouro, numero, pais, referencia, matricula, nome, empresa_id, usuario_id) VALUES 
    (true,NULL,'2020-10-05 00:00:00.000','Coordenadora','1990-01-01',NULL,'65000000','São Luís',NULL,'MA',NULL,NULL,NULL,NULL,'123456','Connie Newman',1,1)
;

INSERT INTO aluno (ativo, atualizado_em, criado_em, data_ingresso, data_nascimento, bairro, cep, cidade, complemento, estado, logradouro, numero, pais, referencia, matricula, nome, curso_id, usuario_id) VALUES 
    (true,NULL,'2020-10-05 00:00:00.000','2020-03-01','1996-03-03',NULL,'65000000','São Luís',NULL,'MA',NULL,NULL,NULL,NULL,'202010123','Connie Newman',6,1)
;

INSERT INTO vinculo_estagio (ativo, atualizado_em, criado_em, data_fim, data_inicio, aluno_id, empresa_id) VALUES 
    (true,NULL,'2020-10-05 00:00:00.000','2021-01-01','2020-01-01',1,1)
;

INSERT INTO vaga (ativo, atualizado_em, criado_em, data_limite, descricao, titulo, vagas, empresa_id, tipo_id) VALUES 
    (true,NULL,'2020-10-05 00:00:00.000','2020-11-01','Nunc interdum pretium elit, eget sollicitudin nulla vestibulum in. Etiam mollis sit amet est at pellentesque.','Vestibulum quis mauris vel dui condimentum',10,1,1)
;

INSERT INTO aluno_vaga (aluno_id, vaga_id, ativo, atualizado_em, criado_em, pontuacao, status_id) VALUES 
    (1,1,true,NULL,'2020-10-05 00:00:00.000',NULL,1)
;

INSERT INTO historico_vaga (ativo, atualizado_em, criado_em, descricao, status_id, vaga_id) VALUES 
    (true,NULL,'2020-10-05 00:00:00.000','Vestibulum quis mauris vel dui condimentum semper at sit amet justo.',7,1)
;