export type Paciente = {
    "id": number,
    "nome": string,
    "cpf": string,
    "email": string,
    "dataNascimento": string,
    "consultas": [number],
    "endereco_id"?: number,
    "plano_id"?: number
}

export type PacienteAddForm = {
    "nome": string,
    "cpf": string,
    "email": string,
    "dataNascimento": string,
    "endereco_id"?: number,
    "plano_id"?: number
}

export type PacienteEditForm = {
    "nome": string,
    "cpf": string,
    "email": string,
    "dataNascimento": string
}