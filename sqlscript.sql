CREATE TABLE Estado ( 
  id_estado NUMBER PRIMARY KEY, 
  nome VARCHAR2(50) 
);

CREATE TABLE Cidade ( 
  id_cidade NUMBER PRIMARY KEY, 
  nome VARCHAR2(50), 
  id_estado NUMBER, 
  CONSTRAINT fk_cidade_estado FOREIGN KEY (id_estado) REFERENCES Estado (id_estado) 
);

CREATE TABLE Endereco ( 
  id_endereco NUMBER PRIMARY KEY, 
  rua VARCHAR2(100), 
  numero VARCHAR2(10), 
  bairro VARCHAR2(100), 
  complemento VARCHAR2(100), 
  cep VARCHAR2(10), 
  id_cidade NUMBER, 
  id_estado NUMBER, 
  CONSTRAINT fk_endereco_cidade FOREIGN KEY (id_cidade) REFERENCES Cidade (id_cidade), 
  CONSTRAINT fk_endereco_estado FOREIGN KEY (id_estado) REFERENCES Estado (id_estado) 
);

CREATE TABLE Paciente ( 
  id_paciente NUMBER PRIMARY KEY, 
  nome VARCHAR2(100), 
  cpf VARCHAR2(15), 
  dataNascimento DATE, 
  id_endereco NUMBER, 
  email VARCHAR2(100), 
  senha VARCHAR2(100), 
  ativo NUMBER(1), 
  CONSTRAINT fk_paciente_endereco FOREIGN KEY (id_endereco) REFERENCES Endereco (id_endereco) 
);

CREATE TABLE Funcionario ( 
  id_funcionario NUMBER PRIMARY KEY, 
  nome VARCHAR2(100), 
  cpf VARCHAR2(15), 
  dataNascimento DATE, 
  id_endereco NUMBER, 
  categoria VARCHAR2(50), 
  dataAdmissao DATE, 
  email VARCHAR2(100), 
  senha VARCHAR2(100), 
  ativo NUMBER(1), 
  CONSTRAINT fk_funcionario_endereco FOREIGN KEY (id_endereco) REFERENCES Endereco (id_endereco) 
);

