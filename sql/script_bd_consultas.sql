/*Scripts*/

CREATE TABLE Estado
(
    id_Estado INT NOT NULL PRIMARY KEY,
    nome_Estado VARCHAR2(45) NOT NULL
);

CREATE TABLE Especialidade
(
    id_Especialidade INT NOT NULL PRIMARY KEY,
    nome VARCHAR2(45) NOT NULL,
    descricao VARCHAR2(100)NOT NULL
);

CREATE TABLE Prontuario
(
    id_Prontuario INT NOT NULL PRIMARY KEY,
    diagnostico VARCHAR2(100) NOT NULL,
    receituario VARCHAR2(100) NOT NULL
);

CREATE TABLE PlanoDeSaude
(
    id_PlanoDeSaude INT NOT NULL PRIMARY KEY,
    nome VARCHAR2(45) NOT NULL,
    descricao VARCHAR2(100),
    cobertura VARCHAR2(100)
);

CREATE TABLE Categoria_Funcionario
(
    id_Categoria INT NOT NULL PRIMARY KEY,
    nome VARCHAR2(45) NOT NULL,
    descricao VARCHAR2(100),
    ativo NUMBER(1,0) NOT NULL,
    id_Funcionario INT
);

ALTER TABLE Categoria_Funcionario ADD CONSTRAINT fk_id_funcionario_Categ FOREIGN KEY(id_Funcionario) REFERENCES Funcionario(id_Funcionario);

CREATE TABLE Cidade
(
    id_Cidade INT NOT NULL PRIMARY KEY,
    nome VARCHAR2(45) NOT NULL,
    id_Estado INT,
    CONSTRAINT fk_id_Estado FOREIGN KEY(id_Estado) references Estado(id_Estado)
);

CREATE TABLE Endereco
(
    id_Endereco INT NOT NULL PRIMARY KEY,
    rua VARCHAR2(45) NOT NULL,
    numero INT NOT NULL,
    bairro VARCHAR2(45) NOT NULL,
    complemento VARCHAR2(45) NOT NULL,
    cep VARCHAR2(45) NOT NULL,
    id_Cidade INT,
    CONSTRAINT fk_id_Cidade FOREIGN KEY(id_Cidade) references Cidade(id_Cidade)
);


CREATE TABLE Funcionario
(
    id_Funcionario INT NOT NULL PRIMARY KEY,
    nome VARCHAR2(45) NOT NULL,
    dataAdmissao DATE NOT NULL,
    cpf VARCHAR2(45)NOT NULL,
    email VARCHAR2(45)NOT NULL,
    dataNascimento DATE NOT NULL,
    id_Endereco INT NOT NULL,
    telefone VARCHAR2(45) NOT NULL,
    CONSTRAINT fk_id_Endereco_Func FOREIGN KEY (id_Endereco) references Endereco(id_Endereco)
);

CREATE TABLE Medico
(
    id_Medico INT NOT NULL PRIMARY KEY,
    crm VARCHAR2(45) NOT NULL,
    maxConsultas INT ,
    id_Funcionario INT,
    id_Especialidade INT,
    CONSTRAINT fk_id_Funcionario_Med FOREIGN KEY(id_Funcionario) REFERENCES Funcionario(id_Funcionario),
    CONSTRAINT fk_id_Especialidade FOREIGN KEY(id_Especialidade) REFERENCES Especialidade(id_Especialidade)
);

CREATE TABLE HorarioAtendimento
(
    id_Horario INT NOT NULL PRIMARY KEY,
    horarioIncio DATE,
    horarioFim DATE,
    disponivel NUMBER(1,0) NOT NULL,
    id_Medico INT,
    CONSTRAINT fk_id_Medico FOREIGN KEY(id_Medico) REFERENCES Medico(id_Medico)
);

CREATE TABLE Fila
(
    id_Fila INT NOT NULL PRIMARY KEY,
    tipoDeFila VARCHAR2(45) NOT NULL,
    id_Especialidade INT,
    CONSTRAINT fk_especialidade_Fila FOREIGN KEY(id_Especialidade) REFERENCES Especialidade(id_Especialidade)
);

CREATE TABLE Paciente
(
    cpf INT NOT NULL PRIMARY KEY,
    nome VARCHAR2(45) NOT NULL,
    senha INT NOT NULL,
    email VARCHAR2(45) NOT NULL,
    idade INT NOT NULL,
    id_Prontuario INT,
    id_PlanoDeSaude INT,
    id_Endereco INT,
    id_Fila INT,
    CONSTRAINT fk_id_ProntuarioPac FOREIGN KEY(id_Prontuario) REFERENCES Prontuario(id_Prontuario),
    CONSTRAINT fk__id_PlanoDeSaude FOREIGN KEY(id_PlanoDeSaude) REFERENCES PlanoDeSaude(id_PlanoDeSaude),
    CONSTRAINT fk_id_EnderecoPac FOREIGN KEY(id_Endereco) REFERENCES Endereco(id_Endereco),
    CONSTRAINT fk_id_Fila FOREIGN KEY(id_Fila) REFERENCES Fila(id_Fila)
);

CREATE TABLE Consulta
(
    id_Consulta INT NOT NULL PRIMARY KEY,
    data_Consulta Date,
    confirmacao NUMBER(1,0) NOT NULL,
    autorizacao_PlanoSaude NUMBER(1,0) NOT NULL,
    paciente_CPF INT,
    id_Medico INT,
    id_Especialidade INT,
    CONSTRAINT fk_id_Paciente FOREIGN KEY (paciente_CPF) REFERENCES Paciente(cpf),
    CONSTRAINT fk_id_MedicoConsul FOREIGN KEY (id_Medico) REFERENCES Medico(id_Medico),
    CONSTRAINT fk_id_EspecialidadeConsul FOREIGN KEY (id_Especialidade) REFERENCES Especialidade(id_Especialidade)
);


alter session
set nls_date_format
= 'DD-MON-YYYY HH24:MI:SS';
