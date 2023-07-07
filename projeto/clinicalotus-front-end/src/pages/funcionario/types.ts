export interface Funcionario {
    "id": number,
    "nome": string,
    "cpf": string,
    "email"?: string,
    "celular": string,
    "dataNascimento"?: string,
    "dataAdmissao"?: string,
    "endereco_id"?: number,
    "categoriaFuncionario_id": number
}
export interface FuncionarioAddForm{
    "nome": string
    "email"?: string,
    "cpf": string,
    "login": string,
    "senha": string,
    "celular": string,
    "categoriaFuncionario_id": number
}

export interface FuncionarioEditForm{
    "nome": string,
    "email": string,
    "celular": string,
}

export interface MedicoAddForm{
    "crm": string,
    "email": number,
}