insert into Role(ROLE) VALUES ('ROLE_ADMIN');
insert into Role(ROLE) VALUES ('ROLE_MEDICO');
insert into Role(ROLE) VALUES ('ROLE_RECEPCIONISTA');

insert into Categoria_Funcionario(NOME, DESCRICAO, ATIVO) values ('Admin', 'administrador', 1);
insert into Categoria_Funcionario(NOME, DESCRICAO, ATIVO) values ('Médico', 'médico', 1);
insert into Categoria_Funcionario(NOME, DESCRICAO, ATIVO) values ('Recepcionista', 'recepcionista', 1);

insert into Funcionario(NOME, CPF, EMAIL, TELEFONE, CELULAR, LOGIN, SENHA, DATA_NASCIMENTO, DATA_ADMISSAO, CATEGORIA_FUNCIONARIO_ID, ENDERECO_ID) values ('Angelica', '111-222-333-44', null, null, null, 'angelica', '$2a$12$g0On5X3.Eydn0.oiARhCi.M7jI2AonX7gL3RYK6mtsbfK9bSi61F2', null, null, 1, null);
INSERT INTO Funcionario_Role(FUNCIONARIO_ID, ROLE_ID) VALUES (1, 1);

insert into Funcionario(NOME, CPF, EMAIL, TELEFONE, CELULAR, LOGIN, SENHA, DATA_NASCIMENTO, DATA_ADMISSAO, CATEGORIA_FUNCIONARIO_ID, ENDERECO_ID) values ('Carlyson', '111-222-333-55', null, null, null, 'carlyson', '$2a$12$g0On5X3.Eydn0.oiARhCi.M7jI2AonX7gL3RYK6mtsbfK9bSi61F2', null, null, 2, null);
INSERT INTO Funcionario_Role(FUNCIONARIO_ID, ROLE_ID) VALUES (2, 2);

insert into Funcionario(NOME, CPF, EMAIL, TELEFONE, CELULAR, LOGIN, SENHA, DATA_NASCIMENTO, DATA_ADMISSAO, CATEGORIA_FUNCIONARIO_ID, ENDERECO_ID) values ('Galba', '111-222-333-66', null, null, null, 'galba', '$2a$12$g0On5X3.Eydn0.oiARhCi.M7jI2AonX7gL3RYK6mtsbfK9bSi61F2', null, null, 2, null);
INSERT INTO Funcionario_Role(FUNCIONARIO_ID, ROLE_ID) VALUES (3, 2);

insert into Funcionario(NOME, CPF, EMAIL, TELEFONE, CELULAR, LOGIN, SENHA, DATA_NASCIMENTO, DATA_ADMISSAO, CATEGORIA_FUNCIONARIO_ID, ENDERECO_ID) values ('Darlan', '111-222-333-77', null, null, null, 'darlan', '$2a$12$g0On5X3.Eydn0.oiARhCi.M7jI2AonX7gL3RYK6mtsbfK9bSi61F2', null, null, 3, null);
INSERT INTO Funcionario_Role(FUNCIONARIO_ID, ROLE_ID) VALUES (4, 3);