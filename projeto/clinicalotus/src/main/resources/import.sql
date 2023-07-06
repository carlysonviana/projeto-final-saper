insert into Role(ROLE) VALUES ('ROLE_ADMIN');
insert into Role(ROLE) VALUES ('ROLE_MEDICO');
insert into Role(ROLE) VALUES ('ROLE_RECEPCIONISTA');

insert into Categoria_Funcionario(NOME, DESCRICAO, ATIVO) values ('Admin', 'administrador', 1);
insert into Categoria_Funcionario(NOME, DESCRICAO, ATIVO) values ('Médico', 'médico', 1);
insert into Categoria_Funcionario(NOME, DESCRICAO, ATIVO) values ('Recepcionista', 'recepcionista', 1);

insert into Especialidade(NOME, DESCRICAO) values ('Clínica Médica', 'Clínica Médica');
insert into Especialidade(NOME, DESCRICAO) values ('Pediatria', 'Pediatria');
insert into Especialidade(NOME, DESCRICAO) values ('Cirurgia Geral', 'Cirurgia Geral');
insert into Especialidade(NOME, DESCRICAO) values ('Ginecologia e Obstetrícia', 'Ginecologia e Obstetrícia');
insert into Especialidade(NOME, DESCRICAO) values ('Anestesiologia', 'Anestesiologia');
insert into Especialidade(NOME, DESCRICAO) values ('Ortopedia e Traumatologia', 'Ortopedia e Traumatologia');
insert into Especialidade(NOME, DESCRICAO) values ('Medicina do trabalho', 'Medicina do trabalho');
insert into Especialidade(NOME, DESCRICAO) values ('Cardiologia', 'Oftalmologia');
insert into Especialidade(NOME, DESCRICAO) values ('Psquiatria', 'Psquiatria');
insert into Especialidade(NOME, DESCRICAO) values ('Radiologia e Diagnóstico por Imagem', 'Radiologia e Diagnóstico por Imagem');
insert into Especialidade(NOME, DESCRICAO) values ('Dermatologia', 'Dermatologia');
insert into Especialidade(NOME, DESCRICAO) values ('Medicina de Família e Comunidade', 'Medicina de Família e Comunidade');

insert into Funcionario(NOME, CPF, EMAIL, TELEFONE, CELULAR, LOGIN, SENHA, DATA_NASCIMENTO, DATA_ADMISSAO, CATEGORIA_FUNCIONARIO_ID, ENDERECO_ID) values ('Angelica', '111-222-333-44', null, null, null, 'angelica', '$2a$12$g0On5X3.Eydn0.oiARhCi.M7jI2AonX7gL3RYK6mtsbfK9bSi61F2', null, null, 1, null);
INSERT INTO Funcionario_Role(FUNCIONARIO_ID, ROLE_ID) VALUES (1, 1);

insert into Funcionario(NOME, CPF, EMAIL, TELEFONE, CELULAR, LOGIN, SENHA, DATA_NASCIMENTO, DATA_ADMISSAO, CATEGORIA_FUNCIONARIO_ID, ENDERECO_ID) values ('Carlyson', '111-222-333-55', null, null, null, 'carlyson', '$2a$12$g0On5X3.Eydn0.oiARhCi.M7jI2AonX7gL3RYK6mtsbfK9bSi61F2', null, null, 2, null);
INSERT INTO Funcionario_Role(FUNCIONARIO_ID, ROLE_ID) VALUES (2, 2);

insert into Medico(FUNCIONARIO_FUNCIONARIO_ID, CRM, ESPECIALIDADE_ID) values (2, 12345678910, 1);

insert into Funcionario(NOME, CPF, EMAIL, TELEFONE, CELULAR, LOGIN, SENHA, DATA_NASCIMENTO, DATA_ADMISSAO, CATEGORIA_FUNCIONARIO_ID, ENDERECO_ID) values ('Galba', '111-222-333-66', null, null, null, 'galba', '$2a$12$g0On5X3.Eydn0.oiARhCi.M7jI2AonX7gL3RYK6mtsbfK9bSi61F2', null, null, 2, null);
INSERT INTO Funcionario_Role(FUNCIONARIO_ID, ROLE_ID) VALUES (3, 2);

insert into Medico(FUNCIONARIO_FUNCIONARIO_ID, CRM, ESPECIALIDADE_ID) values (3, 12345678911, 1);

insert into Funcionario(NOME, CPF, EMAIL, TELEFONE, CELULAR, LOGIN, SENHA, DATA_NASCIMENTO, DATA_ADMISSAO, CATEGORIA_FUNCIONARIO_ID, ENDERECO_ID) values ('Darlan', '111-222-333-77', null, null, null, 'darlan', '$2a$12$g0On5X3.Eydn0.oiARhCi.M7jI2AonX7gL3RYK6mtsbfK9bSi61F2', null, null, 3, null);
INSERT INTO Funcionario_Role(FUNCIONARIO_ID, ROLE_ID) VALUES (4, 3);

